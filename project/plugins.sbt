logLevel := Level.Warn

resolvers += "Typesafe repository" at "https://dl.bintray.com/typesafe/maven-releases/"
resolvers += "SBT repository" at "https://dl.bintray.com/sbt/sbt-plugin-releases/"

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.5.12")

addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.1.5")
addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.8.2")
addSbtPlugin("org.scalariform" % "sbt-scalariform" % "1.6.0")

// sbt-web
addSbtPlugin("com.typesafe.sbt" % "sbt-rjs" % "1.0.8")
addSbtPlugin("com.typesafe.sbt" % "sbt-digest" % "1.1.1")
addSbtPlugin("com.typesafe.sbt" % "sbt-gzip" % "1.0.0")
