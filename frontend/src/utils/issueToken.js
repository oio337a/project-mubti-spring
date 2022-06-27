import axios from "axios";
import parseToken from "./parseToken";
import {login} from "../reducers/userReducer";

function issueToken(token){
    return axios.get("http://localhost:8080/token/refresh",
        {
            withCredentials: true,
            headers: {Authorization: `Bearer ${token}`},
        })
}

export default issueToken;