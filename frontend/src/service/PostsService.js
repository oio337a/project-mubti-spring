import axios from 'axios';
import userRequestApi from "../utils/userRequestApi";

class PostsService {
    getBoards() {
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

    savePost(category, content, title, id) {
        return userRequestApi({
            url: `/posts`,
            method: "post",
            data: {
                post_cateroty: category,
                post_content: content,
                post_title: title,
                userId: id
            }
        })
    }

}

export default new PostsService();