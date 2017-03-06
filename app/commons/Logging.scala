package commons

import javax.inject.Inject

import akka.stream.Materializer
import play.api.Logger
import play.api.mvc.{ Filter, RequestHeader, Result }

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait Logging {
  lazy val logger = Logger(this.getClass)
}

class AccessLoggingFilter @Inject() (implicit val mat: Materializer) extends Filter {

  private[this] val accessLogger = Logger("access")

  override def apply(next: (RequestHeader) => Future[Result])(request: RequestHeader): Future[Result] = {
    val resultFuture = next(request)

    resultFuture.foreach(result => {
      val msg = s"method=${request.method} uri=${request.uri} remote-address=${request.remoteAddress}" +
        s" status=${result.header.status}"
      accessLogger.info(msg)
    })

    resultFuture
  }
}
