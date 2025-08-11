# Scala Native CLI Template

Scala Native CLI Template, with all the usual dependencies included and configured already.

## Requirements

You have to have latest `scala`, `sbt` and `llvm` available on your system.
On a mac you can get it via:
```bash
brew install scala
brew install sbt
brew install llvm
```

## How to use template

Infrastructure code is reusable and suggested structure is scalable towards complex real use cases.

Test, Build and Run:
```bash
sbt clean test compile nativeLink
./target/$scala_version$/$name$ hello --help
```
