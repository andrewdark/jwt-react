import {createAsyncThunk} from "@reduxjs/toolkit";
import {$api} from "../../http";
import {IUser} from "../../models/IUser";
import {IPageable} from "../../models/IPageable";


export const fetchUsers = createAsyncThunk("users/fetchUsers",  async (_, thunkAPI) => {
    try {
        //const authHeader = "Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJ0ZXN0QHVrci5uZXQiLCJpYXQiOjE3MzY3NzcyMjQsImV4cCI6MTczNjgzNzIyNH0.7iwNnZaHZCUBPhLf42RgqFzMVrGH8BNaTL4xy07LZpk5584d3QJ1SpAmUh39vQhK";
        const res = await $api.get<IPageable<IUser>>("/test");
        return res.data;
    } catch (error : any) {
        return thunkAPI.rejectWithValue(error.response.data);
    }
});
