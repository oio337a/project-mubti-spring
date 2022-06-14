import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import PostsService from "../../service/PostsService";

function Post(){
    const params = useParams().id;
    console.log(params);

    const [post, setPost] = useState([]);
    const [comments, setComments] = useState([]);

    useEffect(() => {
        PostsService.getPost(params).then((res) => {
            setPost(res.data.body);
        })}, []);

    console.log(post);

    return (
        <div>
            <div>category</div>
            <div>title</div>
            <div>date</div>
            <div>view</div>
            <div>vote</div>
            <div>writers</div>
            <div>content</div>
            <div>comments</div>

        </div>
    );
}

export default Post;