import axios from 'axios';
import api from "../utils/api";
import {useSelector} from "react-redux";

const BOARD_API_BASE_URL = "http://localhost:8080/posts";

class PostsService {
    getBoards() {
        const token = useSelector((state) => state.user.value);
        return axios.get(BOARD_API_BASE_URL, {headers: {Authorization: `Bearer ${token.accessToken}`}});
    }
}

export default new PostsService();