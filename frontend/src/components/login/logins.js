import LoginBtn from "./login_btn";
import React from "react";

function Logins(){
    const providers = ["naver", "kakao", "google"];

    return (
        <div>
            {providers.map((provider, index) => (
                <LoginBtn provider={provider} key={index} />
            ))}
        </div>
    );
}

export default Logins;