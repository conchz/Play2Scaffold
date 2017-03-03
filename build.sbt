organization in ThisBuild := "org.lavenderx"
name in ThisBuild := "Play2Scaffold"
version := "1.0"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala, JavaAppPackaging)

scalaVersion := "2.11.8"
fork in run := true
scalacOptions ++= Seq(
  "-encoding", "UTF-8",
  "-unchecked",
  "-deprecation"
)
javacOptions in compile ++= Seq(
  "-encoding", "UTF-8",
  "-source", "1.8",
  "-target", "1.8",
  "-Xlint:unchecked",
  "-Xlint:deprecation"
)
ivyScala := ivyScala.value map {
  _.copy(overrideScalaVersion = true)
}

resolvers ++= {
  Seq(
    "repox" at "http://repox.gtan.com:8078/"
  )
}

libraryDependencies ++= Seq(
  filters,
  cache,
  "com.typesafe.play" %% "anorm" % "2.5.3",
  "com.typesafe.play" %% "play-json" % "2.5.12",
  "org.webjars" % "requirejs" % "2.3.2",
  "org.webjars" % "jquery" % "2.2.4", // for bootstrap
  "org.webjars" % "bootstrap" % "3.3.7" exclude("org.webjars", "jquery"),
  "org.webjars" % "angularjs" % "1.5.9" exclude("org.webjars", "jquery"),
  "org.webjars" % "angular-ui-bootstrap" % "1.3.3" exclude("org.webjars", "angularjs"),
  specs2 % Test
)

routesGenerator := InjectedRoutesGenerator

pipelineStages := Seq(rjs, digest, gzip)
RjsKeys.paths += ("jsRoutes" -> ("/jsroutes" -> "empty:"))
