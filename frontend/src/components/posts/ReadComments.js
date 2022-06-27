import dateformat from "dateformat";
import {useEffect, useState} from "react";
import React from "react";

function ReadComments({allComments}){
    console.log(allComments);
    const maxPage = parseInt((allComments.length - 1)/10) + 1;
    const amount = 10;
    const [currentPage, setCurrentPage] = useState(maxPage);
    const [comments, setComments] = useState([]);

    const onClickPage = (e, page) => {
        setCurrentPage(page);
    }

    useEffect(() => {
        const startComment = (currentPage - 1) * amount + 1;
        if (allComments.length < currentPage * 10)
            setComments(allComments.slice(startComment));
        else
            setComments(allComments.slice(startComment, currentPage * 10));
    }, [currentPage])

    return(
        <div>
            {comments.map((comment, index) =>
                <div key={index}>
                    <div> num {comment.commentSeq}</div>
                    <div> user {comment.user.userAlias}</div>
                    <div> time {dateformat(comment.commentDate, 'yyyy/mm/dd')}</div>
                    <div> {comment.commentContent}</div>
                </div>
            )}
            {currentPage < 3 ? null : <button onClick={(e) => onClickPage(e, 1)}>1</button>}
            {currentPage < 4  ? null : <div>...</div>}
            {currentPage === 1 ? null : <button onClick={(e) => onClickPage(e, currentPage - 1)}>{currentPage - 1}</button>}
            {maxPage === 1 ? <div> 댓글이 없습니다. 작성해보세열 </div> : <button onClick={(e) => onClickPage(e, currentPage)}>{currentPage} </button>}
            {maxPage <= currentPage + 1 ? null : <button onClick={(e) => onClickPage(e, currentPage + 1)}>{currentPage + 1}</button>}
            {maxPage - currentPage < 3 ? null : <div>...</div>}
            {maxPage === currentPage ? null : <button onClick={(e) => onClickPage(e, maxPage)}>{maxPage}</button>}
        </div>
    );
}

export default ReadComments;