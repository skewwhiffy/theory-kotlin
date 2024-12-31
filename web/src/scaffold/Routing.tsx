import {Route, Routes} from "react-router";
import {routes} from "./routes";

export const Routing = () =>
  <Routes>
    {routes.map(({path, element}) =>
      <Route key={path} path={path} element={element}/>
    )}
  </Routes>