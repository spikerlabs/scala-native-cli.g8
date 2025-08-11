scalaVersion := "3.3.6" // LTS

enablePlugins(ScalaNativePlugin)

logLevel := Level.Info

import scala.scalanative.build._

nativeConfig ~= { c =>
  c.withLTO(LTO.none)
    .withMode(Mode.debug)
    .withGC(GC.commix)
}

mainClass / run := Some("Cli")

name := "example-cli"

libraryDependencies += "org.scalatest" %%% "scalatest" % "3.2.19" % Test
// command parsing
libraryDependencies += "com.indoorvivants" %%% "decline-derive" % "0.3.1"
// command completion
libraryDependencies += "com.indoorvivants" %%% "decline-completion" % "0.1.0"
// TODO config parsing
libraryDependencies += "com.indoorvivants" %%% "toml" % "0.3.0"
// TODO cli prompts
libraryDependencies += "tech.neander" %%% "cue4s" % "0.0.9"
