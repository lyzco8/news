// 此脚本文件处理article页面信息
$(function () {
    // 获取评论信息，利用Ajax从doAjax的doGetCommByNum方法中取评论
    // 利用jquery将评论信息动态写入页面
    let id = $("#detailId").val();
    $.getJSON("doAjax", "op=getCommByNum&state=0&num=5&id=" + id, function (ret) {
        if (ret.errorCode) {
            alert(ret.message);
        } else {
            let div = $("#div4cm");
            for (let i = 0; i < ret.data.length; i++) {
                let dl = $("<dl>");
                let dt = $("<dt>");
                $("<b>").text((i + 1) + "楼").appendTo(dt);
                $("<b>").text(ret.data[i].username).appendTo(dt);
                let date = new Date(ret.data[i].createDate);
                let createDate = formatDate(date, "yyyy-MM-dd HH:mm:ss");
                $("<span>").addClass("class", "fr").text(createDate).appendTo(dt);
                dt.addClass("class", "comment-top").appendTo(dl);
                let dd = $("<dd>");
                $("<span>").addClass("class", "fr").text(ret.data[i].content).appendTo(dd);
                dd.addClass("class", "comment-body").appendTo(dl);
                dl.appendTo(div);
            }
        }
    });

    // 日期格式化方法
    function formatDate(date, formatter) {
        let str = "";
        let year = date.getFullYear();
        let month = date.getMonth() + 1;
        let day = date.getDate();
        let hour = date.getHours();
        let minute = date.getMinutes();
        let second = date.getSeconds();
        if (month < 10) {
            month = "0" + month;
        }
        if (day < 10) {
            day = "0" + day;
        }
        if (hour < 10) {
            hour = "0" + hour;
        }
        if (minute < 10) {
            minute = "0" + minute;
        }
        if (second < 10) {
            second = "0" + second;
        }
        str = formatter.replace("yyyy", year);
        str = str.replace("MM", month);
        str = str.replace("dd", day);
        str = str.replace("HH", hour);
        str = str.replace("mm", minute);
        str = str.replace("ss", second);
        return str;
    }
});