ThisBuild / scalaVersion     := "2.11.12"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "io.fernandobontorin"
ThisBuild / organizationName := "fernandobontorin"

lazy val root = (project in file("."))
  .settings(
    name := "tweetsExercise",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.0" % "test",
    libraryDependencies += "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.11.2"
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
