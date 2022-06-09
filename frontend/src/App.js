import React, { useState, useEffect } from "react";
import Home from "./components/Home";
import {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import Login from "./pages/login.js";
import Redirect from "./pages/redirect.js";
import Test from "./pages/test.js";

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
