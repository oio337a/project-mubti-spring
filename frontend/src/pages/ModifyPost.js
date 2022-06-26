import RewritePost from "../components/posts/RewritePost";
import {useParams} from "react-router-dom";
import React from "react";

function ModifyPost(){
    const id = useParams().id;

    console.log(id);
    return(
        <RewritePost id={id}/>
    );
}

export default ModifyPost;