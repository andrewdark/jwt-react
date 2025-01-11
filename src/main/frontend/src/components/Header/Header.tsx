import css from "./Header.module.css";
import { HiUser } from "react-icons/hi";

export const Header = () => {
    return (
        <div className={css.container}>
            <header className={css.header}>
                <div>Logo</div>
                <div><HiUser className="my-icon" size="24" />User</div>
            </header>
        </div>
    );
};
