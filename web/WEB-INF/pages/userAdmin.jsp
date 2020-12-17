<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${user.state lt 2}">
    <c:redirect url="user"/>
</c:if>
<html>
<head>
    <title>用户管理</title>
    <%@include file="/WEB-INF/pages/inc.jsp" %>
    <script type="text/javascript" src="jquery/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="js/useradmin.js"></script>
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
        <td colspan="5">
            <label for="n1">用户名：</label>
            <input type="text" name="name" id="n1">
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
        <th>用户名</th>
        <th>邮箱</th>
        <th>积分</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="u" items="${users}">
        <tr>
            <td>${u.id}</td>
            <td>${u.username}</td>
            <td>${u.email}</td>
            <td>${u.score}</td>
            <td>
                <c:if test="${u.state eq 3}">超级管理员</c:if>
                <c:if test="${u.state eq 0}">
                    <c:url var="url" value="userAdmin.do">
                        <c:param name="op" value="rec"/>
                        <c:param name="id" value="${u.id}"/>
                        <c:param name="pno" value="${pg.pageNo}"/>
                        <c:param name="psize" value="${pg.pageSize}"/>
                    </c:url>
                    <button class="btn btn-primary" onclick="location='${url}'">恢复</button>
                </c:if>
                <c:if test="${u.state eq 1}">
                    <c:if test="${user.state eq 3}">
                        <c:url var="url" value="userAdmin.do">
                            <c:param name="op" value="upg"/>
                            <c:param name="id" value="${u.id}"/>
                            <c:param name="pno" value="${pg.pageNo}"/>
                            <c:param name="psize" value="${pg.pageSize}"/>
                        </c:url>
                        <button class="btn btn-success" onclick="location='${url}'">升级</button>
                    </c:if>
                    <c:url var="url" value="userAdmin.do">
                        <c:param name="op" value="del"/>
                        <c:param name="id" value="${u.id}"/>
                        <c:param name="pno" value="${pg.pageNo}"/>
                        <c:param name="psize" value="${pg.pageSize}"/>
                    </c:url>
                    <button class="btn btn-danger" onclick="location='${url}'">删除</button>
                </c:if>
                <c:if test="${u.state eq 2}">
                    <c:if test="${user.state eq 3}" var="rt">
                        <c:url var="url" value="userAdmin.do">
                            <c:param name="op" value="deg"/>
                            <c:param name="id" value="${u.id}"/>
                            <c:param name="pno" value="${pg.pageNo}"/>
                            <c:param name="psize" value="${pg.pageSize}"/>
                        </c:url>
                        <button class="btn btn-warning" onclick="location='${url}'">降级</button>
                    </c:if>
                    <c:if test="${not rt}">
                        管理员
                    </c:if>
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
                        <a href="userAdmin.do?word=${word}&pno=${pg.start-1}&psize=${pg.pageSize}">&laquo;</a>
                    </c:if>
                </li>

                <c:forEach var="i" begin="${pg.start}" end="${pg.end}">
                    <li class="${pg.pageNo eq i?'active':''}">
                    <c:if test="${pg.pageNo eq i}" var="rt">
                        <a>${i}</a>
                    </c:if>
                    <c:if test="${not rt}">
                        <a href="userAdmin.do?word=${word}&pno=${i}&psize=${pg.pageSize}">${i}</a></li>
                    </c:if>
                </c:forEach>
                <li class="${pg.end eq pg.pages?'disabled':''}">
                    <c:if test="${pg.end eq pg.pages}" var="rt">
                        <a>&raquo;</a>
                    </c:if>
                    <c:if test="${not rt}">
                        <a href="userAdmin.do?word=${word}&pno=${pg.end+1}&psize=${pg.pageSize}">&raquo;</a>
                    </c:if>
                </li>
            </ul>
        </td>
    </tr>
    </tfoot>
</table>
</body>
</html>
