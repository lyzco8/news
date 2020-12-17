// 此脚本文件用于处理用户管理页面数据
$(function () {
    //关键字模糊查询
    $(".btn2").on("click", function () {
        let username = $("#n1").val();
        window.location.replace("userAdmin.do?word=" + username);
    });
});