import {useNavigate, useParams} from "react-router-dom";
import PostsService from "../../service/PostsService";
import {useEffect, useState} from "react";
import dateformat from "dateformat";

function ReadPosts(){
    let navigator = useNavigate();

    let today = new Date;

    const onClickNew = () => {
        navigator("/posts/write");
    }

    const [posts, setPosts] = useState([]);
    const [currentPage, setCurrentPage] = useState(useParams().id);
    const [maxPage, setMaxPage] = useState(0);

    const getBoard = async () => {
        const response = await PostsService.getBoard();
        setPosts(response.data.content);
        setMaxPage(parseInt((response.data.totalElements - 1)/10) + 1);
    }

    useEffect(() => {
            getBoard();
        }, []);

    const onClickPost = (num, e) => {
        navigator(`/posts/${num}`);
    }

    const onClickPage = (e, page) => {
        setCurrentPage(page);
        window.history.pushState("","", `http://localhost:3000/posts/pages/${page}`)
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
                            <td> {post.postCategoryType}</td>
                            <td> {post.postTitle}</td>
                            <td> {post.userAlias}</td>
                            <td> {dateformat(post.postDate, 'yy-m-dd') === dateformat(today, 'yy-m-dd')?
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
                  {currentPage < 4  ? null : <div>...</div>}
                  {currentPage === 1 ? null : <button onClick={(e) => onClickPage(e, currentPage - 1)}>{currentPage - 1}</button>}
                  <button onClick={(e) => onClickPage(e, currentPage)}>{currentPage} </button>
                  {maxPage <= currentPage + 1 ? null : <button onClick={(e) => onClickPage(e, currentPage + 1)}>{currentPage + 1}</button>}
                  {maxPage - currentPage < 3 ? null : <div>...</div>}
                  {maxPage === currentPage ? null : <button onClick={(e) => onClickPage(e, maxPage)}>{maxPage}</button>}
              </div>
          </div>
      </div>
    );
}

export default ReadPosts;