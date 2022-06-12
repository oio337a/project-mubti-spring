import React from "react";
import styled from "styled-components";
import { Wraper, Header, Footer, Button } from "../components/Tools";
import MAIN_DATA from "../data/PersonalType";

const Container = styled.div``;
const Category = styled.h2``;
const Content = styled.div``;
const Text = styled.text``;
const InputNick = styled.input``;
const InputMbti = styled.select``;
const Select = styled.option``;

function Info() {
  return (
    <Wraper>
      <Header />
      <Container>
        <Category>추가 정보 입력</Category>
        <Content>
          <Text>닉네임</Text>
          <InputNick />
        </Content>
        <Content>
          <Text>MBTI</Text>
          <InputMbti>
            {MAIN_DATA.map((data) => {
              return (
                <Select name={data.text} key={data.id}>
                  {data.text}
                </Select>
              );
            })}
          </InputMbti>
        </Content>
        <Button>회원가입하기</Button>
      </Container>
      <Footer />
    </Wraper>
  );
}

export default Info;
