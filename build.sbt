import SonatypeKeys._

sonatypeSettings

profileName := "com.hunorkovacs"

organization := """com.hunorkovacs"""

name := """koauth-sync"""

version := "1.1.0"

scalaVersion := "2.11.1"

crossScalaVersions := Seq("2.10.4")

scalacOptions in ThisBuild ++= Seq("-unchecked", "-deprecation", "-target:jvm-1.7")

resolvers ++= Seq(
  "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases/",
  "Sonatype OSS Releases" at "https://oss.sonatype.org/content/repositories/releases"
)

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.3.4",
  "org.slf4j" % "slf4j-api" % "1.7.7",
  "com.hunorkovacs" %% "koauth" % "1.1.0"
)

pomExtra := {
  <url>https://github.com/kovacshuni/koauth-sync</url>
    <licenses>
      <license>
        <name>Apache 2</name>
        <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>
      </license>
    </licenses>
    <scm>
      <connection>scm:git:github.com/kovacshuni/koauth-sync</connection>
      <developerConnection>scm:git@github.com:kovacshuni/koauth-sync.git</developerConnection>
      <url>https://github.com/kovacshuni/koauth-sync</url>
      <tag>1.1.x</tag>
    </scm>
    <developers>
      <developer>
        <id>kovacshuni</id>
        <name>Hunor Kovács</name>
        <url>www.hunorkovacs.com</url>
      </developer>
    </developers>
}

publishTo := Some("releases"  at "https://oss.sonatype.org/service/local/staging/deploy/maven2")

publishMavenStyle := true
