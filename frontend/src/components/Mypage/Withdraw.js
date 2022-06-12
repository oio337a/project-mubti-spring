import React from "react";
import styled from "styled-components";
import { Button } from "../Tools";

const Container = styled.div`
  height: 100%;
  weight: 100%;
`;
const Announce = styled.h4`
  color: red;
`;

function Withdraw() {
  const onClick = () => {
    alert("회원 탈퇴 리얼리??");
  };
  return (
    <Container>
      <Announce>
        게시글은 삭제되지 않고 보존되며, 원치않으시는 경우에는 탈퇴 전
        작성하셨던 게시글을 삭제하시기 바랍니다. 탈퇴 후 동일한 서비스로
        로그인하시면 신규회원으로 가입됩니다.
      </Announce>
      <Button onClick={onClick}>회원 탈퇴 요청</Button>
    </Container>
  );
}

export default Withdraw;
