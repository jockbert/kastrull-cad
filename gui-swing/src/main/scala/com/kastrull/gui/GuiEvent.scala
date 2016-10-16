package com.kastrull.gui

import javax.swing.JPanel
import java.awt.event.MouseListener
import java.awt.event.MouseMotionListener
import java.awt.event.MouseEvent

import GuiEvent.Point
import java.awt.event.MouseWheelListener
import java.awt.event.MouseWheelEvent

object GuiEvent {
  type Point = (Int, Int)

  import scala.language.implicitConversions

  implicit def toGuiPoint(p: java.awt.Point): Point = (p.x, p.y)

  def listenTo(panel: JPanel)(pipe: GuiEvent => Unit): JPanel = {
    panel.addMouseListener(new MouseListener() {
      def mouseClicked(me: MouseEvent): Unit = ()
      def mouseEntered(me: MouseEvent): Unit = ()
      def mouseExited(me: MouseEvent): Unit = ()
      def mousePressed(me: MouseEvent): Unit = pipe(Pressed(me.getPoint, me.getButton))
      def mouseReleased(me: MouseEvent): Unit = pipe(Released(me.getPoint, me.getButton))
    })

    panel.addMouseMotionListener(new MouseMotionListener() {
      def mouseDragged(me: MouseEvent): Unit = pipe(Dragged(me.getPoint))
      def mouseMoved(me: MouseEvent): Unit = ()
    })

    panel.addMouseWheelListener(new MouseWheelListener() {
      def mouseWheelMoved(me: MouseWheelEvent): Unit = pipe(Scroll(me.getPoint))
    })

    panel
  }
}

sealed trait GuiEvent {
  def point: Point
}

case class Dragged(point: Point) extends GuiEvent

case class Pressed(point: Point, button: Int) extends GuiEvent

case class Released(point: Point, button: Int) extends GuiEvent

case class Scroll(point: Point) extends GuiEvent
