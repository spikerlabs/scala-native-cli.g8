scalaVersion := "$scala_version$"

enablePlugins(ScalaNativePlugin)

logLevel := Level.Info

import scala.scalanative.build._

nativeConfig ~= { c =>
  c.withLTO(LTO.none)
    .withMode(Mode.debug)
    .withGC(GC.commix)
}

mainClass / run := Some("Cli")

name := "$name$"

libraryDependencies += "org.scalatest" %%% "scalatest" % "$scalatest_version$" % Test
// command parsing
libraryDependencies += "com.indoorvivants" %%% "decline-derive" % "$decline_derive_version$"
// command completion
libraryDependencies += "com.indoorvivants" %%% "decline-completion" % "$decline_completion_version$"
// TODO config parsing
libraryDependencies += "com.indoorvivants" %%% "toml" % "$toml_version$"
// TODO cli prompts
libraryDependencies += "tech.neander" %%% "cue4s" % "$cue4s_version$"
