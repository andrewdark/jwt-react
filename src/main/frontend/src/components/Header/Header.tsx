import css from "./Header.module.css";

export const Header = () => {
    return (
        <div className={css.container}>
            <header className={css.header}>
                <div>Logo</div>
                <div>User</div>
            </header>
        </div>
    );
};
