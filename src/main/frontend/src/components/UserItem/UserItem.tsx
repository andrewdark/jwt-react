import css from './UserItem.module.css';
import React, {FC} from 'react';
import {IUser} from "../../models/IUser";


interface UserItemProps {
    user: IUser;
}

const UserItem: FC<UserItemProps> = ({user}) => {
    return (
        <div key={user.userId} className={css.UserItem}>
            <div>{user.userId}</div>
            <div>{user.firstName}</div>
            <div>{user.lastName}</div>
            <div>{user.email}</div>
        </div>
    );
};

export default UserItem;
