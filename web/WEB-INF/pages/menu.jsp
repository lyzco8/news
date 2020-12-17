<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>后台管理</title>
    <%@include file="/WEB-INF/pages/inc.jsp" %>
</head>
<body>
<div style="width:90%;margin-left:20px;display: inline-block;">
    <span style="font-size: 36px;font-weight: bold">新闻管理系统</span>
    <div style="float: right">
        <span style="font-size: 14px;font-weight: bold">欢迎：【${user.username}】</span>
        <a href="user">返回前台</a>
        <a href="user?op=logoff">安全退出</a>
    </div>
</div>
<br><br><br>
<ul class="nav nav-tabs nav-justified">
    <li role="presentation"><a href="category.do" id="a1">分类管理</a></li>
    <li role="presentation"><a href="detail.do" id="a2">新闻管理</a></li>
    <li role="presentation"><a href="comment.do" id="a3">评论管理</a></li>
    <li role="presentation"><a href="userAdmin.do" id="a4">用户管理</a></li>
</ul>
</body>
</html>
