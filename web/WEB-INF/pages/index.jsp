<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<html>
<head>
    <title>首页</title>
    <link type="text/css" rel="stylesheet" href="css/comm.css">
    <script type="text/javascript" src="jquery/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="js/index.js"></script>
</head>
<body>
<h2 style="color: red">${msg}</h2>
<div id="header">
    <div class="main-top">
        <div class="logo"><a href=""><span>新闻大视野</span></a></div>
        <div class="login-box">
            <c:if test="${empty user}" var="rt">
                <form action="user">
                    <input type="hidden" name="op" value="login">
                    </span><label>用户名</label><input type="text" name="name"/><label>密码</label><input type="password"
                                                                                                     name="pass"/>
                    <button class="button1">登录</button>
                    <button class="button2"><a href="user?op=showreg">注册</a></button>
                </form>

            </c:if>
            <c:if test="${not rt}">
                <c:if test="${user.state gt 1}">
                    <button type="button" onclick="location='category.do'" style="float: right">后台</button>
                </c:if>
            </c:if>
        </div>
        <div class="nav">
            <ul class="clearfix">
                <%--                jquery动态添加分类名称--%>
            </ul>
        </div>
    </div>
    <!--banner-->
    <div class="main-banner">
        <img src="images/banner.png"/>
    </div>
    <!--搜索横框-->
    <div class="search-box">
        <div class="sl">
            <div class="sr clearfix">

                <span class="left-search clearfix">
                    <label>站内搜索</label><input type="text" name="keyword" placeholder="关键词" id="t1"/><button
                        class="go-btn"
                        id="go-btn1"></button>
                </span>
                <span class="right-link">
                    <label>快速链接</label><select name="cate" id="c1"><option>-----专题选择-----</option></select><button
                        class="go-btn" id="go-btn2"></button>
<%--                    jquery动态添加下拉列表--%>
                </span>

            </div>
        </div>
    </div>
    <!--用户工具栏-->
    <%--    jstl实现用户身份显示--%>
    <c:if test="${not empty user}">
        <div class="admin-bar">
            <a href="user?op=logoff" class="fr">退出账户</a>
            <c:set var="date" value="${now}"/>
            <c:if test="${user.state eq 1}">
                <span>用户：${user.username} ${date}</span>
            </c:if>
            <c:if test="${user.state eq 2}">
                <span>管理员：${user.username} ${date}</span>
            </c:if>
            <c:if test="${user.state eq 3}">
                <span>超级管理员：${user.username} ${date}</span>
            </c:if>
        </div>
    </c:if>
</div>
<!--d页面主体-->
<div id="content" class="main-content clearfix">
    <!--主体的的左边部分-->
    <div class="main-content-left">
        <!--新闻专题分类-->
        <div class="class-box">
            <div class="class-box-header">
                <span class="fr"><a href="#">更多...</a></span>
                <h3>新闻专题</h3>
            </div>
            <div class="class-box-content">
                <ul class="ul4x">
                    <%--jquery动态太添加分类名称--%>
                </ul>
            </div>
        </div>
        <!--最新新闻-->
        <div class="left-box">
            <div class="left-box-tbg">
                <div class="left-box-bbg">
                    <div class="left-box-header"><h3><a href="#">最新新闻</a></h3></div>
                    <div class="left-box-content">
                        <ul id="ul4zx">
                            <%--                            jquery动态添加最新的三条信息--%>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <!--最新评论-->
        <div class="left-box">
            <div class="left-box-tbg">
                <div class="left-box-bbg">
                    <div class="left-box-header"><h3>最新评论</h3></div>
                    <div class="left-box-content">
                        <ul id="ul4pl">
<%--                            jquery动态添加最新的三条有评论的信息--%>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--页面主体的右部，包括新闻的列表和评论内容 -->
    <div class="main-content-right">
        <!--各专题的新闻列表-->
        <div class="main-text-box">
            <div class="main-text-box-tbg">
                <div class="main-text-box-bbg">
                    <div class="main-text-box-header">
                        <h3>即时新闻
                            <c:set var="cate" value="${categoryId}"/>
                            <c:forEach var="detail" items="${detail}">
                                <c:if test="${detail.categoryId eq cate}">
                                    <c:set var="c" value=">${detail.name}"/>
                                </c:if>
                            </c:forEach>
                            ${c}
                        </h3>
                    </div>
                    <div class="main-text-box-content">
                        <ul class="news-list-ul clearfix" id="ul4js">
                            <%--                            即时新闻使用jstl实现，接收来自UserServlet传入的数据--%>
                            <c:forEach var="detail" items="${detail}" varStatus="i">
                                <c:if test="${(i.index+1) % 5 eq 0}" var="rt">
                                    <li class="li-line"><span>${detail.createDate}</span><a id="a4xw"
                                            href="user?op=comm&id=${detail.id}">${detail.title}</a></li>
                                </c:if>
                                <c:if test="${not rt}">
                                    <li><span>${detail.createDate}</span><a href="user?op=comm&id=${detail.id}">${detail.title}</a></li>
                                </c:if>
                            </c:forEach>
                        </ul>
                    </div>
                    <!--图片新闻-->
                    <div class="news-pic">
                        <dl>
                            <dt><img src="images/new-1.png"/></dt>
                            <dd>詹姆斯防守杜兰特</dd>
                        </dl>
                        <dl>
                            <dt><img src="images/new-2.png"/></dt>
                            <dd>麦当劳篮球大篷车</dd>
                        </dl>
                        <dl>
                            <dt><img src="images/new-3.png"/></dt>
                            <dd>遭遇多人包夹</dd>
                        </dl>
                        <dl>
                            <dt><img src="images/new-4.gif"/></dt>
                            <dd>马蒂最新漫画欣赏</dd>
                        </dl>
                    </div>
                    <div class="page-bar">
                        <ul class="page-num-ul clearfix" id="ul4pg">
                            <%--                            分页使用jstl实现，分页数据提交至UserServlet处理--%>
                            <li class="${pg.start eq 1?'disabled':''}">
                                <c:if test="${pg.start eq 1}" var="rt">
                                    <a>&laquo;</a>
                                </c:if>
                                <c:if test="${not rt}">
                                    <a href="user?categoryId=${categoryId}&word=${word}&pno=${pg.start-1}&psize=${pg.pageSize}">&laquo;</a>
                                </c:if>
                            </li>

                            <c:forEach var="i" begin="${pg.start}" end="${pg.end}">
                                <li class="${pg.pageNo eq i?'active':''}">
                                    <c:if test="${pg.pageNo eq i}" var="rt">
                                        <a>${i}</a>
                                    </c:if>
                                    <c:if test="${not rt}">
                                        <a href="user?categoryId=${categoryId}&word=${word}&pno=${i}&psize=${pg.pageSize}">${i}</a>
                                    </c:if>
                                </li>
                            </c:forEach>
                            <li class="${pg.end eq pg.pages?'disabled':''}">
                                <c:if test="${pg.end eq pg.pages}" var="rt">
                                    <a>&raquo;</a>
                                </c:if>
                                <c:if test="${not rt}">
                                    <a href="user?categoryId=${categoryId}&word=${word}&pno=${pg.end+1}&psize=${pg.pageSize}">&raquo;</a>
                                </c:if>
                            </li>
                        </ul>
                        <%--                        <span class="page-go-form"><label>跳转至</label><input type="text" name="numkey" class="page-key"/>页<button--%>
                        <%--                                type="submit" class="page-btn">GO</button></span>--%>
                    </div>
                </div>
            </div>
        </div>
        <!--合作媒体-->
        <div class="main-text-box">
            <div class="main-text-box-tbg">
                <div class="main-text-box-bbg">
                    <div class="main-text-box-header">
                        <h3>合作媒体</h3>
                    </div>
                    <div class="main-text-box-content">
                        <ul class="link-text-ul clearfix">
                            <li><a href="#">中国政府网</a></li>
                            <li><a href="#">中国政府网</a></li>
                            <li><a href="#">中国政府网</a></li>
                            <li><a href="#">中国政府网</a></li>
                            <li><a href="#">中国政府网</a></li>
                            <li><a href="#">中国政府网</a></li>
                            <li><a href="#">中国政府网</a></li>
                            <li><a href="#">中国政府网</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--页面底部-->
<div id="footer" class="main-footer-box">
    24小时客户服务热线：010-68988888 常见问题解答 新闻热线：010-627488888<br/>
    文明办网文明上网举报电话：010-627488888 举报邮箱：jubao@bj-aptech.com.cn<br/>
    Coyright&copy;1999-2007 News China gov,All Right Reserved.<br/>
    新闻中心版权所有
</div>
</body>
</html>
