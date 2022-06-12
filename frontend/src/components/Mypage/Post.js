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

function Post() {
  return (
    <Container>
      <Table>
        <Thead>
          <Tr>
            <Th>카테고리</Th>
            <Th>제목</Th>
            <Th>날짜</Th>
            <Th>조회</Th>
            <Th>추천</Th>
          </Tr>
        </Thead>
        <Tbody>
          <Tr>
            <Td>ENFP</Td>
            <Td>enfp 특</Td>
            <Td>6/7</Td>
            <Td>0</Td>
            <Td>0</Td>
          </Tr>
        </Tbody>
      </Table>
    </Container>
  );
}

export default Post;
