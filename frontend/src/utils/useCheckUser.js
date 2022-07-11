import {useSelector} from "react-redux";

export default function useCheckUser(acceptedRole){
    const value = useSelector((state) => state.root.user.value);
    let timeNow = new Date;

    if (value.expiryTime < timeNow.getTime() || value.accessToken == "")
        return {
            approval: false,
            redirectURL: process.env.REACT_APP_LOGIN_URL
        };

    console.log("value.role", value.role, "acceptedRole", acceptedRole);
    switch (acceptedRole){
        case "COMPLETE_USER" : {
            if (value.role === "COMPLETE_USER") {
                return {
                    approval: true,
                    redirectURL: null
                };
            }
            if (value.role === "ROLE_INCOMPLETE_USER")
                return {
                    approval: false,
                    redirectURL: `${process.env.REACT_APP_BASE_URL}/user/create`
                };
        }
        case "ROLE_INCOMPLETE_USER" : {
            if (value.role === "ROLE_INCOMPLETE_USER")
                return {
                    approval: true,
                    redirectURL: null
                };
        }
        default :
            return {
                approval: false,
                redirectURL: process.env.REACT_APP_BASE_URL
            };
    }
}