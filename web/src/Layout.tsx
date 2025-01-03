import {PropsWithChildren} from "react";

export const Layout = ({children}: PropsWithChildren) => {
  return <div className="flex justify-center mt-4">
    <div className="flex gap-2 w-1/2">{children}</div>
  </div>
}