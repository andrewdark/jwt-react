import css from "./Header.module.css";
import { HiUser } from "react-icons/hi";
import {NavLink} from "react-router-dom";

export const Header = () => {
    return (
        <div className={css.container}>
            <header className={css.header}>
                <div>Logo</div>
                <div>
                    <HiUser className="my-icon" size="24" />
                    <NavLink className={css.link} to={`/sign-in`}>
                        Login
                    </NavLink>
                    <NavLink className={css.link} to={`/sign-up`}>
                        Register
                    </NavLink>
                </div>
            </header>
        </div>
    );
};
