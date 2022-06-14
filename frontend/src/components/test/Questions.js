import TestQuestions from "../../data/TestQuestions";
import {useState} from "react";
import {useNavigate} from "react-router-dom";
import styled from "styled-components";

const Question = styled.div`
  display: inline-block;
  position: relative;
  padding: 5px;
  margin-left: 10px;
  margin-bottom: 10px;
  border: 1px solid rgb(0, 0, 0);
  border-radius: 10px;
  color: rgb(0, 0, 0);
  text-align: center;
  text-decoration: none;
  background-color: rgba(255, 255, 255, 0);
`;

const Item = styled.button`
  display: inline-block;
  position: relative;
  padding: 5px;
  margin-left: 10px;
  margin-bottom: 10px;
  border: 1px solid rgb(0, 0, 0);
  border-radius: 10px;
  color: rgb(0, 0, 0);
  text-align: center;
  text-decoration: none;
  background-color: rgba(255, 255, 255, 0);
`;

function Questions(){
    let navigator = useNavigate();

    const [qNum, setQNum] = useState(0);
    const [iToE, setIToE] = useState(0);
    const [sToN, setStoN] = useState(0);
    const [tToF, setTtoF] = useState(0);
    const [ptoJ, setPtoJ] = useState(0);

    const onClickButton = (btnNum, event) => {
        if (qNum < 12) setQNum((pre) => pre + 1);
        if (btnNum == 1) {
            switch (TestQuestions[qNum].Up) {
                case 'E':
                    setIToE((pre) => pre + 1); break;
                case 'N':
                    setStoN((pre) => pre + 1); break;
                case 'F':
                    setTtoF((pre) => pre + 1); break;
                case 'J':
                    setPtoJ((pre) => pre + 1); break;
                default:
                    break;
            }
        }
        if (qNum == 11){
            const result = makeResult(iToE, sToN, tToF, ptoJ);
            navigator('/test/result', {state: {type: result}});
        }
    };

    function makeResult(iToE, sToN, tToF, ptoJ){
        let result = 'ISTP';

        if (iToE > 1) result = result.replace('I', 'E');
        if (sToN > 1) result = result.replace('S', 'N');
        if (tToF > 1) result = result.replace('T', 'F');
        if (ptoJ > 1) result = result.replace('P', 'J');

        return result;
    };

    return (
      <div>
          <Question>{TestQuestions[qNum].Q}</Question>
          <Item onClick={(e) => onClickButton(1, e)}>{TestQuestions[qNum].A1}</Item>
          <Item onClick={(e) => onClickButton(2, e)}>{TestQuestions[qNum].A2}</Item>
      </div>
    );
}

export default Questions;