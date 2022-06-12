import React, { useState } from "react";
import styled from "styled-components";

const Button = styled.button`
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
  &:hover {
    background-color: rgb(0, 0, 0);
    color: rgb(255, 255, 255);
  }
`;

const Container = styled.div`
  height: 90%;
  display: grid;
  grid-gap: 1px;
  grid-template-areas:
    "myMbti mbti_16"
    "match board";
  grid-template-columns: 1fr 1fr;
  grid-template-rows: 1fr 1fr;
`;

function View() {
  return (
    <Container>
      <Button id="my_mbti">나의 mbti는?</Button>
      <Button id="mbti_16">16 mbti</Button>
      <Button id="match">mbti 대화</Button>
      <Button id="board">자유게시판</Button>
    </Container>
  );
}

function Home() {
  return (
    <div>
      <View />
    </div>
  );
}

export default Home;
