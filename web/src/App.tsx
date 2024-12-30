import React from 'react';
import {BrowserRouter} from "react-router";
import {Routing} from './scaffold/Routing';
import {TopMenu} from "./scaffold/TopMenu";

const App = () =>
  <BrowserRouter>
    <TopMenu/>
    <Routing/>
  </BrowserRouter>

export default App;
