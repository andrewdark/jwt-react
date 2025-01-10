import React from 'react';
import './index.css';
import App from './App';
import { createRoot } from "react-dom/client";
import {HashRouter} from "react-router-dom"; //BrowserRouter

const container = document.getElementById("root");
const app = (<HashRouter><App /></HashRouter>);

const root = createRoot(container!); // createRoot(container!) if you use TypeScript
root.render(app);

