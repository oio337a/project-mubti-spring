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
                categoryType: category,
                postContent: content,
                postTitle: title,
            }
        })
    }

    modifyPost(id, category, content, title) {
        console.log(content, title);
        return userRequestApi({
            url: `/post/${id}`,
            method: "put",
            data: {
                categoryType: category,
                postContent: content,
                postTitle: title,
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