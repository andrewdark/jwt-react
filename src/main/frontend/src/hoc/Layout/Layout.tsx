import css from "./Layout.module.css";
import {useLocation} from "react-router-dom";
import {Suspense} from "react";
import {Header} from "../../components/Header/Header";

export const Layout = (props: any) => {

    const location = useLocation();
    const hideHeader = !["/", "/sign-up", "/sign-in", "/home"].includes(
        location.pathname
    );
    return (
        <Suspense>
            {!hideHeader && <Header/>}
            {props.children}
        </Suspense>
    );
};
