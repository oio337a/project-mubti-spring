import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import PostsService from "../../service/PostsService";
import dateformat from "dateformat";
import ReadComments from "./ReadComments";

function Post(){
    const params = useParams().id;

    const [post, setPost] = useState([]);
    const [comments, setComments] = useState([]);
    const [loading, setLoading] = useState(false);

    const getPost = async () => {
        const response = await PostsService.getPost(params);
        await setPost(response.data);
        await setComments(post.comments);
        setLoading(true);
    }

    useEffect(() => {
        getPost();
    }, []);

    console.log("SDSD", post);

    const onClickVote = () => {
        //PostsService.
    }

    return (
        <div>
            {loading ? <div>
                    <div>category {post.postCategory}</div>
                    <div>title {post.postTitle}</div>
                    <div>date {dateformat(post.postDate, 'yyyy/mm/dd')}</div>
                    <div>view {post.view}</div>
                    <div>vote {post.vote}</div>
                    <div>user {post.user.userAlias}</div>
                    <div>content {post.postContent}</div>
                    <button onClick={onClickVote}>추천</button>
                    <div>---댓글---</div>
                    <ReadComments comments={comments}/>
                </div>
                : <div>로딩 중</div>}
        </div>
    );
}

export default Post;
/*
<div>user {post.user.userAlias}</div>
<ReadComments comments={comments}/>*/