import css from "./Header.module.css";
import {useSelector} from "react-redux";
import {selectIsLoggedIn} from "../../redux/auth/selectors";
import {AuthMenu} from "../AuthMenu/AuthMenu";
import {AuthNav} from "../AuthNav/AuthNav";

export const Header = () => {
    const isLoggedIn = useSelector(selectIsLoggedIn);
    return (
        <div className={css.container}>
            <header className={css.header}>
                <div>Logo</div>
                <div>
                    {isLoggedIn ? <AuthMenu/> : <AuthNav/>}
                </div>
            </header>
        </div>
    );
};
