name := "tavolatura"

version := "1.0"

lazy val `tavolatura` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  jdbc
  , evolutions
  , cache
  , ws
  , specs2 % Test
)

libraryDependencies ++= Seq(
  "mysql" % "mysql-connector-java" % "6.0.6"
)

libraryDependencies ++= Seq(
  "org.scalikejdbc" %% "scalikejdbc" % "2.5.+",
  "org.scalikejdbc" %% "scalikejdbc-config" % "2.5.+",
  "org.scalikejdbc" %% "scalikejdbc-play-initializer" % "2.5.+",
  "org.scalikejdbc" %% "scalikejdbc-play-dbapi-adapter" % "2.5.+",
  "org.scalikejdbc" %% "scalikejdbc-test" % "2.5.+" % "test",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "org.projectlombok" % "lombok" % "1.16.16"
)

libraryDependencies ++= Seq(
  "com.github.tototoshi" %% "play-json4s-jackson" % "0.7.0",
  "com.github.tototoshi" %% "play-json4s-test-jackson" % "0.7.0" % "test"
)

unmanagedResourceDirectories in Test <+= baseDirectory(_ / "target/web/public/test")

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
