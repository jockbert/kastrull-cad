package com.kastrull.gui.swing

import java.awt.event.MouseWheelEvent
import java.awt.event.MouseWheelListener

import com.kastrull.core.Sketch
import com.kastrull.gui.swing.SwingAdaptations.NicePanel
import com.kastrull.gui.swing.builder.CenterView
import com.kastrull.gui.swing.builder.Frame

import javax.swing.JLabel
import javax.swing.JPanel

object Main extends App {

  val sketch: Sketch = Sketch()

  val frame = Frame(buttonPanel, centerView)

  def buttonPanel = new JLabel("Buttons");

  def centerView: JPanel = CenterView(sketch).
    listenTo(ToCoreAdapter.toolAdapter(sketch)).
    listenTo { _ => frame.repaint() }
}
