import React, { useState, useEffect } from "react";
import Home from "./component/Home";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./component/login.js";
import Board from "./component/Board";

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
    {message.map((v,idx)=><li key={`${idx}-${v}`}>{v}</li>)}
//      <Board />
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
