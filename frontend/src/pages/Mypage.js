import React, { useState } from "react";
import { Wraper, Header, Footer } from "../components/Tools";
import styled from "styled-components";
import Sidebar from "../components/Mypage/Sidebar";

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
