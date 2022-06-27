import RewritePost from "../components/posts/RewritePost";
import {useParams} from "react-router-dom";
import React from "react";
import useCheckUser from "../utils/useCheckUser";

function ModifyPost(){
    const approval = useCheckUser("COMPLETE_USER");

    console.log(approval);
    if(!approval)
        window.location.replace(process.env.REACT_APP_LOGIN_URL);

    const id = useParams().id;

    console.log(id);
    return(
        <RewritePost id={id}/>
    );
}

export default ModifyPost;