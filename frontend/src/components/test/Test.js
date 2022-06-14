import {useState} from "react";
import Questions from "./Questions";
import styled from "styled-components";

const Container = styled.div`
  display: flex;
  height: 100%;
  weight: 100%;
`;

const Item = styled.button`
  display: inline-block;
  position: relative;
  padding: 5px;
  margin-left: 10px;
  margin-bottom: 10px;
  border: 1px solid rgb(0, 0, 0);
  border-radius: 10px;
  color: rgb(0, 0, 0);
  text-align: center;
  text-decoration: none;
  background-color: rgba(255, 255, 255, 0);
`;

function Test() {
    const [pressed, setPressed] = useState(false);

    const onClick = () => {
        setPressed(true);
    }
    return (
        <Container>
            {pressed ? <Questions /> : <Item onClick={onClick} >유형 검사 시작</Item>}
        </Container>
    );
}

export default Test;