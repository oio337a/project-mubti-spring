import React, { useState, useEffect } from "react";
import Home from "./component/Home";
import {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import Login from "./page/login.js";
import Redirect from "./page/redirect.js";
import Test from "./page/test.js";
import Posts from "./page/Posts.js";
import Write from "./page/Write";
import ReadPost from "./page/ReadPost";

function App() {
  return (
      <Router>
        <Routes>
            <Route path="/" element={<h1>hello</h1>} />
            <Route path="/login" element={<Login />} />
            <Route path="/oauth/redirect" element={<Redirect />} />
            <Route path="/test" element={<Test />} />
            <Route path="/posts" element={<Posts />} />
            <Route path="/posts/:id" element={<ReadPost />} />
            <Route path="/posts/write" element={<Write />} />
        </Routes>
      </Router>
  );
}

export default App;
