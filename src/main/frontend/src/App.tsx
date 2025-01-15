import React, {useEffect} from 'react';
import css from './App.module.css';
import {Routes, Route} from "react-router-dom";
import {Layout} from "./hoc/Layout/Layout";
import {WelcomePage} from "./pages/WelcomePage/WelcomePage";
import {SignInPage} from "./pages/SignInPage/SignInPage";
import {SignUpPage} from "./pages/SignUpPage/SignUpPage";
import {NotFoundPage} from "./pages/NotFoundPage/NotFoundPage";
import {HomePage} from "./pages/HomePage/HomePage";
import {RestrictedRoute} from "./hoc/RestrictedRoute/RestrictedRoute";
import {PrivateRoute} from "./hoc/PrivateRoute/PrivateRoute";
import {useAppDispatch} from "./hooks/redux";
import {refreshUser} from "./redux/auth/operations";
import {myInter} from "./http";
import {store} from "./redux/store";

function App() {
    const dispatch = useAppDispatch();
    useEffect(() => {
        myInter(store);
        dispatch(refreshUser());
    }, [dispatch]);
    return (
        <Layout>
            <Routes>
                <Route path={`/`} element={<WelcomePage/>}/>
                <Route path={`/sign-in`} element={
                    <RestrictedRoute redirectTo="/home" component={<SignInPage/>}/>}/>
                <Route path={`/sign-up`} element={
                    <RestrictedRoute redirectTo="/home" component={<SignUpPage/>}/>}/>
                <Route path={`/home`} element={
                    <PrivateRoute redirectTo="/sign-in" component={<HomePage/>}/>}/>
                <Route path={`*`} element={<NotFoundPage/>}/>
            </Routes>
        </Layout>
    );
}

export default App;
