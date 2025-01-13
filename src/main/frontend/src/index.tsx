import React from 'react';
import "modern-normalize";
import './index.css';
import App from './App';
import { createRoot } from "react-dom/client";
import {HashRouter} from "react-router-dom";
import {setupStore} from "./redux/store";
import {Provider} from "react-redux"; //BrowserRouter

const store = setupStore();
const container = document.getElementById("root");
const app = ( <Provider store={store}><HashRouter><App /></HashRouter></Provider>);

const root = createRoot(container!); // createRoot(container!) if you use TypeScript
root.render(app);

