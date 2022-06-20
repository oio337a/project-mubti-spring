import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./pages/login.js";
import Redirect from "./pages/redirect.js";
import Mypage from "./pages/Mypage.js";
import TestPage from "./pages/TestPage.js";
import TestResult from "./pages/TestResult";
import ReadPost from "./pages/ReadPost";
import Write from "./pages/Write";
import CreateUser from "./pages/CreateUser";
import {Interceptor} from "./utils/userRequestApi";
import Board from "./pages/Board";
import Main from "./pages/Main";
import ModifyPost from "./pages/ModifyPost";

function App() {
    return (
        <Interceptor>
            <Router>
                <Routes>
                    <Route path="/" element={<Main/>}/>
                    <Route path="/login" element={<Login/>}/>
                    <Route path="/oauth/redirect" element={<Redirect/>}/>
                    <Route path="/mypage" element={<Mypage/>}/>
                    <Route path="/posts" element={<Board/>}/>
                    <Route path="/posts/:id" element={<ReadPost/>}/>
                    <Route path="/posts/write" element={<Write/>}/>
                    <Route path="/posts/:id/write" element={ModifyPost} />
                    <Route path="/test" element={<TestPage/>}/>
                    <Route path="/test/result" element={<TestResult/>}/>
                    <Route path="/user/create" element={< CreateUser/>}/>
                </Routes>
            </Router>
        </Interceptor>
  );
}

export default App;
