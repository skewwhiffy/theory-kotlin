import {AbcBox} from "../notation/AbcBox";

export const Test = () => {
  return <>
    <AbcBox abc={`
  X:1
  K:E
  DD AA|BBA2|
  `} width={300}/>
    <p className="w-1/2">Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor
      invidunt ut labore et
      dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet
      clita kasd gubergren, no sea takimata</p>
  </>
}