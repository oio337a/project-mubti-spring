import React from "react";
import { Wraper, Header, Footer } from "../components/Tools";
import Sidebar from "../components/Sidebar/Sidebar";

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
