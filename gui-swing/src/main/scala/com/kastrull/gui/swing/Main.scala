package com.kastrull.gui.swing

import java.awt.Dimension
import javax.swing.JLabel
import javax.swing.JFrame

object Main extends App {

  implicit class NiceFrame(jf: JFrame) {
    def and(fn: JFrame => Unit): JFrame = {
      fn(jf)
      jf
    }
  }

  val frame = new JFrame("Swing test 2") and
    (_.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)) and
    (_.setMinimumSize(new Dimension(400, 300))) and
    (_.setName("Swing test")) and
    (_.setLocationRelativeTo(null)) and
    (_.getContentPane().add(new JLabel("Hello World"))) and
    (_.setFocusable(true)) and
    (_.pack()) and
    (_.setVisible(true))
}