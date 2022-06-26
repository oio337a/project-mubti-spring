import React from "react";

function LoginBtn({provider}){
return (
        <div>
            <a href={`http://localhost:8080/oauth2/authorization/${provider}?redirect_uri=http://localhost:3000/oauth/redirect`}>
                <img
                    src = {`${process.env.REACT_APP_LOGIN_IMG}${provider}.png`}
                    alt = {`${provider} 로그인`}
                    width={200}
                />
            </a>
        </div>
    );
}

export default LoginBtn;