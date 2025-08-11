import decline_derive.*
import example.*
import infrastructure.*
import infrastructure.Autocompletion.*

@Name("example-cli") enum CliCommands derives CommandApplication {
  @Help("example greeting by list of names") @Name("hello") case Hello(@Positional("name") maybeNames: Option[List[String]])
}

@main def Cli(args: String*): Unit = {

  val formatter = new TableFormatter(new Stringifier())

  CommandApplication.parseOrExit[CliCommands | Autocompletion.Setup](args) match {

    case CliCommands.Hello(listOfValues) =>
      val names = listOfValues match {
        case None | Some(Nil) => TheName("World") :: Nil
        case Some(names)      => names.map(name => TheName(name.capitalize))
      }
      System.out.println("Hello:")
      System.out.println(formatter.format(names))

    case Autocompletion.Setup(completions) => System.out.print(completions)

  }
}
