import {useEffect, useId, useState} from "react";
import * as abcjs from "abcjs";

export const TestNotationAbc = () => {
  const id = useId();
  const [rendered, setRendered] = useState(false)

  useEffect(() => {
    if (rendered) {
      return;
    }
    const div = document.getElementById(id);
    if (!div) {
      return
    }
    div.textContent = null
    setRendered(true)
    abcjs.renderAbc(id, "X:1\nK:D\nDD AA|BBA2|\n", {
      staffwidth: 300
    });
  })

  return <>
    <h1>ABC</h1>
    <div className="w-100" id={id}/>
  </>
}