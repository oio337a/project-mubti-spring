import { gapi } from "gapi-script";
import React, { useEffect } from "react";
import GoogleLogin from "react-google-login";
import img_src from "../img/btn_Google.png";

const key =
  "20496405764-022dns1s96qtq81rrcrmhaouf91itkkk.apps.googleusercontent.com";

function LoginWithGoogle() {
  return (
    <GoogleLogin
      clientId={key}
      onSuccess={(e) => {
        console.log("로그인 성공 e:", e);
      }}
      onFailure={(err) => {
        console.log("fail", err);
      }}
      cookiePolicy={"single_host_origin"}
      render={({ onClick }) => (
        <img
          onClick={(e) => {
            e.preventDefault();
            onClick();
          }}
          src={img_src}
          alt="구글 로그인 버튼"
          width="200"
        />
      )}
    />
  );
}

export default LoginWithGoogle;
