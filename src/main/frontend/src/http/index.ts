import axios from "axios";

export const BASE_URL="http://localhost:8080/api";

const $api = axios.create({
    withCredentials: true, //for cookies
    baseURL: BASE_URL
});

$api.interceptors.request.use((config)=>{
    return config;
});

