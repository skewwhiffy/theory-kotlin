import {Test} from "../route/Test";
import {Home} from "../route/Home";
import {NotFound} from "../route/NotFound";
import {Clef} from "../route/Clef";
import {JSX} from "react";

export type PropsWithRoute = {
  route: Route
}

type Route = {
  menuText?: string
  path: string
  element: JSX.Element
  link?: string
}

export const routes: Route[] = [{
  path: "/test",
  element: <Test/>
}, {
  path: "/",
  element: <Home/>,
  menuText: "Home"
}, {
  path: "/clef/:type",
  link: "/clef/notes",
  element: <Clef/>,
  menuText: "Clef"
}, {
  path: "*",
  element: <NotFound/>
}]