import {useSelector} from "react-redux";
import React from "react";

function Main(){
    const token = useSelector((state) => state.root.user.value); //{console.log(state);});//

    console.log("im....here",token);
    return (
        <div>{token.role}
            <br />
            {token.accessToken}
        </div>
    );
}

export default Main;