import axios from 'axios';
import api from "../utils/api";

class PostsService {
    getBoards() {
        return api({
            url: "/posts",
            method: "get",
        })
    }

    getPost(id) {
        return api({
            url: `/posts/${id}`,
            method: "get",
        })
    }

}

export default new PostsService();