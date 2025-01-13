import {IUser} from "../../models/IUser";
import {createSlice} from "@reduxjs/toolkit";

interface AuthState{
    user: IUser,
    accessToken: null | string,
    isLoggedIn: boolean,
    isRefreshing: boolean,
    isLoading: boolean,
    error: null | string,
}
const initialState:AuthState = {
    user: {
        id: 0,
        userName: '',
        email: ''

    },
    accessToken: null,
    isLoggedIn: false,
    isRefreshing: false,
    isLoading: false,
    error: null,
};
export const authSlice = createSlice({
    name: "auth",
    initialState,
    reducers: {}

})

export const userReducer = authSlice.reducer;
