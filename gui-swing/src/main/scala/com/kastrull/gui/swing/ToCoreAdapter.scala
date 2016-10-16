package com.kastrull.gui.swing

import com.kastrull.core.Sketch
import com.kastrull.gui.GuiEvent
import com.kastrull.gui.Released
import com.kastrull.gui.Pressed
import com.kastrull.core.Line
import com.kastrull.core.Point
import com.kastrull.gui.swing.SwingAdaptations.toCorePoint

object ToCoreAdapter {

  def toolAdapter(sketch: Sketch): GuiEvent => Unit = {
    var start: Option[Point] = None
    ge => (ge, start) match {
      case (Released(p, btn), Some(s)) =>
        sketch.addLine(Line(s, p))
        start = None

      case (Pressed(p, btn), _) => start = Some(p)

      case _                    => ()
    }
  }
}
