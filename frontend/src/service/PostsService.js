import axios from 'axios';
import userRequestApi from "../utils/userRequestApi";

class PostsService {
    getBoards() {
        return userRequestApi({
            url: "/posts",
            method: "get",
        })
    }

    getPost(id) {
        return userRequestApi({
            url: `/posts/${id}`,
            method: "get",
        })
    }

}

export default new PostsService();