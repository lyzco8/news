<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册页面</title>
    <%@include file="/WEB-INF/pages/inc.jsp" %>
    <script type="text/javascript" src="js/reg.js"></script>
    <script type="text/javascript" src="js/reset.js"></script>
</head>
<body>
<%@include file="/WEB-INF/pages/modal.jsp" %>
<c:if test="${not empty msg}">
    <script>
        $(".modal").modal("show");
    </script>
</c:if>
<div class="container">
    <div class="row">
        <div class="col-xs-4 col-xs-offset-4">
            <form method="post">
                <input type="hidden" name="op" value="reg">

                <p class="form-group">
                    <label for="n1">用户名</label>
                    <input class="form-control" type="text" name="name" id="n1">
                </p>
                <p class="form-group">
                    <label for="p1">密码</label>
                    <input class="form-control" type="password" name="pass" id="p1">
                </p>
                <p class="form-group">
                    <label for="rp1">再次输入密码</label>
                    <input class="form-control" type="password" name="repass" id="rp1">
                </p>
                <p class="form-group">
                    <label for="e1">邮箱</label>
                    <input class="form-control" type="email" name="email" id="e1">
                </p>
                <p class="form-group">
                    <label>状态</label>
                    <input type="radio" name="state" id="s2" value="1" checked>
                    <label for="s2">普通用户</label>
                    <input type="radio" name="state" id="s3" value="2">
                    <label for="s3">管理员</label>
                    <input type="radio" name="state" id="s4" value="3">
                    <label for="s4">超级管理员</label>
                </p>
                <p class="form-group text-center">
                    <button class="btn1 btn-sm btn-primary" type="button" onclick="location='user'">注册</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn2 btn-sm btn-danger" id="res">清空</button>
                </p>
            </form>
        </div>
    </div>
</div>
</body>
</html>
