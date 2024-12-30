import {Route, Routes} from "react-router";
import {Test} from "../route/Test";
import {Home} from "../route/Home";
import {NotFound} from "../route/NotFound";

export const Routing = () =>
  <Routes>
    <Route path="/test" element={<Test/>}/>
    <Route path="/" element={<Home/>}/>
    <Route path="*" element={<NotFound/>}/>
  </Routes>