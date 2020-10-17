function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();
    if (!content){
        alert("不能回复空内容");
    }

    $.ajax({

        type: "POST",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": questionId,
            "content": content,
            "type": 1

        }),
        success: function (response) {
            if (response.code == 200){
                // //隐藏掉回复框
                // $("#comment_section").hide()
                window.location.reload();
            }  else {
                if (response.code == 2003){
                    var isAccepted = confirm(response.message);
                    if (isAccepted){
                        window.open("https://gitee.com/oauth/authorize?client_id=59b71ba336407dedac6d22233520478ef5d5b799a1ab931a14112545dab06dbe&redirect_uri=http://localhost:8080/callback&response_type=code")
                        window.localStorage.setItem("closable",true);
                    }

                } else {
                    alert(response.message);
                }

            }
        },
        dataType: "json"
    });

}