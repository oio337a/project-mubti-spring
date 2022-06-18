import axios from "axios";
import {useDispatch, useSelector} from "react-redux";
import {login} from "../reducers/userReducer";
import {useEffect, useState} from "react";
import useParseToken from "./useParseToken";
import {Buffer} from "buffer";
import parseToken from "./parseToken";
import issueToken from "./issueToken";

axios.defaults.baseURL ="http://localhost:8080/token";
axios.defaults.withCredentials = true;

function useReIssueToken (){
    const dispatch = useDispatch();
    const token = useSelector((state) => state.user.value);

    let timeNow = new Date;

    const [loading, setloading] = useState(true);
    const [newToken, setNewToken] = useState("");

    const getToken = () => {
        try {
            axios.get("/refresh",
                {
                    withCredentials: true,
                    headers: {Authorization: `Bearer ${token.accessToken}`},
                })
                .then((res) => {
                    issueToken(token.accessToken)
                        .then((res) => {console.log("%%", res);setNewToken(res.data);});
                    /*const newToken = res.data;

                    const base64Payload = newToken.split('.')[1];
                    const payload = Buffer.from(base64Payload, 'base64');
                    const result = JSON.parse(payload.toString());

                    const expiryTime = result.exp;
                    const role = result.role;

                    */
                    const [expiryTime, role] = parseToken(newToken);

                    dispatch(login({
                        accessToken: newToken,
                        expiryTime: expiryTime * 1000,
                        role: role
                    }));
                })
                .finally(() => {
                    setloading(false);
                });
        }
        catch (err){
            console.error("issueToken.get", err.message);
        }
    };

    useEffect(() => {
        if (token.expiryTime < timeNow.getTime() + 30000) {
            getToken();
        }
    }, []);

    return loading;
};

export default useReIssueToken;