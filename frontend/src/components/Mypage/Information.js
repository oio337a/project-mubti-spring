import React from "react";
import styled from "styled-components";

const Container = styled.div`
  height: 100%;
  weight: 100%;
`;
const Contents = styled.div``;
const Box = styled.div``;
const Text = styled.text``;
const Input = styled.input``;
const Button = styled.button``;

function Information() {
  const onClick = () => {
    alert("수정 하시겠숩니ㄲㅏ??");
  };
  return (
    <Container>
      <Contents>
        <Box>
          <Text>SNS 유형:</Text>
          <Input></Input>
        </Box>
        <Box>
          <Text>닉네임:</Text>
          <Input></Input>
        </Box>
        <Text>중복된 닉네임 입니다.</Text>
        <Box>
          <Text>MBTI</Text>
          <Input></Input>
        </Box>
      </Contents>
      <Button onClick={onClick}>수정</Button>
    </Container>
  );
}

export default Information;
