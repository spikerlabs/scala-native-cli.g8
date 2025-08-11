package infrastructure

class TableFormatter(stringifier: Stringifier) extends Formatter {
  def format[A <: Product](list: List[A]): String = {
    list match {
      case Nil => ""
      case items =>
        val header = "" :: items.head.productElementNames.toList.map(stringifier.head)
        val data = items.map(_.productIterator.toList).zipWithIndex.map { case (list, index) =>
          (index + 1).toString + "." :: list.map(stringifier.str)
        }
        val lengths = (header :: data).map(_.map(_.length))
        val longest = lengths.tail.foldLeft(lengths.head) { case (currentLongest, nextLength) =>
          currentLongest.zip(nextLength).map { case (longestLength, currentLength) =>
            Math.max(longestLength, currentLength)
          }
        }
        val headerRow = header.zip(longest).map(a => a._1.padTo(a._2, ' '))
        val dataRows = data.map(_.zip(longest).map(a => a._1.padTo(a._2, ' ')))
        val dividerRow = List.fill(header.length)("-").zip(longest).map(a => a._1.padTo(a._2, '-'))
        val table = (headerRow :: dividerRow :: dataRows).map("| " + _.mkString(" | ") + " |").mkString("\n") + "\n"
        return table
    }
  }
}
