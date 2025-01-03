import {TestNotationAbc} from "../notation/TestNotationAbc";

export const Test = () => {
  return <div className="flex-col justify-items-center justify-center">
    <div className="flex gap-2 w-1/2">
      <TestNotationAbc/>
      <p className="w-1/2">Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et
        dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet
        clita kasd gubergren, no sea takimata</p>
    </div>
  </div>
}