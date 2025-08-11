# WORK IN PROGRESS - NOT A GITER TEMPLATE YET

# Scala Native CLI Template

Scala Native CLI Template, with all the usual dependencies included and configured already.
This is a  [Giter8][g8] template, which integrates beatifully with [sbt][sbt].

## Requirements

You have to have latest `scala`, `sbt` and `llvm` available on your system.
On a mac you can get it via:
```bash
brew install scala
brew install sbt
brew install llvm
```

## How to set up

It will install in sub directory (using the project name you have chosen).

The template will ask number of questions about desired versions of things, normally you should be fine to use defaults.

```bash
sbt new spikerlabs/scala-native-cli.g8
```

## How to use generated project

Infrastructure code is reusable and suggested structure is scalable towards complex real use cases.

Test, Build and Run:
```bash
sbt clean test compile nativeLink
./target/scala-3.3.6/example-cli hello --help
```

[g8]: https://www.foundweekends.org/giter8/
[sbt]: https://www.scala-sbt.org
