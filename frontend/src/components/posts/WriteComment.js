import {useState} from "react";
import React from "react";

function WriteComment(){
    const [comment, setComment] = useState("");

    const onChangeComment = (e) =>{
        setComment(e.target.value);
    }

    const onClickBtn = () => {
        //PostReducer.
    }

    return(
      <div>
          <input onChange={onChangeComment}>
          </input>
          <button onClick={onClickBtn}>작성</button>
      </div>
    );
}

export default WriteComment;