import { useState } from "react";
import { Wraper, Header, Footer } from "../components/Tools";
import MAIN_DATA from "../data/PersonalType";
import styled from "styled-components";
import React from "react";

const Btn = styled.button`
  min-width: 50px;
  display: inline-block;
  position: relative;
  padding: 5px;
  margin-left: 10px;
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

const Contents = styled.div`
  display: flex;
  border: 1px solid black;
  align-items: center;
  justify-content: center;
  justify-items: center;
  height: 50%;
`;

function Properties() {
  const [content, setContent] = useState();

  const buttonValueSetting = (e) => {
    const { name } = e.target;
    setContent(name);
  };

  const select = {
    ISTJ: "안녕하세요.",
    ISFJ: "하하하",
    INFJ: "INFJ:",
    INTJ: "INTJ:",

    ISTP: "ISTP:",
    ISFP: "ISFP:",
    INFP: "INFP:",
    INTP: "INTP:",

    ESTP: "ESTP:",
    ESFP: "ESFP:",
    ENFP: "ENFP:",
    ENTP: "ENTP:",

    ESTJ: "ESTJ:",
    ESFJ: "ESFJ:",
    ENFJ: "ENFJ:",
    ENTJ: "ENTJ:",
  };

  console.log(content);
  return (
    <Wraper>
      <Header />
      {MAIN_DATA.map((data) => {
        return (
          <Btn onClick={buttonValueSetting} name={data.text} key={data.id}>
            {data.text}
          </Btn>
        );
      })}
      {content && <Contents>{select[content]}</Contents>}
      <Footer />
    </Wraper>
  );
}
export default Properties;
