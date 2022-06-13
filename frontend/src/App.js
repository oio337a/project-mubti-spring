import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./pages/login.js";
import Redirect from "./pages/redirect.js";
import Test from "./pages/test.js";
import Mypage from "./pages/Mypage.js";
import Information from "./components/Mypage/Information.js";
import Post from "./components/Mypage/Post.js";
import Withdraw from "./components/Mypage/Withdraw.js";
import Talking from "./components/Mypage/Talking.js";
import TestPage from "./pages/TestPage.js";
import TestResult from "./pages/TestResult";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<h1>hello</h1>} />
        <Route path="/login" element={<Login />} />
        <Route path="/oauth/redirect" element={<Redirect />} />
        <Route path="/mypage" element={<Mypage />} />
        <Route path="/post" element={<Post />} />
        <Route path="/infomation" element={<Information />} />
        <Route path="/withdraw" element={<Withdraw />} />
        <Route path="/talking" element={<Talking />} />
        <Route path="/test" element={<TestPage />} />
        <Route path="/test/result" element={<TestResult />} />
      </Routes>
    </Router>
  );
}

export default App;
