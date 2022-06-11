import axios from 'axios';
import api from "../utils/api";

const BOARD_API_BASE_URL = "http://localhost:8080/api/v1/posts";

class PostsService {
    getBoards() {
        return api.get(BOARD_API_BASE_URL);
    }
}

export default new PostsService();