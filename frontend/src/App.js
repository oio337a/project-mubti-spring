import React, { useState, useEffect } from "react";
import Home from "./component/Home";
import {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import Login from "./page/login.js";
import Redirect from "./page/redirect.js";
import Test from "./page/test.js";

function App() {
  return (
      <Router>
        <Routes>
            <Route path="/" element={<h1>hello</h1>} />
            <Route path="/login" element={<Login />} />
            <Route path="/oauth/redirect" element={<Redirect />} />
            <Route path="/test" element={<Test />} />
        </Routes>
      </Router>
  );
}

export default App;
