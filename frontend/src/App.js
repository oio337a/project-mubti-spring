import React, { useState, useEffect } from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./component/login.js";
import Board from "./component/Board"
import { createStore } from "redux";
import Post from "./component/Post"

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
  //const store = createStore(reducer);
  return (
      <Router>
         <Routes>
           <Route path="/" element={<h1>hello</h1>} />
           <Route path="post/" element={<Post />} />
           <Route path="board/" element={<Board />} />
         </Routes>
      </Router>
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