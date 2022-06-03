import LoginWithKakao from "../components/login_kakao";
import LoginWithNaver from "../components/login_naver";
import LoginWithGoogle from "../components/login_google";

function Login() {
  return (
    <div>
      <LoginWithKakao />
      <br />
      <LoginWithNaver />
      <br />
      <LoginWithGoogle />
    </div>
  );
}

export default Login;
