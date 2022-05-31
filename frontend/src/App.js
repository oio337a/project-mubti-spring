import React, { useState, useEffect } from "react";
import Home from "./component/Home";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./component/login.js";
//import Board from "./component/Board";
import Board from "./component/Board.js"
import { createStore } from "redux";
//import { reducer } from  "./assets/reducers/"
import Post from "./component/Post.js"

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
           <Route path="/Post" element={<Post />} />
           <Route path="/Board" element={<Board />} />
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
