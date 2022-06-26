import axios from 'axios';
import { useSelector } from "react-redux";
import {useEffect} from "react";
import useReIssueToken from "./useReIssueToken";

const BASE_URL = "http://localhost:8080";

const instance = axios.create({
    baseURL: BASE_URL,
    withCredentials: true
})

const Interceptor = ({children}) => {
    const token = useSelector((state) => state.user.userReducer.value);

    useReIssueToken();

    console.log("API", token);

    useEffect(() => {
        const requestInterceptor = instance.interceptors.request.use(
            function (config) {
                try {
                    console.log("!!!!!!!!!!!!!!!!!!!!config", config)
                    config.headers.Authorization = `Bearer ${token.accessToken}`;

                    return config;
                }
                catch (err){
                    console.error("interceptors.request.config", err.message);
                }
            },
            function (error) {
                try {
                    console.log("API ERROR", error);

                    return Promise.reject(error);
                }
                catch (err){
                    console.error("interceptors.request.error", err.message);
                }
            }
        );

        const responseInterceptor = instance.interceptors.response.use(
            function(config) {
                try {
                    console.log("send response well");

                    return config;
                }
                catch (err){
                    console.error("interceptors.response.config", err.message);
                }
            },
            function (error){
                try {
                    console.log(error);
                    const status = error.response.status;

                    switch (status) {
                        case 401 : {
                            alert("로그인해야 이용 가능");
                            window.location.href("http://localhost:8080/login");
                            break;
                        }
                        default : {
                            console.log("interceptors.response.error", status);
                        }
                    }
                    return Promise.reject(error);
                }
                catch (err){
                    console.error("interceptors.response.error", err.message);
                }
            }
        )

        return () => {
            instance.interceptors.request.eject(requestInterceptor);
            instance.interceptors.response.eject(responseInterceptor);
        }
    }, [])
    return children;
}

export default instance;
export {Interceptor};
