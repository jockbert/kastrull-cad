package com.kastrull.core

import java.util.ArrayList

case class Point(x: Int, y: Int) {

  def sub(other: Point) = Point(x - other.x, y - other.y)
  def isOrigo() = x == 0 && y == 0
}

case class Line(a: Point, b: Point)

case class Sketch() {

  var lines: List[Line] = Nil;

  def addLine(l: Line): Sketch = {
    lines = l :: lines
    this
  }
}
