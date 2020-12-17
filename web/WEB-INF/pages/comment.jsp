<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${user.state lt 2}">
    <c:redirect url="user"/>
</c:if>
<html>
<head>
    <title>评论</title>
    <%@include file="/WEB-INF/pages/inc.jsp" %>
    <script type="text/javascript" src="js/reset.js"></script>
    <script type="text/javascript" src="js/comment.js"></script>
    <style>
        .wrap {
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
        }
    </style>
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
        <td colspan="5">
            <label for="c1">评论内容：</label>
            <input type="text" name="content" id="c1">
        </td>
        <td colspan="5">
            <button class="btn btn-xs btn-warning" type="reset" id="res">重置
            </button>
            <button class="btn2 btn-xs btn-info" type="button">关键字查询
            </button>
        </td>
    </tr>
    <tr>
        <th>ID</th>
        <th>新闻标题</th>
        <th>评论内容</th>
        <th>作者名</th>
        <th>创建日期</th>
        <th>
            <button class="btn btn-xs btn-info" type="button"
                    onclick="location='comment.do?op=showAdd'" disabled>新增
            </button>
        </th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="comment" items="${comments}">
        <tr id="tr1">
            <td>${comment.id}</td>
            <td>${comment.title}</td>
            <td class="wrap">${comment.content}</td>
            <td>${comment.username}</td>
            <td>${comment.createDate}</td>
            <td>
                <c:if test="${comment.state eq 0}">
                    <c:url var="url1" value="comment.do">
                        <c:param name="op" value="rec"/>
                        <c:param name="id" value="${comment.id}"/>
                        <c:param name="pno" value="${pg.pageNo}"/>
                        <c:param name="psize" value="${pg.pageSize}"/>
                    </c:url>
                    <button class="btn btn-xs btn-success" type="button"
                            onclick="location='${url1}'">恢复
                    </button>
                </c:if>
                <c:if test="${comment.state eq 1}">
                    <c:url var="url2" value="comment.do">
                        <c:param name="op" value="del"/>
                        <c:param name="id" value="${comment.id}"/>
                        <c:param name="pno" value="${pg.pageNo}"/>
                        <c:param name="psize" value="${pg.pageSize}"/>
                    </c:url>
                    <button class="btn btn-xs btn-danger" type="button"
                            onclick="location='${url2}'">删除
                    </button>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
    <tfoot>
    <tr>
        <td colspan="10">
            <ul class="pagination">
                <li class="${pg.start eq 1?'disabled':''}">
                    <c:if test="${pg.start eq 1}" var="rt">
                        <a>&laquo;</a>
                    </c:if>
                    <c:if test="${not rt}">
                        <a href="comment.do?word=${word}&pno=${pg.start-1}&psize=${pg.pageSize}">&laquo;</a>
                    </c:if>
                </li>

                <c:forEach var="i" begin="${pg.start}" end="${pg.end}">
                    <li class="${pg.pageNo eq i?'active':''}">
                        <c:if test="${pg.pageNo eq i}" var="rt">
                            <a>${i}</a>
                        </c:if>
                        <c:if test="${not rt}">
                            <a href="comment.do?word=${word}&pno=${i}&psize=${pg.pageSize}">${i}</a>
                        </c:if>
                    </li>
                </c:forEach>
                <li class="${pg.end eq pg.pages?'disabled':''}">
                    <c:if test="${pg.end eq pg.pages}" var="rt">
                        <a>&raquo;</a>
                    </c:if>
                    <c:if test="${not rt}">
                        <a href="comment.do?word=${word}&pno=${pg.end+1}&psize=${pg.pageSize}">&raquo;</a>
                    </c:if>
                </li>
            </ul>
        </td>
    </tr>
    </tfoot>
</table>
</body>
</html>
