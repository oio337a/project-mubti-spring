import {Link, useNavigate} from "react-router-dom";
import PostsService from "../../service/PostsService";
import {useSelector} from "react-redux";
import {useEffect, useState} from "react";
import dateformat from "dateformat";

function ReadPosts(){
    let navigator = useNavigate();
    const token = useSelector((state) => state.user.value);
    let today = new Date;

    const onClickNew = () => {
        navigator("/posts/write");
    }

    const [posts, setPosts] = useState([]);
    //const posts = [];
    useEffect(() => {
        PostsService.getBoards().then((res) => {
            setPosts(res.data.body.posts);
        })}, []);

    const onClickPost = (num) => {
        navigator(`/posts/${num}`);
    }

    return (
      <div>

          <button onClick={onClickNew}>글 쓰기</button>
          <table>
              <thead>
                <tr>
                    <th>Num</th>
                    <th>category</th>
                    <th>title</th>
                    <th>writer</th>
                    <th>date</th>
                    <th>votes</th>
                    <th>views</th>
                </tr>
              </thead>
              <tbody>
              {
                    posts.map((post, index) =>
                        <tr key = {index} onClick={onClickPost(post.postSeq)} >
                            <td> {post.postSeq}</td>
                            <td> {post.postCategory}</td>
                            <td> {post.postTitle}</td>
                            <td> {post.user.userAlias}</td>
                            <td> {dateformat(post.postDate, 'yy-m-dd') == dateformat(today, 'yy-m-dd')?
                                dateformat(post.postDate, 'h:MM'):dateformat(post.postDate, 'yy-m-dd')}</td>
                            <td> {post.vote}</td>
                            <td> {post.view} [{post.comments.length}]</td>
                        </tr>
                    )
              }
              </tbody>
          </table>
      </div>
    );
}

export default ReadPosts;