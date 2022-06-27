import WritePost from "../components/posts/WritePost.js"
import React from "react";
import useCheckUser from "../utils/useCheckUser";

function Write()
{
    const checkValue = useCheckUser("COMPLETE_USER");

    console.log(checkValue);
    if(!checkValue.approval)
        //window.location.replace(checkValue.redirectURL);
    return (
        <WritePost />
    );
}

export default Write;