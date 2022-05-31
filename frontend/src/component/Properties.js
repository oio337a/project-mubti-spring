import { useState } from "react";
import styles from "../css/properties.module.css";
import { Header, Footer } from "./Home";

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
    <div>
      <Header />
      {MAIN_DATA.map((data) => {
        return (
          <button
            onClick={buttonValueSetting}
            name={data.text}
            className={styles.ghost_btn}
            key={data.id}
          >
            {data.text}
          </button>
        );
      })}
      {content && <div className={styles.content}>{select[content]}</div>}
      <Footer />
    </div>
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
