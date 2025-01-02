import {Vex} from "vexflow";
import {useEffect, useId, useState} from "react";

export const TestNotation = () => {
  const elementId = useId()
  const [rendered, setRendered] = useState(false)

  useEffect(() => {
    if (rendered) {
      return;
    }
    const div = document.getElementById(elementId);
    if (!div) {
      return
    }
    div.textContent = null
    setRendered(true)
    const vf = new Vex.Flow.Factory({renderer: {
      elementId: elementId, height: 0, width: 0
    }});
    const score = vf.EasyScore();
    const system = vf.System();
    system.addStave({
      voices: [score.voice(
        score.notes('C#5/q, B4, A4, G#4 '))]
    }).addClef('treble').addTimeSignature('4/4');

    vf.draw();
  })

  return <div id={elementId}/>;
}