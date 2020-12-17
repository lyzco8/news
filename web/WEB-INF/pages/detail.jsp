<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${user.state lt 2}">
    <c:redirect url="user"/>
</c:if>
<html>
<head>
    <title>新闻</title>
    <%@include file="/WEB-INF/pages/inc.jsp" %>
    <script type="text/javascript" src="js/reset.js"></script>
    <script type="text/javascript" src="js/detail.js"></script>
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
<table class="table" style="table-layout:fixed">
    <thead>
    <tr>
        <td colspan="4">
            <label>分类编号：</label>
            <select name="cate" id="c1">
                <option value="">分类选择</option>
            </select>
        </td>
        <td colspan="4">
            <label for="t1">标题：</label>
            <input type="text" name="title" id="t1">
        </td>
        <td colspan="2">
            <button class="btn1 btn-xs btn-primary" type="button">分类编号查询
            </button>
            <button class="btn btn-xs btn-warning" type="reset" id="res">重置
            </button>
            <button class="btn2 btn-xs btn-info" type="button">关键字查询
            </button>
        </td>
    </tr>
    <tr>
        <th>新闻ID</th>
        <th>分类名称</th>
        <th>新闻标题</th>
        <th>新闻摘要</th>
        <th>新闻内容</th>
        <th>图片路径</th>
        <th>作者名</th>
        <th>创建日期</th>
        <th>修改日期</th>
        <th>
            <button class="btn btn-xs btn-info" type="button"
                    onclick="location='detail.do?op=showAdd'">新增
            </button>
        </th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="detail" items="${detail}">
        <tr>
            <td>${detail.id}</td>
            <td>${detail.name}</td>
            <td class="wrap">${detail.title}</td>
            <td class="wrap">${detail.summary}</td>
            <td class="wrap">${detail.content}</td>
            <td>${detail.picPath}</td>
            <td>${detail.username}</td>
            <td>${detail.createDate}</td>
            <td>${detail.modifyDate}</td>
            <td>
                <button class="btn btn-xs btn-primary" type="button" ${detail.state eq 0?"disabled":""}
                        onclick="location='modify.do?op=showDetail&id=${detail.id}'">修改
                </button>
                <c:if test="${detail.state eq 0}">
                    <c:url var="url1" value="detail.do">
                        <c:param name="op" value="rec"/>
                        <c:param name="id" value="${detail.id}"/>
                        <c:param name="pno" value="${pg.pageNo}"/>
                        <c:param name="psize" value="${pg.pageSize}"/>
                    </c:url>
                    <button class="btn btn-xs btn-success" type="button"
                            onclick="location='${url1}'">恢复
                    </button>
                </c:if>
                <c:if test="${detail.state eq 1}">
                    <c:url var="url2" value="detail.do">
                        <c:param name="op" value="del"/>
                        <c:param name="id" value="${detail.id}"/>
                        <c:param name="pno" value="${pg.pageNo}"/>
                        <c:param name="psize" value="${pg.pageSize}"/>
                    </c:url>
                    <button class="btn btn-xs btn-danger" type="button"
                            onclick="location='${url2}'">删除
                    </button>
                </c:if>
                <form action="modify.do?op=pic" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="id" value="${detail.id}">
                    <input type="file" name="cover" accept="image/gif,image/jpeg,image/png">
                    <button ${detail.state eq 0?"disabled":""}>${empty detail.picPath?"添加图片":"修改图片"}</button>
                </form>
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
                        <a href="detail.do?categoryId=${categoryId}&word=${word}&pno=${pg.start-1}&psize=${pg.pageSize}">&laquo;</a>
                    </c:if>
                </li>

                <c:forEach var="i" begin="${pg.start}" end="${pg.end}">
                    <li class="${pg.pageNo eq i?'active':''}">
                        <c:if test="${pg.pageNo eq i}" var="rt">
                            <a>${i}</a>
                        </c:if>
                        <c:if test="${not rt}">
                            <a href="detail.do?categoryId=${categoryId}&word=${word}&pno=${i}&psize=${pg.pageSize}">${i}</a>
                        </c:if>
                    </li>
                </c:forEach>
                <li class="${pg.end eq pg.pages?'disabled':''}">
                    <c:if test="${pg.end eq pg.pages}" var="rt">
                        <a>&raquo;</a>
                    </c:if>
                    <c:if test="${not rt}">
                        <a href="detail.do?categoryId=${categoryId}&word=${word}&pno=${pg.end+1}&psize=${pg.pageSize}">&raquo;</a>
                    </c:if>
                </li>
            </ul>
        </td>
    </tr>
    </tfoot>
</table>
</body>
</html>
