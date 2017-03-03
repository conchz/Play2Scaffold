package controllers

import javax.inject.Inject

import commons.Logging
import play.api.cache._
import play.api.mvc._
import play.api.routing.JavaScriptReverseRoute

import scala.concurrent.duration._

/**
 * When controllers are objects, a compilation error occurred: type Application is not a member of package controllers;
 * How to fix it: http://stackoverflow.com/questions/30543960/play-framework-for-scala-compilation-errortype-application-is-not-a-member-of
 */
class Application @Inject() (val cache: CacheApi) extends Controller with Logging {

  val cacheDuration: FiniteDuration = 1.day

  /**
   * Retrieves all routes via reflection.
   * http://stackoverflow.com/questions/12012703/less-verbose-way-of-generating-play-2s-javascript-router
   */
  val routeCache: Seq[JavaScriptReverseRoute] = {
    val jsRoutesClasses = Seq(classOf[routes.javascript])
    jsRoutesClasses.flatMap { jsRoutesClass =>
      val controllers = jsRoutesClass.getFields.map(_.get(null))
      controllers.flatMap { controller =>
        controller.getClass.getDeclaredMethods.filter(_.getName != "_defaultPrefix").map { action =>
          action.invoke(controller).asInstanceOf[play.api.routing.JavaScriptReverseRoute]
        }
      }
    }
  }

  /**
   * Caching action that caches an OK response for the given amount of time with the key.
   * NotFound will be cached for 5 mins. Any other status will not be cached.
   */
  def Caching(key: String, okDuration: Duration): CachedBuilder =
    new Cached(cache)
      .status(_ => key, OK, okDuration.toSeconds.toInt)
      .includeStatus(NOT_FOUND, 5.minutes.toSeconds.toInt)

  def index = Action {
    Ok(views.html.index())
  }

  /**
   * Returns the JavaScript router that the client can use for "type-safe" routes.
   * Uses browser caching; set duration (in seconds) according to your release cycle.
   *
   * @param varName The name of the global variable, defaults to `jsRoutes`
   */
  def jsRoutes(varName: String = "jsRoutes"): EssentialAction = Caching("jsRoutes", cacheDuration) {
    Action { implicit request =>
      Ok(play.api.routing.JavaScriptReverseRouter(varName)(routeCache: _*)).as(JAVASCRIPT)
    }
  }
}