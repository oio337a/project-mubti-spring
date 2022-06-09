import axios from 'axios';
import { useSelector } from "react-redux";
import { useDispatch } from "react-redux"
import { login } from "../reducers/userReducer";

const instance = axios.create({
    baseURL: "/"
})

const dispatch = useDispatch();

instance.interceptors.request.use((config) => {
    const token = useSelector((state) => state.user.value);

    //시간 비교 수정
    if (token.accessToken && token.expireTime){

    }
    else if (token.expireTime){
        axios.get("/refresh").then((res) => {
            dispatch(login({
                accessToken:res.accessToken,
                expireTime:res.expireTime}));

        })
    }
    return config;
})
