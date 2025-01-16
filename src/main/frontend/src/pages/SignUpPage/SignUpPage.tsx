import css from "./SignUpPage.module.css";
import {NavLink} from "react-router-dom";
import {SignUpForm} from "../../components/SignUpForm/SignUpForm";
import {useAppDispatch} from "../../hooks/redux";
import {logIn} from "../../redux/auth/operations";
import {ISignUpRequest} from "../../models/auth/ISignUpRequest";

export const SignUpPage = () => {
    const dispatch = useAppDispatch();

    const loginHandler = (credentials: ISignUpRequest) => {
        dispatch(logIn(credentials));
    }
    return (
        <div className={css.loginWrap}>
            <h2>Register</h2>
            <SignUpForm loginHandler={loginHandler}/>
            <NavLink className={css.link} to={`/sign-in`}>
                Already registered? Login
            </NavLink>
        </div>
    );
};
