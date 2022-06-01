import React, { useState } from "react";
import styled from "styled-components";
import { Header, Footer } from "../components/Tools";
import { Link } from "react-router-dom";

const Main = styled.div`
  display: flex;
  height: 100%;
  background-color: blue;
  background: url(../../public/img/test01.png) no-repeat center center fixed;
`;

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
    <Main>
      <Header />
      <View />
      <Footer />
    </Main>
  );
}

export { Home };
