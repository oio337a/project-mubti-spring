import queryString from 'query-string';
import { useNavigate } from "react-router-dom";
import {useDispatch} from "react-redux"
import {useEffect, useState} from "react";
import { login } from "../reducers/userReducer";
import parseToken from "../utils/parseToken";

function Redirect(){
    const dispatch = useDispatch();
    let navigator = useNavigate();

    const [loading, setLoading] = useState(false);
    const token = queryString.parse(window.location.search).token;
    const [expiryTime, role, id] = parseToken(token);

    console.log("hey!!", token, expiryTime);

    const storeToken = () => {
        if (token && expiryTime) {
            dispatch(login({
                accessToken: token,
                expiryTime: expiryTime * 1000,
                role: role,
                id: id
            }));
            setLoading(true);
        }
    }

    useEffect(() => {
        if(loading){
            if (role == "ROLE_INCOMPLETE_USER")
                navigator("/user/create");

            else {
                //window.history.go(-2);
            }
        }
    }, [loading]);

    useEffect(() => {
        storeToken();
        /*
        if (token && expiryTime) {
            dispatch(login({
                accessToken: token,
                expiryTime: expiryTime * 1000,
                role: role,
                id: id
            }));
            if (role == "ROLE_INCOMPLETE_USER")
                navigator("/user/create");

            else {
                window.history.go(-2);
            }
        }*/
    }, [token]);
/*
    useEffect(() => {
        console.log(role);
        if (role == "ROLE_INCOMPLETE_USER")
            navigator("/user/create");

        else {
            window.history.go(-2);
        }
    }, []);
*/
    return(
      <div>
          hi
      </div>
    );
}

export default Redirect;