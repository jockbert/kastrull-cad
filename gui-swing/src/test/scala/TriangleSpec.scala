
import org.specs2._
import org.scalacheck.Gen
import org.scalacheck.Arbitrary

class TriangelSpec extends Specification with ScalaCheck {

  def is = s2"""
    Specification for a triangle
      (A) A triangle side must be positive and non-zero
         (A1) A side is valid when > 0 $a1
         (A2) A side is invalid when <= 0 $a2
      (B) A side can not be longer or equal than the sum of
      the two other sides $b
      (C) A triangle is only valid vhen (A) and (B) is
      applicable for all sides. $c

      The cirumference of a triangel is larger than 3 times the shortest side  $e1
      The cirumference of a triangel is smaller than 3 times the longest side  $e2
      The circumference of the sides 3, 4 and 5 is 12 $e3

      The area of a triangel is smaller than the half square of the middle length side $f1
      The area of the sides 3, 4 and 5 is 6 $f2
      The area of the sides 40, 30 and 50 is 600 $f3
    """

  def a1 = prop { (side: Int) => side > 0 ==> Triangle.isPositive(side) }
  def a2 = prop { (side: Int) => side <= 0 ==> !Triangle.isPositive(side) }

  def b = prop { (a: Int, b: Int, c: Int) =>
    Triangle.isNotToLong(a)(b, c) mustEqual
      (b.toLong + c.toLong - a.toLong) > 0
  }

  def c = prop { (a: Int, b: Int, c: Int) =>
    import Triangle._
    Triangle.isValid(a, b, c) mustEqual (
      isPositive(a) &&
      isPositive(b) &&
      isPositive(c) &&
      isNotToLong(a)(b, c) &&
      isNotToLong(b)(a, c) &&
      isNotToLong(c)(b, a))
  }

  implicit val orderedSidesTriangle = Arbitrary {
    def validSide(n: Int) = for (x <- Gen.choose(n, Integer.MAX_VALUE)) yield x

    for {
      a <- validSide(1)
      b <- validSide(a)
      c <- validSide(b)
      if Triangle.isValid(a, b, c)
    } yield Triangle(a, b, c)
  }

  def e1 = prop { (t: Triangle) => t.circumference() >= 3 * t.a.toLong }
  def e2 = prop { (t: Triangle) => t.circumference() <= 3 * t.c.toLong }
  def e3 = Triangle(4, 3, 5).circumference() must_== 12

  def f1 = prop { (t: Triangle) => t.area() <= (t.b.toDouble * t.b) / 2 }
  def f2 = Triangle(3, 4, 5).area() must_== 6
  def f3 = Triangle(40, 30, 50).area() must_== 600
}

