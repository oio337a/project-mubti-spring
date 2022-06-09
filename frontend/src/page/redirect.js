import queryString from 'query-string';
import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux"
import { useEffect } from "react";
import { login } from "../reducers/userReducer";
import { Buffer } from 'buffer';

function Redirect(){
    const dispatch = useDispatch();
    let navigate = useNavigate();

    const token = queryString.parse(window.location.search).token;

    var base64Payload = token.split('.')[1];
    var tokenPayload = Buffer.from(base64Payload, 'base64');
    var decodedToken = JSON.parse(tokenPayload.toString())

    console.log(decodedToken);
    if (decodedToken.accessToken && decodedToken.expireTime){
        console.log("IF");

        dispatch(login({
            accessToken:decodedToken.accessToken,
            expireTime:decodedToken.expireTime}));
    }
    else{
        console.log("ELSE");
    }

    useEffect(() => {
        navigate('/test');
    }, []);

    return(
      <div>
          hi
      </div>
    );
}

export default Redirect;