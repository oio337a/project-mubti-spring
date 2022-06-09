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
    const expiryTime = queryString.parse(window.location.search).expiryTime;

    console.log(token);
    if (token && expiryTime){
        console.log("IF");

        dispatch(login({
            accessToken:token,
            expiryTime:expiryTime}));
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