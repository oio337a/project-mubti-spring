import {useSelector} from "react-redux";

function Test(){
    const user = useSelector((state) => state.user.value);
    console.log("!!!!!!!", user);

    return(
        <div>hh 스토어에서 가져온 토큰 ㅎㅎ ->{user.token}</div>
    )
}

export default Test;