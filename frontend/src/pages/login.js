import LoginWithKakao from "./login_kakao.js"
import LoginWithNaver from "./login_naver.js"
import LoginWithGoogle from "./login_google.js"

function Login(){
    return(
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