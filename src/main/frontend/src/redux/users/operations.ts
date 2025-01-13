import {createAsyncThunk} from "@reduxjs/toolkit";

export const fetchUsers = createAsyncThunk("users/fetchUsers",  async (_, thunkAPI) => {
    try {
        // const authHeader = "Bearer " + thunkAPI.getState().auth.accessToken;
        // const res = await axios.get(URL + "/water", {
        //     headers: {
        //         Authorization: authHeader,
        //     },
        // });
        return null; //res.data;
    } catch (error:any) {
        return thunkAPI.rejectWithValue(error.response.data.data.message);
    }
});
