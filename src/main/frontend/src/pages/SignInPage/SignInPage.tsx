import  css from './SignInPage.module.css';
import {SignInForm} from "../../components/SignInForm/SignInForm";

export const SignInPage = () => {
    return (
        <div className={css.loginWrap}>
            <h2>Login</h2>
            <SignInForm />
        </div>
    );
};
