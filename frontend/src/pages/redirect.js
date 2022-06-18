import queryString from 'query-string';
import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux"
import { useEffect } from "react";
import { login } from "../reducers/userReducer";
import {Buffer} from "buffer";
import useParseToken from "../utils/useParseToken";
import parseToken from "../utils/parseToken";

function Redirect(){
    const dispatch = useDispatch();
    let navigator = useNavigate();

    const token = queryString.parse(window.location.search).token;

    const [expiryTime, role] = parseToken(token);

    console.log("hey!!", expiryTime, role);

    if (token && expiryTime){
        dispatch(login({
            accessToken: token,
            expiryTime: expiryTime * 1000,
            role: role}));
    }

    useEffect(() => {
        console.log(role);
        if (role == "ROLE_INCOMPLETE_USER")
            navigator("/user/create");

        else {
            window.history.go(-2);
        }
    }, []);

    return(
      <div>
          hi
      </div>
    );
}

export default Redirect;