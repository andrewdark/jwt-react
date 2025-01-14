import {IUser} from "../../models/IUser";
import {ActionReducerMapBuilder, createSlice, PayloadAction} from "@reduxjs/toolkit";
import {logIn, logOut, refreshUser, register} from "./operations";
import {ISignInResponse} from "../../models/auth/ISignInResponse";
import {ISignUpResponse} from "../../models/auth/ISignUpResponse";

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
    extraReducers: (builder:ActionReducerMapBuilder<AuthState>) => {
        builder
            .addCase(register.fulfilled, (state: AuthState, action:PayloadAction<ISignUpResponse>) => {
                state.userId =action.payload.userId;
                state.user = action.payload.user;
                state.accessToken = action.payload.accessToken;
                state.isLoggedIn = true;
            })
            .addCase(logIn.fulfilled, (state: AuthState, action:PayloadAction<ISignInResponse>) => {
                state.userId = action.payload.userId;
                state.user = action.payload.user;
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
                state.user = action.payload.user;
                state.isLoggedIn = true;
                state.isRefreshing = false;
            })
            .addCase(refreshUser.rejected, (state: AuthState) => {
                state.isRefreshing = false;
            });
    },

})

export const authReducer = authSlice.reducer;
