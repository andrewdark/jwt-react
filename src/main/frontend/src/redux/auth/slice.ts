import {IUser} from "../../models/IUser";
import {createSlice} from "@reduxjs/toolkit";
import {logIn, logOut, refreshUser, register} from "./operations";

interface AuthState {
    userId: null | number,
    user: null | IUser,
    accessToken: null | string,
    isLoggedIn: boolean,
    isRefreshing: boolean,
    isLoading: boolean,
    error: null | string,
}

const initialState: AuthState = {
    userId: null,
    user: null,
    accessToken: null,
    isLoggedIn: false,
    isRefreshing: false,
    isLoading: false,
    error: null,
};
export const authSlice = createSlice({
    name: "auth",
    initialState,
    reducers: {},
    extraReducers: (builder) => {
        builder
            .addCase(register.fulfilled, (state: AuthState, action) => {
                state.user = action.payload.user;
                state.accessToken = action.payload.accessToken;
                state.isLoggedIn = true;
            })
            .addCase(logIn.fulfilled, (state: AuthState, action) => {
                state.userId = action.payload.userId;
                state.user = <IUser>{
                    userId: 0,
                    firstName: '',
                    lastName: '',
                    email: ''
                };
                state.accessToken = action.payload.accessToken;
                state.isLoggedIn = true;
            })
            .addCase(logOut.fulfilled, (state: AuthState) => {
                state.user = initialState.user;
                state.userId = initialState.userId;
                state.accessToken = initialState.accessToken;
                state.isLoggedIn = initialState.isLoggedIn;
                state.isRefreshing = initialState.isRefreshing;
            })
            .addCase(refreshUser.pending, (state: AuthState) => {
                state.isRefreshing = true;
            })
            .addCase(refreshUser.fulfilled, (state: AuthState, action) => {
                state.userId = action.payload.userId;
                state.user = <IUser>{
                    userId: 0,
                    firstName: '',
                    lastName: '',
                    email: ''
                };
                state.isLoggedIn = true;
                state.isRefreshing = false;
            })
            .addCase(refreshUser.rejected, (state: AuthState) => {
                state.isRefreshing = false;
            });
    },

})

export const authReducer = authSlice.reducer;
