lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := """vantage email campaign manager""",
    version := "1.0-SNAPSHOT",
    organization := "vantage circle",
    scalaVersion := "2.13.4",

    libraryDependencies ++= Seq(
      filters,
      ws,
      jdbc,
      cacheApi,
      guice,
      "org.scala-lang.modules" %% "scala-async" % "0.10.0",
      "org.scala-lang" % "scala-reflect" % scalaVersion.value % Provided,
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % "test",
      "mysql" % "mysql-connector-java" % "8.0.17",
      "joda-time" % "joda-time" % "2.7",
      "org.joda" % "joda-convert" % "1.7",
      "com.github.mumoshu" %% "play2-memcached-play28" % "0.11.0",
      "io.lettuce" % "lettuce-core" % "6.1.4.RELEASE",
      "io.getquill" %% "quill-jdbc" % "3.12.0",

    ),
    scalacOptions ++= Seq(
      "-feature",
      "-deprecation",
      "-Xfatal-warnings",
      "-Xasync"
    )
  )

