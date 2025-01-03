import React from 'react';
import {BrowserRouter} from "react-router";
import {Routing} from './scaffold/Routing';
import {TopMenu} from "./scaffold/TopMenu";
import './scaffold/tailwind.css';
import {QueryClient, QueryClientProvider} from "@tanstack/react-query";
import {Layout} from "./Layout";

const queryClient = new QueryClient()

const App = () =>
  <QueryClientProvider client={queryClient}>
    <BrowserRouter>
      <TopMenu/>
      <Layout>
        <Routing/>
      </Layout>
    </BrowserRouter>
  </QueryClientProvider>

export default App;
