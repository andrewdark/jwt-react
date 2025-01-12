import  css from './SignInPage.module.css';
import {SignInForm} from "../../components/SignInForm/SignInForm";
import {NavLink} from "react-router-dom";

export const SignInPage = () => {
    return (
        <div className={css.loginWrap}>
            <h2>Login</h2>
            <SignInForm />
            <NavLink className={css.link} to={`/sign-up`}>
                Don't have an account? Register
            </NavLink>
        </div>
    );
};
