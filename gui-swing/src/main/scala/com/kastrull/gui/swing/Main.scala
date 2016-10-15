package com.kastrull.gui.swing

import java.awt.BorderLayout
import java.awt.Color
import java.awt.Dimension
import java.awt.GradientPaint
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.RenderingHints
import java.awt.event.MouseWheelEvent
import java.awt.event.MouseWheelListener

import com.kastrull.core.Line
import com.kastrull.core.{ Point => P }
import com.kastrull.core.Sketch
import com.kastrull.gui.GuiEvent

import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel

object Main extends App {

  implicit class NiceFrame(jf: JFrame) {
    def and(fn: JFrame => Unit): JFrame = {
      fn(jf)
      jf
    }
  }
  implicit class NicePanel(jp: JPanel) {
    def and(fn: JPanel => Unit): JPanel = {
      fn(jp)
      jp
    }

    def listenTo(fn: GuiEvent => Unit) = {
      GuiEvent.listenTo(jp)(fn)
      jp
    }
  }

  val sketch: Sketch = Sketch().addLine(Line(P(50, 10), P(100, 200)))

  val frame = new JFrame("Kastrull") and
    (_.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)) and
    (_.setMinimumSize(new Dimension(400, 300))) and
    (_.setLocationRelativeTo(null)) and
    (_.getContentPane().setLayout(new BorderLayout())) and
    (_.getContentPane().add(buttonPanel, BorderLayout.NORTH)) and
    (_.getContentPane().add(centerView, BorderLayout.CENTER)) and
    (_.setFocusable(true)) and
    (_.pack()) and
    (_.setVisible(true))

  def buttonPanel = new JLabel("Buttons");

  def centerView = new JPanel() {

    override def paintComponent(g: Graphics) {
      to2D(g).setPaint(gradientPaint)
      g.fillRect(0, 0, 400, 400);

      g.setColor(Color.BLACK)
      to2D(g).setRenderingHint(
        RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);

      println(sketch.lines)
      sketch.lines.foreach { l => g.drawLine(l.a.x, l.a.y, l.b.x, l.b.y) }
    }

    def gradientPaint = {
      val topColor = new Color(176, 237, 253)
      val bottomColor = new Color(4, 118, 147)
      new GradientPaint(0, 0, topColor, 0, 400, bottomColor)
    }

    def to2D(g: Graphics) = g match {
      case g2: Graphics2D => g2
      case _              => throw new ClassCastException
    }

  } and (_.addMouseWheelListener(new MouseWheelListener() {
    def mouseWheelMoved(me: MouseWheelEvent): Unit = println(" wheel\t" + me.getPoint + " " + me.getButton())
  })) listenTo {
    case x => println(x)
  }
}
