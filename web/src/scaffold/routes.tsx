import {Test} from "../route/Test";
import {Home} from "../route/Home";
import {NotFound} from "../route/NotFound";
import {Interval} from "../route/Interval";

export const routes = [{
  path: "/test",
  element: <Test/>
}, {
  path: "/",
  element: <Home/>,
  menuText: "Home"
}, {
  path: "/interval",
  element: <Interval/>,
  menuText: "Interval"
}, {
  path: "*",
  element: <NotFound/>
}]