package infrastructure

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should._

class StringifierSpec extends AnyFlatSpec with Matchers {

  val stringifier = new Stringifier()

  "Stringifier" should "keep string value as is" in {
    stringifier.str("value") shouldBe "value"
  }

  it should "capitalise headers" in {
    stringifier.head("name") shouldBe "Name"
    stringifier.head("boatName") shouldBe "Boat Name"
    stringifier.head("boat_name") shouldBe "Boat Name"
    stringifier.head("boat___name") shouldBe "Boat Name"
    stringifier.head("   some name ____") shouldBe "Some Name"
  }

  it should "return a value as a string" in {
    case class Boat(name: String, length: Int)
    stringifier.str(true) shouldBe "true"
    stringifier.str(12) shouldBe "12"
    stringifier.str(1.7) shouldBe "1.7"
    stringifier.str(Boat("Evergreen", 550)) shouldBe "Boat(Evergreen,550)"
  }

  it should "drop the wrapper if type contains only single value" in {
    case class Boat(name: String)
    val boat = Boat("Evergreen")
    stringifier.str(boat) shouldBe "Evergreen"
  }

  it should "omit some and none" in {
    stringifier.str(Some("value")) shouldBe "value"
    stringifier.str(None) shouldBe ""
  }

  it should "treat enum types like it does headers" in {
    enum Number(label: String) {
      case One extends Number("One")
      case Three extends Number("Three")
      case TwentySeven extends Number("Twenty Seven")
    }
    stringifier.str(Number.One) shouldBe "One"
    stringifier.str(Number.Three) shouldBe "Three"
    stringifier.str(Number.TwentySeven) shouldBe "Twenty Seven"
  }
}
