import { useNavigate } from 'react-router-dom';

function LoginBtn({provider}){
    let navigate = useNavigate();

    const onClick = () => {
        navigate(`/oauth2/authorization/${provider}?redirect_uri=http://localhost:3000/oauth/redirect`);
    }
    return (
        <div>
          <img
            src = {`${process.env.REACT_APP_LOGIN_IMG}${provider}.png`}
            alt = {`${provider} 로그인`}
            width={200}
            onClick={onClick}
          />
        </div>
    );
}

export default LoginBtn;