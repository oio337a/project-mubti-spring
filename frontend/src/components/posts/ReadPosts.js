import {useNavigate} from "react-router-dom";
import PostsService from "../../service/PostsService";
import {useSelector} from "react-redux";
import {useState} from "react";

function ReadPosts(){
    let navigator = useNavigate();
    const token = useSelector((state) => state.user.value);

    const onClickNew = () => {
        navigator("/posts/write");
    }
    //const posts = useSelector((state) => state.posts.value);
    //const posts = [];
    const [posts, setPosts] = useState([]);
    PostsService.getBoards(token).then((res) => {
        setPosts(res.data);
    })

    return (
      <div>
          <button onClick={onClickNew}>글 쓰기</button>
          <table>
              <thead>
                <tr>
                    <th>Num</th>
                    <th>title</th>
                    <th>writer</th>
                    <th>date</th>
                    <th>votes</th>
                    <th>views</th>
                </tr>
              </thead>
              <tbody>
              {
                    posts.map((post) =>
                        <tr key = {post.num}>
                            <td> {post.num}</td>
                            <td> {post.title}</td>
                            <td> {post.user}</td>
                            <td> {post.date}</td>
                            <td> {post.votes}</td>
                            <td> {post.views}</td>
                        </tr>
                    )
              }
              </tbody>
          </table>
      </div>
    );
}

export default ReadPosts;