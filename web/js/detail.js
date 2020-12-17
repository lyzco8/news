// 此脚本文件处理新闻页面信息
$(function () {
    //获取全部新闻
    $.getJSON("doAjax", "op=getCate&state=-1&num=2147483647", function (ret) {
        if (ret.errorCode) {
            alert(ret.message);
        } else {
            for (let i = 0; i < ret.data.length; i++) {
                let name = ret.data[i].name;
                $("<option>").attr({'value': i + 1, 'id': 'opt'}).text(name).appendTo($("#c1"));
            }
        }
    });

    //根据分类id查找对应的新闻
    $(".btn1").on("click", function () {
        let categoryId = $("#c1").val();
        window.location.replace("detail.do?categoryId=" + categoryId + "&state=-1");
    });

    //根据关键字查找对应的新闻
    $(".btn2").on("click", function () {
        let title = $("#t1").val();
        window.location.replace("detail.do?word=" + title + "&state=-1");
    });
});