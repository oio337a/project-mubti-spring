import {useNavigate} from "react-router-dom";
import PostsService from "../../service/PostsService";
import {useEffect, useState} from "react";
import dateformat from "dateformat";
import queryString from "query-string";
import React from "react";
import {types} from "./mbtiTypes";

const BASE_URL="http://localhost:3000/posts";

function ReadPosts(){
    let navigator = useNavigate();

    let today = new Date;

    const onClickNew = () => {
        navigator("/posts/write");
    }

    const queryStrings = queryString.parse(window.location.search);
    const page = queryStrings.page === undefined ? 1 : queryStrings.page;
    const type = queryStrings.type === undefined ? "전체" : queryStrings.type;

    const [posts, setPosts] = useState([]);
    const [currentPage, setCurrentPage] = useState(page);
    const [maxPage, setMaxPage] = useState(0);
    const [category, setCategory] = useState(type);
    const [search, setSearch] = useState("");
    const [searchType, setSearchType] = useState("전체");

    const getBoard = async () => {
        const response = await PostsService.getBoard();
        console.log(response);
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
        if (category === "전체") {
            window.history.pushState("", "", `${BASE_URL}?page=${page}`)
            PostsService.getPagedPosts(page - 1).then((res) => {
                setPosts(res.data.content);
            })
        }
        else {
            window.history.pushState("", "", `${BASE_URL}?type=${category}&page=${page}`)
            PostsService.getPostByCategory(category, page - 1).then((res) => {
                console.log("PAGE WITH CATEGORY", res);
                //setPosts(res.data.content);
            })
        }
    }

    function SelectCategory(){
        const onSelect = (e) => {
            const newCategory = e.target.value;
            setCategory(newCategory);
            PostsService.getPostByCategory(category, 1)
                .then((res)=> {
                    console.log("CATEGORY", res);
                    //setMaxPage();
                })
            window.history.pushState("","", `${BASE_URL}?type=${newCategory}&page=${page}`)
        };
        return(
            <div>
                <h3>카테고리</h3>
                <select value={category} onChange={onSelect}>
                    <option value={"전체"}>
                        전체
                    </option>
                    {types.map((type, index) => (
                        <option value={type} key={index}>
                            {type}
                        </option>
                    ))}
                </select>
            </div>
        );
    }

    function Searching(){
        const onSelectSearch = (e) => {
            const newCategory = e.target.value;
            setSearchType(newCategory);
        }

        const onChangeSearch = (e) => {
            setSearch(e.target.value);
        }

        const onClickSearch = () => {
            PostsService.getSearchedPost(category, 1, search, searchType)
                .then((res) => {console.log("SEARCH", res);})
        }

        return(
            <div>
                <select value={category} onChange={onSelectSearch}>
                    <option value={"전체"}>
                        전체
                    </option>
                    {types.map((type, index) => (
                        <option value={type} key={index}>
                            {type}
                        </option>
                    ))}
                </select>
                <input onChange={onChangeSearch} />
                <button onClick={onClickSearch}>검색</button>
            </div>
        )
    }

    return (
      <div>
          <button onClick={onClickNew}>글 쓰기</button>
          <SelectCategory />
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
                            <td> {post.categoryType}</td>
                            <td> {post.postTitle}</td>
                            <td> {post.userAlias}</td>
                            <td> {dateformat(post.postDate, 'yy-m-dd') === dateformat(today, 'yy-m-dd')?
                                dateformat(post.postDate, 'h:MM'):dateformat(post.postDate, 'yy-m-dd')}</td>
                            <td> {post.vote}</td>
                            <td> {post.view} [{post.comment.length}]</td>
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
          <Searching />
      </div>
    );
}

export default ReadPosts;