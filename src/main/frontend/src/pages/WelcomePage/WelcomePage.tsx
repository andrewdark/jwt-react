import {useAppDispatch, useAppSelector} from "../../hooks/redux";
import {useEffect} from "react";
import {fetchUsers} from "../../redux/users/operations";
import {IUser} from "../../models/IUser";
import {userSlice} from "../../redux/users/slice";

export const WelcomePage = () => {
    const users = useAppSelector(state => state.userReducer.users);

    const dispatch = useAppDispatch();

    const clickHandler = () => {
        dispatch(fetchUsers());
        console.log("LOH: ", users);
    }
    return (
        <div>
            <h1>HELLO WORM!</h1>
            <p>This is a Welcome Page!!</p>
            <button onClick={clickHandler}>Fetch user</button>
            <ul>
                {
                    users.map(el => {
                        return <li key={el.userId}>{el.userId + " | " + el.firstName + " | " + el.lastName + " | " + el.email}</li>
                    })}

            </ul>

        </div>
    );
};
