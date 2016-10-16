package com.kastrull.gui.swing

import javax.swing.JFrame
import javax.swing.JPanel
import com.kastrull.core.Point
import com.kastrull.gui.GuiEvent
import scala.language.implicitConversions

object SwingAdaptations {
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

  import scala.language.implicitConversions
  implicit def toCorePoint(p: (Int, Int)): Point = Point(p._1, p._2)
}
