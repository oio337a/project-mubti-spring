import React from "react";
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
`;

function Header() {
  return (
    <nav>
      <Button>로그인</Button>
      <Button>회원가입</Button>
    </nav>
  );
}

function Footer() {
  return (
    <div
      style={{
        display: "flex",
        height: "10%",
      }}
    >
      이곳은 풋 입니다.
    </div>
  );
}

export { Header, Footer };
