<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${user.state lt 2}">
    <c:redirect url="user"/>
</c:if>
<html>
<head>
    <title>添加新闻</title>
    <%@include file="/WEB-INF/pages/inc.jsp" %>
    <script src="ckeditor/ckeditor.js"></script>
</head>
<body>
<%@include file="menu.jsp"%>
<%@include file="/WEB-INF/pages/modal.jsp" %>
<c:if test="${not empty msg}">
    <script>
        $(".modal").modal("show");
    </script>
</c:if>
<form action="detail.do" method="post">
    <input type="hidden" name="op" value="add">
    <div class="container">
        <p class="form-group">
            <label for="c1">新闻类别ID</label>
            <input class="form-control" type="number" name="categoryId" id="c1">
        </p>
        <p class="form-group">
            <label for="t1">新闻标题</label>
            <input class="form-control" type="text" name="title" id="t1">
        </p>
        <p class="form-group">
            <label for="s1">新闻摘要</label>
            <input class="form-control" type="text" name="summary" id="s1">
        </p>
        <p class="form-group">
            <label>新闻内容</label>
            <textarea name="content" class="ckeditor"></textarea>
        </p>
        <input type="hidden" name="authorId" value="${user.id}">
        <br>
        <button>提交</button>
    </div>
</form>
</body>
</html>
