import { useState } from "react";
import {BrowserRouter as Router, Link} from 'react-router-dom';
import Board from "./Board"

function Post()
{
    const [category, setCateroty] = useState("--선택--");
    const [title, setTitle] = useState("제목입니다");
    const [content, setContent] = useState("");
    const onTitleChanged = (event) => {
        setTitle(event.target.value);
    };
    const onContentChanged = (event) => {
        setContent(event.target.value);
    };
    const onUndo = () => {

    };
    const onSubmit = () => {

    };
    function SelectCategory(){
        const types = ["ENFJ", "ENFP", "ENTJ", "ENTP", "ESFJ", "ESTJ", "ESFP", "ESTP",
            "INFJ", "INFP", "INTJ", "INTP", "ISFJ", "ISTJ", "ISFP", "ISTP"];
        const onSelect = (event) => {
            setCateroty(event.target.value);
        };
        return(
            <div>
                <h3>카테고리</h3>
                <select value={category} onChange={onSelect}>
                    {types.map((type) => (
                        <option value={type}>
                            {type}
                        </option>
                    ))}
                </select>
            </div>
        );
    }
    return (
        <div>
            <SelectCategory />
            <h1>제목</h1>
            <input
                type="text"
                id="title"
                placeholder="Title"
                value={title}
                onChange={onTitleChanged}
            />
            <input
                type="text"
                id="content"
                placeholder="Content"
                value={content}
                onChange={onContentChanged}
            />
            <Router>
                <Link to="/board">
                    <button onClick={onUndo}>취소</button>
                </Link>
            </Router>
            <button onClick={onSubmit}>등록</button>
        </div>
    );
}

export default Post;