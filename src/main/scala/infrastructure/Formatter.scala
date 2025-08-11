package infrastructure

trait Formatter {
  def format[A <: Product](list: List[A]): String
}
