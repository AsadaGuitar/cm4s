ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.13.10"

val AkkaVersion = "2.6.8"
val AkkaHTTPVersion = "10.2.0"
val jwtAkkaHttpVersion = "1.4.4"

lazy val client = (project in file("client"))
  .settings(
    name := "cm4s_client"
  )

lazy val console = (project in file("console"))
  .settings(
    name := "cm4s_console",
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-effect" % "3.5.0",
      "org.typelevel" %% "cats-core" % "2.9.0",
      "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
      "com.typesafe.akka" %% "akka-http" % AkkaHTTPVersion,
      "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
      "com.emarsys" %% "jwt-akka-http" % jwtAkkaHttpVersion,
      "com.typesafe.scala-logging" % "scala-logging-slf4j_2.10" % "2.1.2" % "compile",
      "org.slf4j" % "slf4j-api" % "1.7.12" % "compile",
      "ch.qos.logback" % "logback-classic" % "1.1.3" % "runtime"
    )
  )
