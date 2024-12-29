import React from 'react';
import {BrowserRouter} from "react-router";
import {Routing} from './scaffold/Routing';

const App = () =>
  <BrowserRouter>
    <Routing/>
  </BrowserRouter>

export default App;
