import React from 'react';
import css from './App.module.css';
import { Routes, Route } from "react-router-dom";
import {Layout} from "./hoc/Layout/Layout";
import {WelcomePage} from "./pages/WelcomePage/WelcomePage";
import {SignInPage} from "./pages/SignInPage/SignInPage";
import {SignUpPage} from "./pages/SignUpPage/SignUpPage";
import {NotFoundPage} from "./pages/NotFoundPage/NotFoundPage";
import {HomePage} from "./pages/HomePage/HomePage";

function App() {
  return (
      <Layout>
          <Routes>
              <Route path={`/`} element={<WelcomePage />} />
              <Route path={`/sign-in`} element={<SignInPage />} />
              <Route path={`/sign-up`} element={<SignUpPage />} />
              <Route path={`/home`} element={<HomePage />} />
              <Route path={`*`} element={<NotFoundPage />} />
          </Routes>
      </Layout>
  );
}

export default App;
