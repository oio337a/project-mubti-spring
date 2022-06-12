import React, { useState, useEffect } from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./pages/login.js";
import Redirect from "./pages/redirect.js";
import Test from "./pages/test.js";
import Mypage from "./pages/Mypage.js";
import Information from "./components/Mypage/Information.js";
import Post from "./components/Mypage/Post.js";
import Withdraw from "./components/Mypage/Withdraw.js";
import Talking from "./components/Mypage/Talking.js";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<h1>hello</h1>} />
        <Route path="/login" element={<Login />} />
        <Route path="/oauth/redirect" element={<Redirect />} />
        <Route path="/test" element={<Test />} />
        <Route path="/mypage" element={<Mypage />} />
        <Route path="/post" component={<Post />} />
        <Route path="/infomation" component={<Information />} />
        <Route path="/withdraw" component={<Withdraw />} />
        <Route path="/talking" component={<Talking />} />
      </Routes>
    </Router>
  );
}

export default App;
