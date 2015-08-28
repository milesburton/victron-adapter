import sbt.Keys._

name := "solar"

mainClass in Compile := Some("com.mb.Main")

organization := "com.mb"

scalaVersion := "2.11.7"

version := "0.0.1"

licenses := Seq("The Apache Software License, Version 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0"))

homepage := Some(url("https://blog.milesburton.com"))

resolvers += "Sonatype OSS Snapshots" at
  "https://oss.sonatype.org/content/repositories/snapshots"


libraryDependencies += "ch.inventsoft.akka" %% "rxtx-akka-io" % "1.0.4"

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.4"

libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % "2.3.4" % "test"

libraryDependencies += "org.rxtx" % "rxtx" % "2.1.7"


libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.1" % "test"

pomIncludeRepository := { _ => false}

lazy val commonSettings = Seq(
  version := "0.0.1",
  organization := "com.mb",
  scalaVersion := "2.11.7"
)

lazy val app = (project in file("app")).
  settings(commonSettings: _*).
  settings(
  )

pomExtra := (
  <scm>
    <url>git@github.com:msiegenthaler/rxtx-akka-io</url>
    <connection>scm:git:git@github.com:msiegenthaler/rxtx-akka-io</connection>
  </scm>
    <developers>
      <developer>
        <id>msiegenthaler</id>
        <name>Mario Siegenthaler</name>
        <url>https://github.com/msiegenthaler</url>
      </developer>
    </developers>)