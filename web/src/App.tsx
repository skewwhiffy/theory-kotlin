import React from 'react';
import {BrowserRouter} from "react-router";
import {Routing} from './scaffold/Routing';
import {TopMenu} from "./scaffold/TopMenu";
import './scaffold/tailwind.css';
import {QueryClient, QueryClientProvider} from "@tanstack/react-query";

const queryClient = new QueryClient()

const App = () =>
  <QueryClientProvider client={queryClient}>
    <BrowserRouter>
      <TopMenu/>
      <Routing/>
    </BrowserRouter>
  </QueryClientProvider>

export default App;
