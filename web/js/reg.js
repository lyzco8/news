// 此脚本文件处理注册页面信息
$(function () {
    //注册页面信息判断和提交
    let isValidName = false;
    let isValidPass = false;
    let isValidEmail = false;
    $("#n1").on("blur", function () {
        let name = $("#n1").val();
        $.getJSON("doAjax", "op=checkMsg&username=" + name, function (ret) {
            if (!ret.errorCode) {
                if (ret.data.name > 0) {
                    isValidName = false;
                    alert("用户名已存在");
                    return;
                }
            }
            let reg = /^[a-zA-Z0-9一-龥]{2,8}$/;
            let rt = name.match(reg);
            if (rt == null) {
                isValidName = false;
                alert("用户名格式不正确");
            } else {
                isValidName = true;
            }
        });
    });
    $("#rp1").on("blur", function () {
        let pass = $("#p1").val();
        let repass = $("#rp1").val();
        let reg = /^[a-zA-Z0-9]{4,16}$/;
        let rt = pass.match(reg);
        if (rt == null) {
            isValidPass = false;
            alert("密码格式不正确");
        } else {
            isValidPass = true;
        }
        let rp = repass.match(reg);
        if (rp == null) {
            isValidPass = false;
            alert("密码格式不正确");
        } else {
            isValidPass = true;
        }
        if (pass !== repass) {
            isValidPass = false;
            alert("两次密码输入不同")
        }
    });
    $("#e1").on("blur", function () {
        let email = $("#e1").val();
        $.getJSON("doAjax", "op=checkMsg&email=" + email, function (ret) {
            if (!ret.errorCode) {
                if (ret.data.email > 0) {
                    isValidEmail = false;
                    alert("邮箱已存在");
                    return;
                }
            }
            let reg = /^\w+@\w+\.\w+$/;
            let rt = email.match(reg);
            if (rt == null) {
                isValidEmail = false;
                alert("邮箱格式不正确");
            } else {
                isValidEmail = true;
            }
        });
    });
    $(".btn1").on("click", function () {
        if (isValidName && isValidPass && isValidEmail) {
            $.post("user", $('form').serialize(),
                function (ret) {
                }
            );
        } else {
            alert("注册失败");
        }
    });
});