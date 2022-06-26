import axios from 'axios';
import userRequestApi from "../utils/userRequestApi";

class PostsService {
    getBoard() {
        return userRequestApi({
            url: "/post",
            method: "get",
        })
    }

    getPagedPosts(page) {
        return userRequestApi({
            url: `/post?page=${page}`,
            method: "get"
        })
    }

    getPost(id) {
        return userRequestApi({
            url: `/post/${id}`,
            method: "get",
        })
    }

    savePost(category, content, title) {
        return userRequestApi({
            url: `/post`,
            method: "post",
            data: {
                post_cateroty: category,
                post_content: content,
                post_title: title,
            }
        })
    }

    modifyPost(id, content, title) {
        return userRequestApi({
            url: `/post/${id}`,
            method: "put",
            data: {
                post_content: content,
                post_title: title,
            }
        })
    }

    deletePost(id) {
        return userRequestApi({
            url: `/post/${id}`,
            method: "delete",
        })
    }

}

export default new PostsService();