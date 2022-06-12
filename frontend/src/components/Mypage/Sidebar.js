import React, { useState } from "react";
import styled from "styled-components";
import Information from "./Information";
import Talking from "./Talking";
import Post from "./Post";
import Withdraw from "./Withdraw";

const Container = styled.div`
  display: flex;
  height: 100%;
  weight: 100%;
`;

const Side = styled.div`
  display: flex;
  border-right: 1px solid #e0e0e0;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  justify-items: center;
  min-width: 160px;
  width: 10rem;
  height: 100%;
`;
const Menu = styled.div`
  margin-top: 30px;
  display: flex;
  flex-direction: column;
`;

const Items = styled.div`
  display: flex;
  flex-direction: column;
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

function Sidebar() {
  const menus = [
    { name: "채팅 내역" },
    { name: "작성한 글" },
    { name: "개인정보 수정" },
    { name: "계정 탈퇴" },
  ];
  const selectComponent = {
    0: <Talking />,
    1: <Post />,
    2: <Information />,
    3: <Withdraw />,
  };
  const [content, setContent] = useState();

  const buttonValueSetting = (e) => {
    setContent(e.target.id);
  };

  console.log(content);
  return (
    <Container>
      <Side>
        <Menu>
          <Items>
            {menus.map((data, index) => {
              return (
                <Item onClick={buttonValueSetting} key={index} id={index}>
                  {data.name}
                </Item>
              );
            })}
          </Items>
        </Menu>
      </Side>
      {selectComponent[content]}
    </Container>
  );
}

export default Sidebar;
