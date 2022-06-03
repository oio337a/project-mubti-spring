import React, { useState, useEffect } from "react";
import Home from "./pages/Home";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Board from "./pages/Board";
import Login from "./pages/login";
import Mubti from "./pages/Mubti";

function App() {
  const [message, setMessage] = useState([]);
  useEffect(() => {
    fetch("/hello")
      .then((res) => {
        return res.json();
      })
      .then((data) => {
        setMessage(data);
      });
  }, []);
  return (
    <>
      {message.map((v, idx) => (
        <li key={`${idx}-${v}`}>{v}</li>
      ))}
      <Mubti />
    </>
    //   <Router>
    //       <Routes>
    //           <Route path="/" element={<h1>hello</h1>} />
    //           <Route path="/login" element={<Login />} />
    //       </Routes>
    //   </Router>
  );
}

export default App; /*}

/*
      </Router>
      <>
          <Properties />
    {/* <ul>
    {message.map((v,idx)=><li key={`${idx}-${v}`}>{v}</li>)}
      </ul> */
// </>
// */
