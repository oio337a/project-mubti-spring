import {useSelector} from "react-redux";
import {useEffect, useState} from "react";
import axios from "axios";
import api from "../utils/api";

function Test(){
    const user = useSelector((state) => state.user.value);
    console.log("!!!!!!!", user);


    const [test, setTest] = useState("");

    useEffect(() => {
        const testA = async () => {
            await api.get('/api/v1/users').then((res) => setTest(res));
        }
    }, []);

    return(
        <div>hh 스토어에서 가져온 토큰 ㅎㅎ ->{user.accessToken}, {user.expiryTime}dkdk Test {test}</div>
    )
}

export default Test;