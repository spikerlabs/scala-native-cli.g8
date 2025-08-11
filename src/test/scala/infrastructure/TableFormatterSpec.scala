package infrastructure

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.*

class TableFormatterSpec extends AnyFlatSpec with Matchers {
  "Table Printer" should "print nothing when list is empty" in {
    new TableFormatter(new Stringifier()).format(Nil) shouldBe ""
  }
  it should "print a single element with one field with headers" in {
    case class Boat(shortName: String)
    val one = Boat("Boaty McBoatface")
    new TableFormatter(new Stringifier()).format(List(one)) shouldBe
      """||    | Short Name       |
         || -- | ---------------- |
         || 1. | Boaty McBoatface |
         |""".stripMargin
  }
  it should "print a couple of elements with numbers, booleans, enums" in {
    enum BoatType {
      case Dingie
      case ContainerShip
    }
    case class Boat(
        name: String,
        length: Int,
        speed: Float,
        operational: Boolean,
        kind: BoatType
    )
    val boats = List(
      Boat("Boaty McBoatface", 5, 10.5, false, BoatType.Dingie),
      Boat("Evergreen", 500, 50.0, true, BoatType.ContainerShip)
    )

    new TableFormatter(new Stringifier()).format(boats) shouldBe
      """||    | Name             | Length | Speed | Operational | Kind           |
         || -- | ---------------- | ------ | ----- | ----------- | -------------- |
         || 1. | Boaty McBoatface | 5      | 10.5  | false       | Dingie         |
         || 2. | Evergreen        | 500    | 50.0  | true        | Container Ship |
         |""".stripMargin
  }
}
