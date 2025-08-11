package infrastructure

import com.monovore.decline.{Command, Opts}
import decline_derive.*
import net.andimiller.decline.completion.Completion

object Autocompletion {
  case class Setup(value: String)

  given withCompletions[T](using t: CommandApplication[T]): CommandApplication[T | Setup] = new {

    private val zsh = Command(
      "zsh",
      s"""output autocompletion script for zsh
        |example setup of the cli with autocompletion setup:
        |
        |     \${t.command.name} completion zsh > completion.bash && echo "source \$\$PWD/completion.bash" >> ~/.zshrc
        |
        |you will need make sure the executable is in in \$\$PATH. If it is not and you are building it from source, you can try:
        |
        |     echo "export PATH=\\\\\$\$PATH:\$\$PWD/target/scala-3.3.6" >> ~/.zshrc
        |
        |""".stripMargin
    )(Opts(Setup(Completion.zshBashcompatCompletion(command))))

    private val bash =
      Command(
        "bash",
        "output autocompletion script for bash"
      )(Opts(Setup(Completion.bashCompletion(command))))

    private val completion = Command(
      "completion",
      "output autocompletion scripts for common shells"
    ) {
      Opts.subcommands(zsh, bash)
    }

    private val commands = {
      t.subcommands :+ completion
    }

    override def command: Command[T | Setup] =
      Command[T | Setup](t.command.name, t.command.header)(
        Opts.subcommands(commands.head, commands.tail*)
      )

    override def subcommands: List[Command[T | Setup]] =
      commands

  }
}
