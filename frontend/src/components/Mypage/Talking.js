import React from "react";
import styled from "styled-components";

const Container = styled.div`
  height: 100%;
  weight: 100%;
`;

const Table = styled.table``;
const Thead = styled.thead``;
const Tbody = styled.tbody``;
const Tr = styled.tr``;
const Th = styled.th``;
const Td = styled.td``;

function Talking() {
  return (
    <Container>
      <Table>
        <Thead>
          <Tr>
            <Th>닉네임</Th>
            <Th>대화내용</Th>
            <Th>날짜</Th>
          </Tr>
        </Thead>
        <Tbody>
          <Tr>
            <Td>손영원</Td>
            <Td>안녕하세요.</Td>
            <Td>2022</Td>
            <button>삭제 버튼</button>
          </Tr>
        </Tbody>
      </Table>
    </Container>
  );
}

export default Talking;
