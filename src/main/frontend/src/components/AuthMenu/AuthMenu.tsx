import React, {FC} from 'react';
import css from './AuthMenu.module.css';
import {useAppDispatch} from "../../hooks/redux";
import {logOut} from "../../redux/auth/operations";

interface AuthMenuProps {
}

export const AuthMenu: FC<AuthMenuProps> = (props) => {
    const dispatch = useAppDispatch();
    return (
        <div >
            <p className={css.username}>Welcome, USER</p>
            <button type="button" onClick={() => dispatch(logOut())}>
                Logout
            </button>
        </div>
    );
};
