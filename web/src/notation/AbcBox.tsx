import {useEffect, useId} from "react";
// noinspection SpellCheckingInspection
import * as abcjs from "abcjs";

type Props = {
  abc: string,
  width?: number
}

export const AbcBox = ({abc, width}: Props) => {
  const id = useId();
  let rendered = false

  useEffect(() => {
    if (rendered) {
      return;
    }
    const div = document.getElementById(id);
    if (!div) {
      return
    }
    div.textContent = null
    rendered = true;
    // noinspection SpellCheckingInspection
    abcjs.renderAbc(id, abc, {
      staffwidth: width
    });
  })

  return <div className="w-100" id={id}/>
}