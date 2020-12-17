// 此脚本文件处理评论页面信息
$(function () {
    //根据关键字模糊查找
    $(".btn2").on("click", function () {
        let content = $("#c1").val();
        window.location.replace("comment.do?word=" + content + "&state=-1");
    });

});