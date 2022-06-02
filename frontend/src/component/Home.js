import React, { useState } from "react";
import styles from "../css/home.module.css";
import { Link } from "react-router-dom";

function Header() {
  return (
    <nav>
      <button className={styles.ghost_btn}>로그인</button>
      <button className={styles.ghost_btn}>회원가입</button>
    </nav>
  );
}

function Container() {
  return (
    <div className={styles.container}>
      <button id="my_mbti" className={styles.ghost_btn}>
        나의 mbti는?
      </button>
      <button id="mbti_16" className={styles.ghost_btn}>
        16 mbti
      </button>
      <button id="match" className={styles.ghost_btn}>
        mbti 대화
      </button>
      <button id="board" className={styles.ghost_btn}>
        자유게시판
      </button>
    </div>
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

function Home() {
  return (
    <div className={styles.main}>
      <Header />
      <Container />
      <Footer />
    </div>
  );
}

export { Home, Header, Footer };
