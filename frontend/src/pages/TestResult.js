import {useLocation} from "react-router-dom";
import Result from "../components/test/Result";
import React from "react";

function TestResult(){
    const type = useLocation().state.type;

    return (
        <Result type = {type} />
    );
}

export default TestResult;