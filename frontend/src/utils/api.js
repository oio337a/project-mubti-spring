import axios from 'axios';
import { useSelector, useDispatch } from "react-redux";
import { login } from "../reducers/userReducer";
import {useEffect} from "react";

const BASE_URL = "http://localhost:8080/api/v1";

const instance = axios.create({
    baseURL: BASE_URL,
    withCredentials: true
})

const Interceptor = ({children}) => {
    const dispatch = useDispatch();
    const token = useSelector((state) => state.user.value);

    useEffect(() => {
        console.log("API IF");
        const interceptor = instance.interceptors.request.use(
            function (config) {

                let timeNow = new Date;
                console.log("are you here?");
                console.log(token.expiryTime, timeNow.getTime());

                if (token.expiryTime < timeNow.getTime() + 30000){
                    axios.get("http://localhost:8080/auth/refresh", {headers: {Authorization: `Bearer ${token.accessToken}`}})
                        .then((res) => {
                            dispatch(login({
                                accessToken:res.token,
                                expiryTime:res.expiryTime}));
                        })
                }
                config.headers.Authorization = `Bearer ${token.accessToken}`;

                return config;
            },
            function (error) {
                console.log("API ERROR", error);
                return error;
            }
        );

        //return () => instance.interceptors.request.eject(interceptor);
    }, [])
    return children;
}

export default instance;
export {Interceptor};