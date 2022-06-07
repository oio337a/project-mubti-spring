import { useState } from "react";
import { useNavigate } from 'react-router-dom';
import { types } from "./mbtiTypes.js";
import { useDispatch } from "react-redux";
import { savePost } from "../../reducers/postReducer";

function WritePost()
{
    const dispatch = useDispatch();
    const navigate = useNavigate();

    const [category, setCateroty] = useState("--선택--");
    const [title, setTitle] = useState("");
    const [content, setContent] = useState("");

    const onTitleChanged = (event) => {
        setTitle(event.target.value);
    };
    const onContentChanged = (event) => {
        setContent(event.target.value);
    };
    const onUndo = () => {
        navigate("/posts");
    };
    const onSubmit = () => {
        dispatch(savePost({category: category, title: title, content: content}));
        navigate("/posts");
    };

    function SelectCategory(){
        const onSelect = (event) => {
            setCateroty(event.target.value);
        };
        return(
            <div>
                <h3>카테고리</h3>
                <select value={category} onChange={onSelect}>
                    {types.map((type, index) => (
                        <option value={type} key={index}>
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
            <button onClick={onUndo}>취소</button>
            <button onClick={onSubmit}>등록</button>
        </div>
    );
}

export default WritePost;