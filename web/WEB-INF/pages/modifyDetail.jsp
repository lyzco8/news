<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${user.state lt 2}">
    <c:redirect url="user"/>
</c:if>
<html>
<head>
    <title>修改信息</title>
    <%@include file="inc.jsp" %>
    <script src="ckeditor/ckeditor.js"></script>
</head>
<body>
<%@include file="menu.jsp" %>
<%@include file="/WEB-INF/pages/modal.jsp" %>
<c:if test="${not empty msg}">
    <script>
        $(".modal").modal("show");
    </script>
</c:if>
<c:set var="det" value="${detail}"/>
<form action="modify.do?op=detail&id=${det.id}" method="post">
    <div class="container">
        <p class="form-group">
            <label for="i1">ID</label>
            <input class="form-control" type="number" name="id" value="${det.id}" id="i1" disabled>
        </p>
        <p class="form-group">
            <label for="i2">分类ID</label>
            <input class="form-control" type="number" name="categoryId" value="${det.categoryId}" id="i2">
        </p>
        <p class="form-group">
            <label for="t1">新闻标题</label>
            <input class="form-control" type="text" name="title" value="${det.title}" id="t1">
        </p>
        <p class="form-group">
            <label for="s1">新闻摘要</label>
            <input class="form-control" type="text" name="summary" value="${det.summary}" id="s1">
        </p>
        <p class="form-group">
            <label for="c1">新闻内容</label>
            <input class="form-control" type="text" name="content" value="${det.content}" id="c1">
        </p>
        <p class="form-group">
            <label for="a1">作者ID</label>
            <input class="form-control" type="number" name="authorId" value="${det.authorId}" id="a1">
        </p>
        <p class="form-group text-center">
            <button class="btn btn-sm btn-primary">修改</button>
            <button class="btn btn-sm btn-danger" type="reset">清空</button>
        </p>
    </div>
</form>
</body>
</html>
