import React, {useEffect, useState} from "react";
import styled from "styled-components";
import { Wraper, Header, Footer} from "../Tools";
import MAIN_DATA from "../../data/PersonalType";
import UserService from "../../service/UserService";
import {useDispatch, useSelector} from "react-redux";
import useReIssueToken from "../../utils/useReIssueToken";
import axios from "axios";
import {login} from "../../reducers/userReducer";
import {useNavigate} from "react-router-dom";
import {Buffer} from "buffer";
import parseToken from "../../utils/parseToken";
import issueToken from "../../utils/issueToken";

const Container = styled.div``;
const Category = styled.h2``;
const Content = styled.div``;
const Text = styled.text``;
const InputNick = styled.input``;
const InputMbti = styled.select``;
const Select = styled.option``;
const Button = styled.button`
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

function Info() {
    const token = useSelector((state) => state.root.user.value);
    const dispatch = useDispatch();
    let navigator = useNavigate();

    const [type, setType] = useState("TYPE");
    const [alias, setAlias] = useState("");
    const [checked, setChecked] = useState(true);
    const [newToken, setNewToken] = useState("");

    useEffect(() => {
        if (newToken !== "") {
            const [expiryTime, role, id] = parseToken(newToken);
            dispatch(login({
                accessToken: newToken,
                expiryTime: expiryTime * 1000,
                role: role,
                id: id
            }));
            navigator("/");
        }
    }, [newToken]);

    const onClickBtn = async () => {
        if (checked === false) {
            setAlias("");
            alert("중복된 닉네임");
        }
        else if (alias.length === 0) {
            alert("닉네임을 입력하세요.");
        }
        else if (type === "TYPE"){
            alert("유형을 선택하세요.");
        }
        else {
            await UserService.saveInfo(type, alias);
            setNewToken(
                await issueToken(token.accessToken)
                    .then((res) => res.data)
            );
        }
    }

    const onChangeType = (e) => {
        console.log(e.target.value);
        setType(e.target.value);
    }

    const onChangeAlias = (e) => {
        const newAlias = e.target.value;
        setAlias(newAlias);
        UserService.checkAilas(newAlias)
            .then((res) => {
                if (res.status === 200) setChecked(true);})
            .catch(e => {
                if(e.response.status === 409) setChecked(false);
        })
    }

    return (
        <Wraper>
            <Header/>
            <Container>
                <Category>추가 정보 입력</Category>
                <Content>
                    <Text>닉네임</Text>
                    <InputNick maxlength="15" onChange={onChangeAlias}/>
                    <Text>{checked ? (alias.length === 0 ? "닉네임을 입력하세요" : "가능한 닉네임") : "중복 닉네임" }</Text>
                </Content>
                <Content>
                    <Text>MBTI</Text>
                    <InputMbti onChange={onChangeType} >
                        <Select name={"default"}>TYPE</Select>
                        {MAIN_DATA.map((data, index) => (
                            <Select name={data.text} key = {index}>
                                {data.text}
                            </Select>
                            ))}
                    </InputMbti>
                </Content>
                <Button onClick={onClickBtn}>회원가입하기</Button>
            </Container>
            <Footer/>
        </Wraper>
    );
}

export default Info;
