import axios from "axios";
import userRequestApi from "../utils/userRequestApi";

const BASE_URL = "http://localhost:8080";

class UserService {
    saveInfo(type, alias) {
        return userRequestApi({
            url:  "/users",
            method: "put",
            data: {
                mbtiType: type,
                userAlias: alias
            },
        })
    }

    checkAilas(alias) {
        return userRequestApi({
            url:  `/users/alias/${alias}/check`,
            method: "get"
        })
    }
}

export default new UserService();