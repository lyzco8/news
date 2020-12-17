<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<html>
<head>
    <title>首页</title>
    <link type="text/css" rel="stylesheet" href="css/comm.css">
    <script type="text/javascript" src="jquery/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="js/index.js"></script>
    <script type="text/javascript" src="js/article.js"></script>
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
<%--                            <li><a href="#">詹姆斯26+9+7热火2-1雷霆 詹姆斯快速实战迷踪步2+1</a></li>--%>
<%--                            <li><a href="#">詹姆斯26+9+7热火2-1雷霆 詹姆斯快速实战迷踪步2+1</a></li>--%>
<%--                            <li><a href="#">詹姆斯26+9+7热火2-1雷霆 詹姆斯快速实战迷踪步2+1</a></li>--%>
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
            <div class="article-place"><a href="user?state=0">新闻中心</a> > <a
                    href="user?categoryId=${detail.categoryId}&state=0">${detail.name}</a></div>
            <div class="main-text-box-tbg">
                <div class="main-text-box-bbg">
                    <div class="article-box">
                        <input type="hidden" id="detailId" value="${detail.id}">
                        <!--新闻的标题-->
                        <h1>${detail.title}</h1>
                        <div class="source-bar">发布者：${detail.username} 分类：${detail.name}新闻
                            更新时间：<c:if test="${empty detail.modifyDate}" var="rt">${detail.createDate}</c:if>
                            <c:if test="${not rt}">${detail.modifyDate}</c:if>
                        </div>
                        <div class="article-content">
                            <span style="width: 80%"><img src="${detail.picPath}"></span>
                            <span class="article-summary"><b>摘要：</b>${detail.summary}</span>
                            ${detail.content}
                        </div>
                        <c:if test="${empty user}" var="rt">
                            <div class="comment">
                                <dd class="comment-body">未登录，登陆后可发表评论</dd>
                            </div>
                        </c:if>
                        <c:if test="${not rt}">
                            <div class="comment" id="div4cm">
<%--                                <c:forEach var="comm" items="${comm}" varStatus="i">--%>
<%--                                    <dl>--%>
<%--                                        <dt class="comment-top">--%>
<%--                                            <span class="fr">${comm.createDate}</span>--%>
<%--                                            <b>${i.index+1}楼</b> <b>${comm.username}</b>--%>
<%--                                        </dt>--%>
<%--                                        <dd class="comment-body">--%>
<%--                                            <span class="fr"><a href="#">支持</a>（0） <a href="#">回复</a>（0）</span>--%>
<%--                                            ${comm.content}--%>
<%--                                        </dd>--%>
<%--                                    </dl>--%>
<%--                                </c:forEach>--%>
                            </div>
                            <div class="comment-form">
                                <div class="comment-form-header">
                                    <span>用户：${user.username}</span>
                                    <h3>发表评论：</h3>
                                </div>
                                <div class="comment-form-content">
                                    <input type="hidden" class="comment-newsId" value="${detail.id}">
                                    <input type="hidden" class="comment-authorId" value="${user.id}">
                                    <input type="text" class="comment-textarea" name="comment">
                                    <button class="comment-btn">评论</button>
                                </div>
                            </div>
                        </c:if>
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
