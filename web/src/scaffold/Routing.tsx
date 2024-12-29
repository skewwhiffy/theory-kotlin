import {Route, Routes} from "react-router";
import {Test} from "../route/Test";
import {NotFound} from "../route/NotFound";

export const Routing = () =>
  <Routes>
    <Route path="/test" element={<Test/>}/>
  </Routes>