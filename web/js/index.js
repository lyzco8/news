// 此脚本文件处理index界面信息
$(function () {
    // 获取五个state不为0的分类名称
    // 利用Ajax从doAjax的doGetCate方法中取分类数据
    // 利用jquery将分类名称动态写入页面
    $.getJSON("doAjax", "op=getCate&state=0&num=5", function (ret) {
        if (ret.errorCode) {
            alert(ret.message);
        } else {
            for (let i = 0; i < ret.data.length; i++) {
                let name = ret.data[i].name;
                let id = ret.data[i].id;
                let li = $("<li>");
                let a = $("<a>");
                a.attr({href: "user?categoryId=" + id + "&state=0", id: "a" + i}).text(name).appendTo(li);
                li.appendTo($("[class=clearfix]"));
            }
        }
    });

    // 获取七个state不为0的分类名称
    // 利用Ajax从doAjax的doGetCate方法中取分类数据
    // 利用jquery将分类名称动态写入页面
    $.getJSON("doAjax", "op=getCate&state=0&num=7", function (ret) {
        if (ret.errorCode) {
            alert(ret.message);
        } else {
            for (let i = 0; i < ret.data.length; i++) {
                let name = ret.data[i].name;
                let id = ret.data[i].id;
                let li = $("<li>");
                let a = $("<a>");
                a.attr({href: "user?categoryId=" + id + "&state=0"}).text(name).appendTo(li);
                li.appendTo($("[class=ul4x]"));
            }
        }
    });

    // 获取所有state不为0的分类名称
    // 利用Ajax从doAjax的doGetCate方法中取分类数据
    // 利用jquery将分类名称动态写入页面
    $.getJSON("doAjax", "op=getCate&state=0&num=2147483647", function (ret) {
        if (ret.errorCode) {
            alert(ret.message);
        } else {
            for (let i = 0; i < ret.data.length; i++) {
                let name = ret.data[i].name;
                let id = ret.data[i].id;
                $("<option>").attr({'value': id, 'id': 'opt'}).text(name).appendTo($("#c1"));
            }
        }
    });

    // 点击左边go按钮，跳转至user进行关键字模糊查询
    $("[id=go-btn1]").on("click", function () {
        let title = $("#t1").val();
        window.location.replace("user?word=" + title + "&state=0");
    });

    //点击右边的go按钮，跳转至user进行分类id查询
    $("[id=go-btn2]").on("click", function () {
        $("#ul4js").html("");
        let categoryId = $("#c1").val();
        window.location.replace("user?categoryId=" + categoryId + "&state=0");
    });

    //获取3条最新新闻
    //利用Ajax从doAjax中的doGetDetailsByNum方法获取新闻信息
    //利用jquery将新闻信息动态写入页面
    $.getJSON("doAjax", "op=getDetailsByNum&categoryId=0" + "&state=0&num=3", function (ret) {
        if (ret.errorCode) {
            alert(ret.message);
        } else {
            for (let i = 0; i < ret.data.length; i++) {
                let title = ret.data[i].title;
                let li = $("<li>");
                let id = ret.data[i].id;
                $("<a>").attr("href", "user?op=comm&id=" + id).text(title).appendTo(li);
                li.appendTo($("[id=ul4zx]"));
            }
        }
    });

    //获取三条最新的有评论的新闻
    //利用Ajax从doAjax中的doGetDetailsByComm方法获取有评论的新闻信息
    //利用jquery将新闻信息动态写入页面
    $.getJSON("doAjax", "op=getDetailsByComm&id=0" + "&state=0&num=3", function (ret) {
        if (ret.errorCode) {
            alert(ret.message);
        } else {
            for (let i = 0; i < ret.data.length; i++) {
                let title = ret.data[i].title;
                let li = $("<li>");
                let id = ret.data[i].id;
                $("<a>").attr("href", "user?op=comm&id=" + id).text(title).appendTo(li);
                li.appendTo($("[id=ul4pl]"));
            }
        }
    });

    //添加评论
    //产生点击事件后利用Ajax通过doAjax中的doAdd方法将评论信息添加至数据库
    $("[class=comment-btn]").on("click", function () {
        let newsId = $("[class=comment-newsId]").val();
        let authorId = $("[class=comment-authorId]").val();
        let content = $("[class=comment-textarea]").val();
        if (content == null || content === "" || content === 'undefined') {
            alert("不能为空");
            return;
        }
        $.getJSON("doAjax", "op=addComm&newsId=" + newsId + "&authorId=" + authorId + "&content=" + content, function (ret) {
            if (ret.errorCode) {
                alert(ret.message);
            } else {
                alert(ret.data);
                window.location.reload();
            }
        });
    });

});