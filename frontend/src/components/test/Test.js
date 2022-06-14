import {useState} from "react";
import Questions from "./Questions";
import styled from "styled-components";

const Container = styled.div`
  display: flex;
  height: 100%;
  weight: 100%;
`;

function Test() {
    const [pressed, setPressed] = useState(false);

    const onClick = () => {
        setPressed(true);
    }
    return (
        <div>
            {pressed ? <Questions /> : <button onClick={onClick} >유형 검사 시작</button>}
        </div>
    );
}

export default Test;