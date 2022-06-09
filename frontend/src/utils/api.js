import axios from 'axios';
import { useSelector } from "react-redux";
import { useDispatch } from "react-redux"
import { login } from "../reducers/userReducer";

const instance = axios.create({
    baseURL: "http://localhost:8080"
})

const dispatch = useDispatch();

instance.interceptors.request.use((config) => {
    const token = useSelector((state) => state.user.value);
    let timeNow = new Date;

    if (token.accessToken){
        if (token.expiryTime < timeNow.getMilliseconds() - 30000){
            axios.get("/refresh", {headers: {Authorization: `Bearer ${token.accessToken}`}})
                .then((res) => {
                dispatch(login({
                    accessToken:res.accessToken,
                    expiryTime:res.expiryTime}));

            })
        }
    }

    return config;
})
