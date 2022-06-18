import {useSelector} from "react-redux";

function Main(){
    const token = useSelector((state) => state.user.value);

    console.log("im....here",token);
    return (
        <div>{token.role}</div>
    );
}

export default Main;