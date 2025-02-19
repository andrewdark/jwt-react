import axios from "axios";
import {AuthResponse} from "../models/auth/AuthResponse";
import {AppStore} from "../redux/store";
import {refreshToken} from "../redux/auth/operations";

export const BASE_URL = "http://localhost:8080/api";

export const $api = axios.create({
    withCredentials: true, //for cookies
    baseURL: BASE_URL
});


export const myInter = (store: AppStore) => {
    $api.interceptors.request.use((config) => {
        let token = store.getState().authReducer.accessToken;
        config.headers.Authorization = `Bearer ${token}`;
        return config;
    });

    $api.interceptors.response.use((config) => {

        console.log("interceptors.response STATUS: OK");
        return config;
    }, async (error) => {
        console.log("interceptors.response STATUS: BAD");

        const originalRequest = error.config;
        if (error.response.status == 401 && error.config && !error.config._isRetry) {
            originalRequest._isRetry = true;
            try {
               await store.dispatch(refreshToken());
                return $api.request(originalRequest);
            } catch (e) {
                console.log('НЕ АВТОРИЗОВАН')
            }
        }
        throw error;
    })
}




