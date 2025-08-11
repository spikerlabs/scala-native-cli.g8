lazy val root = (project in file(".")).settings(
  name := "scala-native-cli.g8",
  Test / test := {
    val _ = (Test / g8Test).toTask("").value
  },
  scriptedLaunchOpts ++= List(
    "-Xms1024m",
    "-Xmx1024m",
    "-XX:ReservedCodeCacheSize=128m",
    "-Xss2m",
    "-Dfile.encoding=UTF-8"
  ),
  scriptedBufferLog := false,
  logLevel := Level.Info,
  resolvers += Resolver.url(
    "typesafe",
    url("https://repo.typesafe.com/typesafe/ivy-releases/")
  )(Resolver.ivyStylePatterns)
)
