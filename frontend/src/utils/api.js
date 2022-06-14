import axios from 'axios';
import { useSelector, useDispatch } from "react-redux";
import { login } from "../reducers/userReducer";
import {useEffect} from "react";

const BASE_URL = "http://localhost:8080";

const instance = axios.create({
    baseURL: BASE_URL,
    withCredentials: true
})

const Interceptor = ({children}) => {
    const dispatch = useDispatch();
    const token = useSelector((state) => state.user.value);

    let timeNow = new Date;

    useEffect(() => {

        instance.interceptors.request.use(
            function (config) {

                let timeNow = new Date;
                console.log(token.expiryTime, timeNow.getTime());

                if (token.expiryTime < timeNow.getTime() + 30000){
                    axios.get("http://localhost:8080/auth/refresh", {withCredentials: true, headers: {Authorization: `Bearer ${token.accessToken}`}})
                        .then((res) => {
                            dispatch(login({
                                accessToken:res.token,
                                expiryTime:res.expiryTime}));
                        })
                }
                config.headers.Authorization = `Bearer ${token.accessToken}`;

                console.log("request", config);

                return config;
            },
            function (error) {
                console.log("API ERROR", error);

                return Promise.reject(error);
            }
        );

        instance.interceptors.response.use(
            function(config) {
                console.log("send response well");

                return config;
            },
            function (error){
                const status = error.response.status;

                switch (status){
                    case 401 : {
                        alert("로그인해야 이용 가능");
                        window.location.href("http://localhost:8080/login");
                        break;
                    }
                }
                return Promise.reject(error);
            }
        )

        //return () => instance.interceptors.request.eject(interceptor);
    }, [])
    return children;
}

export default instance;
export {Interceptor};
