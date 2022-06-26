import userRequestApi from "../utils/userRequestApi";

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