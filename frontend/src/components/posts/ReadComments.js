import dateformat from "dateformat";

function ReadComments({comments}){
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
        </div>
    );
}

export default ReadComments;