package com.kastrull.gui.swing.builder

import javax.swing.JPanel
import java.awt.Graphics
import java.awt.Color
import java.awt.GradientPaint
import java.awt.Graphics2D
import java.awt.RenderingHints
import com.kastrull.core.Sketch
import java.awt.Dimension
import com.kastrull.core.Point

object CenterView {

  def apply(sketch: Sketch): JPanel = new JPanel() {

    override def paintComponent(g: Graphics) {
      paintBackground(g)
      paintLines(g)
    }

    def paintBackground(g: java.awt.Graphics) = {
      to2D(g).setPaint(gradientPaint)
      val s: Dimension = this.getSize()
      g.fillRect(0, 0, s.width, s.height);
    }

    def gradientPaint = {
      val topColor = new Color(176, 237, 253)
      val bottomColor = new Color(4, 118, 147)
      new GradientPaint(0, 0, topColor, 0, 400, bottomColor)
    }

    def paintLines(g: java.awt.Graphics) = {
      g.setColor(Color.BLACK)
      to2D(g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

      sketch.lines.foreach {
        l =>
          g.drawLine(l.a.x, l.a.y, l.b.x, l.b.y)
          drawDot(g, l.a)
          drawDot(g, l.b)
      }
    }

    def drawDot(g: Graphics, a: Point) = {
      val r = 2
      val r2 = r * 2
      g.fillOval(a.x - r, a.y - r, r2, r2)
    }

    def to2D(g: Graphics) = g match {
      case g2: Graphics2D => g2
      case _              => throw new ClassCastException
    }
  }
}
