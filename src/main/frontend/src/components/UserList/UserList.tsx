import React from 'react';
import {fetchUsers} from "../../redux/users/operations";
import {useAppDispatch, useAppSelector} from "../../hooks/redux";

const UserList = () => {
    const users = useAppSelector(state => state.userReducer.users);

    const dispatch = useAppDispatch();

    const clickHandler = () => {
        dispatch(fetchUsers());
        console.log("LOU: ", users);
    }

    return (
        <div>
            <button onClick={clickHandler}>Fetch user</button>
            <ul>
                {
                    users.map(el => {
                        return <li
                            key={el.userId}>{el.userId + " | " + el.firstName + " | " + el.lastName + " | " + el.email}</li>
                    })}

            </ul>
        </div>
    );
};

export default UserList;
