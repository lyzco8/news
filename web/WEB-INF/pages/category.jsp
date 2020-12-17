<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${user.state lt 2}">
    <c:redirect url="user"/>
</c:if>
<html>
<head>
    <title>分类</title>
    <%@include file="/WEB-INF/pages/inc.jsp" %>
    <script type="text/javascript" src="js/category.js"></script>
    <script type="text/javascript" src="js/reset.js"></script>
</head>
<body>
<%@include file="menu.jsp" %>
<%@include file="/WEB-INF/pages/modal.jsp" %>
<c:if test="${not empty msg}">
    <script>
        $(".modal").modal("show");
    </script>
</c:if>
<table class="table">
    <thead>
    <tr>
        <td>
            <label for="i1">分类编号：</label>
            <input type="number" name="id" id="i1" required>
        </td>
        <td>
            <label for="n1">分类名称：</label>
            <input type="text" name="name" id="n1" required>
        </td>
        <td>
            <button class="btn1 btn-xs btn-primary" type="button">修改
            </button>
            <button class="btn btn-xs btn-warning" type="reset" id="res">重置
            </button>
            <button class="btn2 btn-xs btn-info" type="button">新增
            </button>
        </td>
    </tr>
    <tr>
        <th>ID</th>
        <th>分类名称</th>
        <th>创建日期</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="cate" items="${categories}">
        <tr>
            <td>${cate.id}</td>
            <td>${cate.name}</td>
            <td>${cate.createDate}</td>
            <td>
                <c:if test="${cate.state eq 0}">
                    <button class="btn btn-xs btn-success" type="button"
                            onclick="location='category.do?op=rec&id=${cate.id}'">恢复
                    </button>
                </c:if>
                <c:if test="${cate.state eq 1}">
                    <button class="btn btn-xs btn-danger" type="button"
                            onclick="location='category.do?op=del&id=${cate.id}'">删除
                    </button>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
