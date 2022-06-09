import { useState } from "react";
import styles from "../css/properties.module.css";
import styled from "styled-components";
import { Header, Footer } from "./Home";

const Container = styled.div`
  display: flex;
  height: 100vh;
`;

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
    ISTJ: "ISTJ:",
    ISFJ: "ISFJ:",
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
    <Container>
      <Header />
      {MAIN_DATA.map((data) => {
        return (
          <Btn
            onClick={buttonValueSetting}
            name={data.text}
            className={styles.ghost_btn}
            key={data.id}
          >
            {data.text}
          </Btn>
        );
      })}
      {content && (
        <Contents className={styles.content}>{select[content]}</Contents>
      )}
      <Footer />
    </Container>
  );
}
export default Properties;

export const MAIN_DATA = [
  { id: 1, text: "ISTJ" },
  { id: 2, text: "ISFJ" },
  { id: 3, text: "INFJ" },
  { id: 4, text: "INTJ" },
  { id: 5, text: "ISTP" },
  { id: 6, text: "ISFP" },
  { id: 7, text: "INFP" },
  { id: 8, text: "INTP" },
  { id: 9, text: "ESTP" },
  { id: 10, text: "ESFP" },
  { id: 11, text: "ENFP" },
  { id: 12, text: "ENTP" },
  { id: 13, text: "ESTJ" },
  { id: 14, text: "ESFJ" },
  { id: 15, text: "ENFJ" },
  { id: 16, text: "ENTJ" },
];
