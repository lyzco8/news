// 此脚本文件处理分类页面数据
$(function () {
    //分页信息修改
    $(".btn1").on("click", function () {
        let id = $("#i1").val();
        let name = $("#n1").val();
        if(id == null || id === "" || id === 'undefined'){
            alert("有空输入，无法修改");
        }else if(name == null || name === "" || name === 'undefined'){
            alert("有空输入，无法修改");
        }else {
            window.location.replace("category.do?op=modify&id="+id+"&name=" + name);
        }
    });

    //分页信息添加
    $(".btn2").on("click", function () {
        let name = $("#n1").val();
        if (name == null || name === "" || name === 'undefined') {
            alert("无效输入，请重输")
        } else {
            window.location.replace("category.do?op=add&name=" + name);
        }
    });
});