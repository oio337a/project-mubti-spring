import React from "react";
import styled from "styled-components";

const Svg = styled.svg:hover`

`

function Mubti() {
  return (
    <svg viewbox="0 0 50 50" width="50" height="50">
      <defs>
        <mask id="mask" x="0" y="0" width="100" height="49">
          <rect x="0.5" y="0.5" width="49" height="49" fill="#fff" />
          <text x="20" text-anchor="middle" y="50" dy="0">
            S
          </text>
          <text x="25" id="ltrV" text-anchor="middle" y="50" dy="0">
            V
          </text>
          <text x="30" text-anchor="middle" y="50" dy="0">
            G
          </text>
        </mask>
      </defs>
      <rect
        x="0.5"
        y="0.5"
        width="49"
        height="49"
        mask="url(#mask)"
        fill-opacity="1"
        fill="#E3DFD2"
      />
    </svg>
  );
}

export default Mubti;
