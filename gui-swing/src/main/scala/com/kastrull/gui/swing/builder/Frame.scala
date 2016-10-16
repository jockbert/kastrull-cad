package com.kastrull.gui.swing.builder

import javax.swing.JFrame
import com.kastrull.gui.swing.SwingAdaptations._
import java.awt.Dimension
import java.awt.BorderLayout
import javax.swing.JPanel
import javax.swing.JComponent

object Frame {
  def apply(top: JComponent, center: JComponent) = new JFrame("Kastrull") and
    (_.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)) and
    (_.setMinimumSize(new Dimension(500, 300))) and
    (_.setLocationRelativeTo(null)) and
    (_.getContentPane().setLayout(new BorderLayout())) and
    (_.getContentPane().add(top, BorderLayout.NORTH)) and
    (_.getContentPane().add(center, BorderLayout.CENTER)) and
    (_.setFocusable(true)) and
    (_.pack()) and
    (_.setVisible(true))
}
