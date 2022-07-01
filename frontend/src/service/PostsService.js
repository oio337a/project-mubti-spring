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

    getPostByCategory(category, page) {
        return userRequestApi({
            url: "/posts",
            method: "get",
            data: {
                category_type: category,
                page: page
            }
        })
    }

    getSearchedPost(category, page, search, searchType){
        return userRequestApi({
            url: "/posts",
            method: "get",
            data: {
                category_type: category,
                page: page,
                search_type: searchType,
                keyword: search
            }
        })
    }

    savePost(category, content, title) {
        return userRequestApi({
            url: `/posts`,
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
            url: `/posts/${id}`,
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
            url: `/posts/${id}`,
            method: "delete",
        })
    }

    clickVote(id){
        return userRequestApi({
            url:`/posts/${id}/vote`,
            method: "post"
        })
    }

    saveComment(){

    }

    deleteComment(){

    }
}

export default new PostsService();