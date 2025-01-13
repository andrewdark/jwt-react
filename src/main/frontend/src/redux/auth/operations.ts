import {createAsyncThunk} from "@reduxjs/toolkit";
import {$api} from "../../http";
import {RootState} from "../store";
import {ISignUpRequest} from "../../models/ISignUpRequest";
import {ISignInRequest} from "../../models/ISignInRequest";

// Utility to add JWT
const setAuthHeader = (token: string) => {
    $api.defaults.headers.common.Authorization = `Bearer ${token}`;
};

// Utility to remove JWT
const clearAuthHeader = () => {
    $api.defaults.headers.common.Authorization = '';
};

/*
 * POST @ /users/signup
 * body: { name, email, password }
 */
export const register = createAsyncThunk(
    'auth/register',
    async (credentials:ISignUpRequest, thunkAPI) => {
        try {
            const res = await $api.post('/auth/signup', credentials);
            // After successful registration, add the token to the HTTP header
            setAuthHeader(res.data.token);
            return res.data;
        } catch (error: any) {
            return thunkAPI.rejectWithValue(error.message);
        }
    }
);

/*
 * POST @ /users/login
 * body: { email, password }
 */
export const logIn = createAsyncThunk(
    'auth/login',
    async (credentials:ISignInRequest, thunkAPI) => {
        try {
            const res = await $api.post('/auth/signin', credentials);
            // After successful login, add the token to the HTTP header
            setAuthHeader(res.data.token);
            return res.data;
        } catch (error: any) {
            return thunkAPI.rejectWithValue(error.message);
        }
    }
);

/*
 * POST @ /users/logout
 * headers: Authorization: Bearer token
 */
export const logOut = createAsyncThunk('auth/logout', async (_, thunkAPI) => {
    try {
        await $api.post('/auth/logout');
        // After a successful logout, remove the token from the HTTP header
        clearAuthHeader();
    } catch (error: any) {
        return thunkAPI.rejectWithValue(error.message);
    }
});

/*
 * GET @ /users/me
 * headers: Authorization: Bearer token
 */
export const refreshUser = createAsyncThunk(
    'auth/refresh',
    async (_, thunkAPI) => {
        // Reading the token from the state via getState()
        const state: RootState = <RootState>thunkAPI.getState();
        const persistedToken = state.authReducer.accessToken;

        if (persistedToken === null) {
            // If there is no token, exit without performing any request
            return thunkAPI.rejectWithValue('Unable to fetch user');
        }

        try {
            // If there is a token, add it to the HTTP header and perform the request
            setAuthHeader(persistedToken);
            const res = await $api.get('/auth/refresh');
            return res.data;
        } catch (error: any) {
            return thunkAPI.rejectWithValue(error.message);
        }
    }
);
