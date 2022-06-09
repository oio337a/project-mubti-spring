import React from "react";
import "../css/adjust.css";

function Main() {
  return (
    <>
      <svg viewbox="0 0 50 50" width="50" height="50">
        <defs>
          <mask id="mask" x="0" y="0" width="100" height="49">
            <rect x="0.5" y="0.5" width="99.6%" height="99.6%" fill="#fff" />
            <text x="36%" text-anchor="middle" y="99.6%" dy="0">
              Mu
            </text>
            <text x="55%" id="ltrV" text-anchor="middle" y="99.6%" dy="0">
              bt
            </text>
            <text x="65%" text-anchor="middle" y="99.6%" dy="0">
              i
            </text>
          </mask>
        </defs>
        <rect
          x="0.5"
          y="0.5"
          width="99.6%"
          height="99.6%"
          mask="url(#mask)"
          fill-opacity="1"
          fill="#E3DFD2"
        />
      </svg>
    </>
  );
}

export default Main;
