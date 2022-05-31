import NaverLogin from 'react-naver-login';
import img_src from "../img/btn_Naver.png";

const key = "QZNvmQ2FtcEOoyE5Qefz";
const callbackUrl = "http://localhost:3000/login";

function LoginWithNaver() {
    return(
        <NaverLogin
            clientId={key}
            callbackUrl= {callbackUrl}
            isPopup = {true}
            onSuccess = {(e) => {console.log("로그인 성공 e:", e);}}
            onFailure = {(err) => {
                console.log("fail", err);
            }}
            render = {({ onClick }) => (
                <img
                    onClick={(e) => {
                        e.preventDefault();
                        onClick();
                    }}
                    src={img_src}
                    alt="네이버 로그인 버튼"
                    width="200"
                />
            )}
        />
    );
}

export default LoginWithNaver;