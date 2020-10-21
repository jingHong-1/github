// 提交回复
function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();
    comment2target(questionId, 1, content);

}


//封装评论
function comment2target(targetId, type, content) {
    if (!content) {
        alert("不能回复空内容");
    }

    $.ajax({

        type: "POST",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": targetId,
            "content": content,
            "type": type

        }),
        success: function (response) {
            if (response.code == 200) {
                // //隐藏掉回复框
                // $("#comment_section").hide()
                window.location.reload();
            } else {
                if (response.code == 2003) {
                    var isAccepted = confirm(response.message);
                    if (isAccepted) {
                        window.open("https://gitee.com/oauth/authorize?client_id=59b71ba336407dedac6d22233520478ef5d5b799a1ab931a14112545dab06dbe&redirect_uri=http://localhost:8080/callback&response_type=code")
                        window.localStorage.setItem("closable", true);
                    }

                } else {
                    alert(response.message);
                }

            }
        },
        dataType: "json"
    });
}

//二级评论
function comment(e) {
    var commentId = e.getAttribute("data-id");
    var content = $("#input-" + commentId).val();
    comment2target(commentId, 2, content);

}

// 展开二级评论

function collapseComments(e) {
    var id = e.getAttribute("data-id");
    var comments = $("#comment-" + id);
    var twoComment = $("#twoComment-" + id);
    //获取一下二级评论的状态
    var collapse = e.getAttribute("data-collapse");
    if (collapse) {
        // 折叠二级评论
        twoComment.removeClass("in");
        comments.removeClass("in");
        e.removeAttribute("data-collapse");
        e.classList.remove("active");
    } else {
        //获取评论值
        $.getJSON('/comment/' + id, function (data) {
            var subCommentContainer = $("#comment-" + id);
            subCommentContainer.html("");
            var htm = "";
            $.each(data.data, function (index, comment) {
                // var c = $("<div/>", {
                //     "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 comments",
                //     html: comment.content
                // });


                htm += "<div class='col-lg-12 col-md-12 col-sm-12 col-xs-12 comments'><div class='media'><div class='media-left media-middle'>";

                htm += "<a><img class='media-object img-rounded 'src=" + comment.user.avatarUrl + "></a></div>";
                htm += "<div class='media-body'><h5 class='media-heading'> <span>" + comment.user.name + "</span></h5>";

                htm += " <div>" + comment.content + "</div><div class='menu'> <span class='pull-right'>" + formateDate(comment.gmtCreate) + "</span>"


                htm += "</div></div></div></div>";

            });
            //追加标签
            // <!--//评论输入框-->


            subCommentContainer.html(htm);
            // 展开二级评论
            comments.addClass("in");
            twoComment.addClass("in");
            e.classList.add("active");
            //标记额二级评论状态
            e.setAttribute("data-collapse", "in");

        })
    }

}

// 格式化时间
function formateDate(timestamp) {
    var date = new Date(timestamp);
    var y = 1900 + date.getYear();
    var m = "0" + (date.getMonth() + 1);
    var d = "0" + date.getDate();
    return y + "-" + m.substring(m.length - 2, m.length) + "-" + d.substring(d.length - 2, d.length);
}

// 选择标签
function selectTag(value) {
    var prevalue = $("#tag").val();
    if (prevalue.indexOf(value) == -1) {
        if (prevalue) {
            $("#tag").val(prevalue + ',' + value);
        } else {
            $("#tag").val(value);
        }
    }
}