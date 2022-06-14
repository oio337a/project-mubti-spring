import React, { useState, useEffect } from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./pages/login.js";
import Redirect from "./pages/redirect.js";
import Test from "./pages/test.js";
import Board from "./pages/Board.js"
import Write from "./pages/Write.js"
import ReadPost from "./pages/ReadPost.js"
import {Interceptor} from "./utils/api";

function App() {
  return (
      <Interceptor>
          <Router>
              <Routes>
                  <Route path="/" element={<h1>hello</h1>} />
                  <Route path="/login" element={<Login />} />
                  <Route path="/oauth/redirect" element={<Redirect />} />
                  <Route path="/test" element={<Test />} />
                  <Route path="/posts" element={<Board />} />
                  <Route path="/posts/*" element={<ReadPost />} />
                  <Route path="/posts/write" element={<Write />} />
              </Routes>
          </Router>
      </Interceptor>
  );
}

export default App;
