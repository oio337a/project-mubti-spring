import {useLocation} from "react-router-dom";
import Result from "../components/test/Result";

function TestResult(){
    const type = useLocation().state.type;

    return (
        <Result type = {type} />
    );
}

export default TestResult;