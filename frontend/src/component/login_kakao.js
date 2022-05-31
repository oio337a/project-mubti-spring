import KakaoLogin from "react-kakao-login";

const key = "69eb29d22c807b3e4be134756e403536";
const img_src = "//k.kakaocdn.net/14/dn/btroDszwNrM/I6efHub1SN5KCJqLm1Ovx1/o.jpg";

function LoginWithKakao(){
    return(
        <KakaoLogin
            token={key}
            onSuccess = {(e) => {console.log("로그인 성공 e:", e);}}
            onFail = {(err) => {
                console.log("fail", err);
            }}
            onLogout={() => {
                console.log("logout");
            }}
            render = {({ onClick }) => (
                <img
                    onClick={(e) => {
                        e.preventDefault();
                        onClick();
                    }}
                    src={img_src}
                    alt="카카오 로그인 버튼"
                    width="200"
                />
            )}
        />
    );
}

export default LoginWithKakao;