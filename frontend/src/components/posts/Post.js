import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import PostsService from "../../service/PostsService";
import dateformat from "dateformat";
import ReadComments from "./ReadComments";
import { useDispatch, useSelector } from "react-redux";
import { savePost } from "../../reducers/postReducer";
import ReactHtmlParser from "react-html-parser";
import React from "react";

function Post(){
    const params = useParams().id;
    const userId = useSelector((state) => state.root.user.value.id);
    const dispatch = useDispatch();

    const [post, setPost] = useState([]);
    const [comments, setComments] = useState([]);
    const [loading, setLoading] = useState(false);

    const getPost = async () => {
        try{
            const response = await PostsService.getPost(params);
            await setPost(response.data);
            await setComments(response.data.comment);
            await setLoading(true);
        }
        catch (e){}
    }

    useEffect(() => {
        getPost();
    }, []);

    const onClickVote = () => {
        PostsService.clickVote(post.postSeq)
            .then((res) => {
                if (res.status === 200) console.log("VOTE!!");})
            .catch(e => {
                console.log(e);
                if(e.response.status === 409) alert("중복");
            })
    }

    const onClickDelete = () => {
        PostsService.deletePost(post.postSeq).then((res) => {
            console.log(res);
        });
        window.location.replace(`http://localhost:3000/posts`);
    }

    const onClickModify = () => {
        dispatch(savePost({
            category: post.categoryType,
            title: post.postTitle,
            content: post.postContent
        }));
        window.location.replace(`http://localhost:3000/posts/${post.postSeq}/write`);
    }

    return (
        <div>
            {loading ?
                <div>
                    {(userId === post.userId ?
                        <div>
                            <button onClick={onClickModify}>수정</button>
                            <button onClick={onClickDelete}>삭제</button>
                        </div>
                        : <div>{userId}와 {post.userId}</div> )}
                    <div>category {post.categoryType}</div>
                    <div>title {post.postTitle}</div>
                    <div>date {dateformat(post.postDate, 'yyyy/mm/dd')}</div>
                    <div>view {post.view}</div>
                    <div>vote {post.vote}</div>
                    <div>user {post.userAlias}</div>
                    <div>content {ReactHtmlParser(post.postContent)}</div>
                    <button onClick={onClickVote}>추천</button>
                    <div>---댓글---</div>
                    <ReadComments allComments={comments}/>
                </div>
                : <div>로딩 중</div>}
        </div>
    );
}

export default Post;
