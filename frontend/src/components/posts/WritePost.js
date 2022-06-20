import { useState } from "react";
import { useNavigate } from 'react-router-dom';
import { types } from "./mbtiTypes.js";
import Editor from '../EditorComponent';
import PostsService from "../../service/PostsService";
import {useSelector} from "react-redux";

function WritePost()
{
    const navigate = useNavigate();
    const id = useSelector((state) => state.user.value.id);

    const [category, setCateroty] = useState("-선택-");
    const [title, setTitle] = useState("");
    const [content, setContent] = useState("");

    const onTitleChanged = (event) => {
        setTitle(event.target.value);
    };

    const onUndo = () => {
        navigate("/posts");
    };

    const onChangeContent = (e) => {
        console.log(e);
        setContent(e);
    };
    const onSubmit = () => {
        if (title.length == 0) alert("제목을 입력하세요.");
        else if (content.length == 0) alert("내용을 입력하세요.");
        else if (category == "-선택-") alert("카테고리를 선택하세요.");
        else {
            PostsService.savePost(category, content, title, id);
            navigate("/posts");
        }
    };

    function SelectCategory(){
        const onSelect = (event) => {
            setCateroty(event.target.value);
        };
        return(
            <div>
                <h3>카테고리</h3>
                <select value={category} onChange={onSelect}>
                    <option value={"-선택-"}>
                        -선택-
                    </option>
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
            <button onClick={onUndo} >취소</button>
            <button onClick={onSubmit} >등록</button>
            <Editor value={content} onChange={onChangeContent}/>
        </div>
    );
}

export default WritePost;