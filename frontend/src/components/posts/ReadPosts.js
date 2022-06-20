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
    const [currentPage, setCurrentPage] = useState(1);
    const [maxPage, setMaxPage] = useState(0);

    useEffect(() => {
        PostsService.getBoards().then((res) => {
            setPosts(res.data.content);
            setMaxPage(parseInt((res.data.totalElements)/10) + 1);
        })}, []);

    const onClickPost = (num, e) => {
        navigator(`/posts/${num}`);
    }

    const onClickPage = (e, page) => {
        setCurrentPage(page);
        PostsService.getPagedPosts(page - 1).then((res) => {
            setPosts(res.data.content);
        })
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
                        <tr key = {index} onClick={(e) => onClickPost(post.postSeq, e)}>
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
          <div>
              <div>
                  {currentPage < 3 ? null : <button onClick={(e) => onClickPage(e, 1)}>1</button>}
                  {currentPage < 3  ? null : <div>...</div>}
                  {currentPage == 1 ? null : <button onClick={(e) => onClickPage(e, currentPage - 1)}>{currentPage - 1}</button>}
                  <button onClick={(e) => onClickPage(e, currentPage)}>{currentPage} </button>
                  {maxPage > 2 ? ((currentPage + 1 > maxPage ? null : <button onClick={(e) => onClickPage(e, currentPage + 1)}>{currentPage + 1}</button>)) : null}
                  {maxPage - currentPage < 3 ? null : <div>...</div>}
                  {maxPage - currentPage < 2  ? null : <button onClick={(e) => onClickPage(e, maxPage)}>{maxPage}</button>}
              </div>
          </div>
      </div>
    );
}

export default ReadPosts;