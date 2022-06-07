import {useNavigate} from "react-router-dom";

function ReadPosts(){
    let navigator = useNavigate();
    const onClickNew = () => {
        navigator("/posts/write");
    }
    return (
      <div>
          <button onClick={onClickNew}>글 쓰기</button>
      </div>
    );
}

export default ReadPosts;