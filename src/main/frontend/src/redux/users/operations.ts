import {createAsyncThunk} from "@reduxjs/toolkit";
import {$api} from "../../http";
import {IUser} from "../../models/IUser";
import {IPageable} from "../../models/IPageable";


export const fetchUsers = createAsyncThunk("users/fetchUsers",  async (_, thunkAPI) => {
    try {
        const res = await $api.get<IPageable<IUser>>("/test");
        return res.data;
    } catch (error : any) {
        //{error.message, error.code}
        return thunkAPI.rejectWithValue(error.message);
    }
});
