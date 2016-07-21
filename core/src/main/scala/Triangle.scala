
object Triangle extends App {
  val t = Triangle(3, 4, 5)

  println("Given a " + t)
  println(" The circumference is " + t.circumference())
  println(" The area is " + t.area())

  def isPositive(side: Int): Boolean =
    side > 0

  def isNotToLong(a: Int)(b: Int, c: Int): Boolean =
    b.toLong + c - a > 0

  def isValid(a: Int, b: Int, c: Int): Boolean = {
    isPositive(a) && isPositive(b) && isPositive(c) && isNotToLong(a)(b, c) && isNotToLong(c)(b, a) && isNotToLong(b)(a, c)
  }
}

case class Triangle(a: Int, b: Int, c: Int) {
  def circumference(): Long = a.toLong + b + c

  def area(): Double = {
    val s = circumference().toDouble / 2
    Math.sqrt(s * (s - a) * (s - b) * (s - c))
  }
}

