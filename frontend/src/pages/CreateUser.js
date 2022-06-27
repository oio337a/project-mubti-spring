import Info from "../components/user/Info";
import React from "react";
import useCheckUser from "../utils/useCheckUser";

function CreateUser(){
    const checkValue = useCheckUser("INCOMPLETE_USER");

    console.log(checkValue);
    if(!checkValue.approval)
        window.location.replace(checkValue.redirectURL);
    return (
        <Info />
    );
}

export default CreateUser;