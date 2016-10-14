package com.kastrull.gui.swing

import java.awt.Dimension
import javax.swing.JLabel
import javax.swing.JFrame
import java.awt.BorderLayout
import javax.swing.JPanel
import java.awt.Color
import java.awt.Graphics
import java.awt.Paint
import java.awt.GradientPaint
import java.awt.Graphics2D

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
  }

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

  }
}
