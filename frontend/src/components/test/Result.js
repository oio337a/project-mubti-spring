import MAIN_DATA from "../../data/PersonalType";
import React from "react";
import {useEffect, useState} from "react";
import styled from "styled-components";

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

function Result({type}) {
    const [selectedType, setSelectedType] = useState(type);
    const [typeDesrc, setTypeDesrc] = useState("");

    const onClickItem = (e, type) => {
      setSelectedType(type);
    };

    useEffect(() => {
        MAIN_DATA.forEach((data) => {
            if (data.text == selectedType) setTypeDesrc(data.descr);
        })
    }, [selectedType]);

    return (
        <div>
            <div>내 유형</div>
            <div>{selectedType}</div>
            <div>{typeDesrc}</div>
            <div>다른 유형도 보기</div>
            <div>
                {MAIN_DATA.map((data, index) => (
                    <Item key={index} onClick={(e) => onClickItem(e, data.text)}>{data.text}</Item>
                ))}
            </div>
        </div>
    );
}

export default Result;