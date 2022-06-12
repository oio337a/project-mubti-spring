import React from "react";
import styled from "styled-components";

const Wraper = styled.div`
  display: flex;
  flex-direction: column;
  height: 100vh;
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

const Nav = styled.nav`
  display: flex;
  justify-content: end;
  justify-items: end;
`;

function Header() {
  return (
    <Nav>
      <Button>로그인</Button>
      <Button>회원가입</Button>
    </Nav>
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
      풋
    </div>
  );
}

export { Wraper, Button, Header, Footer };
