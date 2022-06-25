import axios from 'axios';
import userRequestApi from "../utils/userRequestApi";

class PostsService {
    getBoard() {
        return userRequestApi({
            url: "/posts",
            method: "get",
        })
    }

    getPagedPosts(page) {
        return userRequestApi({
            url: `/posts?page=${page}`,
            method: "get"
        })
    }

    getPost(id) {
        return userRequestApi({
            url: `/posts/${id}`,
            method: "get",
        })
    }

    savePost(category, content, title) {
        return userRequestApi({
            url: `/posts`,
            method: "post",
            data: {
                post_cateroty: category,
                post_content: content,
                post_title: title,
            }
        })
    }

    modifyPost(id, category, content, title) {
        return userRequestApi({
            url: `/posts/${id}`,
            method: "post",
            data: {
                post_cateroty: category,
                post_content: content,
                post_title: title,
            }
        })
    }

}

export default new PostsService();