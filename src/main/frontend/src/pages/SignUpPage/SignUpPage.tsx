import css from "./SignUpPage.module.css";
import {NavLink} from "react-router-dom";
import {SignUpForm} from "../../components/SignUpForm/SignUpForm";

export const SignUpPage = () => {
    return (
        <div className={css.loginWrap}>
            <h2>Register</h2>
            <SignUpForm />
            <NavLink className={css.link} to={`/sign-in`}>
                Already registered? Login
            </NavLink>
        </div>
    );
};
