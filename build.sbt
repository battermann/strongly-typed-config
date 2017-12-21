import com.typesafe.config.{ConfigFactory, ConfigRenderOptions}

lazy val root = (project in file("."))
  .enablePlugins(SbtJsonPlugin)
  .settings(
    inThisBuild(
      List(
        organization := "com.example",
        scalaVersion := "2.12.4",
        version := "0.1.0-SNAPSHOT"
      )),
    name := "Typesafe config",
    libraryDependencies += "com.typesafe.play" %% "play-json" % "2.6.7",
    libraryDependencies += "com.typesafe" % "config" % "1.3.1",
    generateConfigJson,
    (compile in Compile) := (compile in Compile)
      .dependsOn(generateConfigJsonTask)
      .value
  )

val generateConfigJsonTask =
  TaskKey[Unit]("generateConfigJson", "Generate JSON config sample.")

val generateConfigJson = generateConfigJsonTask := {
  val config = ConfigFactory.parseFile(
    (baseDirectory in Compile).value / "src" / "main" / "resources" / "application.conf")
  val content = config.root().render(ConfigRenderOptions.concise())
  val file = (baseDirectory in Compile).value / "src" / "main" / "resources" / "json" / "configuration.json"
  IO.write(file, content)
}
