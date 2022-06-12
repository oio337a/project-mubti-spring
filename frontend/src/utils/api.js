import axios from 'axios';
import { useSelector } from "react-redux";
import { useDispatch } from "react-redux"
import { login } from "../reducers/userReducer";

const instance = axios.create({
    baseURL: "http://localhost:8080"
})

instance.interceptors.request.use((config) => {
    const dispatch = useDispatch();
    const token = useSelector((state) => state.user.value);
    let timeNow = new Date;

    if (token.accessToken){
        console.log("API IF");
        if (token.expiryTime < timeNow.getMilliseconds() + 30000){
            axios.get("/refresh", {headers: {Authorization: `Bearer ${token.accessToken}`}})
                .then((res) => {
                dispatch(login({
                    accessToken:res.accessToken,
                    expiryTime:res.expiryTime}));

            })
        }
    }
    else{
        console.log("API ELSE");
    }

    return config;
})

export default instance;