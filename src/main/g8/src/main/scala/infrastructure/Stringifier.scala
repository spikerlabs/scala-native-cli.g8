package infrastructure

class Stringifier() {
  def head(value: String): String =
    value
      .replaceAll("\\\\_", " ")
      .replaceAll("([A-Z])", " \$1")
      .replaceAll(" +", " ")
      .split(" ")
      .filter(_.nonEmpty)
      .map(_.capitalize)
      .mkString(" ")
  def str[A](value: A): String = value match {
    case None      => ""
    case a: String => a
    case a: Product =>
      if (a.productElementNames.isEmpty)
        head(a.productPrefix)
      else if (a.productElementNames.size == 1)
        str(a.productIterator.toList.head)
      else a.toString
    case a => a.toString
  }
}
