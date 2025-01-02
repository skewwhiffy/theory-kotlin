import {Vex} from "vexflow";
import {useEffect, useId, useState} from "react";

const {Factory} = Vex.Flow;

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
    const vf = new Factory({
      renderer: {elementId, width: 500, height: 200},
    });

    const score = vf.EasyScore();
    const system = vf.System();

    system
      .addStave({
        voices: [
          score.voice(score.notes('C#5/q, B4, A4, G#4', {stem: 'up'})),
          score.voice(score.notes('C#4/h, C#4', {stem: 'down'})),
        ],
      })
      .addClef('treble')
      .addTimeSignature('4/4');
    vf.draw();
  })

  return <div id={elementId}/>;
}