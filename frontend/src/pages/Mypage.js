import React, { useState } from "react";
import { Wraper, Header, Footer } from "../components/Tools";
import styled from "styled-components";
import Sidebar from "../components/Sidebar/Sidebar";
import Comment from "../components/Mypage/Comments";
import Information from "../components/Mypage/Information";
import Talking from "../components/Mypage/Talking";

function Mypage() {
  return (
    <Wraper>
      <Header />
      <Sidebar />
      <Footer />
    </Wraper>
  );
}

export default Mypage;
