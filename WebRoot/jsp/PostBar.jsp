<%--
  Created by IntelliJ IDEA.
  User: xiaoou
  Date: 2017/4/6
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>贴吧</title>
    <style type="text/css">
        html, body {width:100%;height:100%;} /*非常重要的样式让背景图片100%适应整个屏幕*/
        .bg {display: table;width: 100%;height: 100%;padding: 100px 0;text-align: center;color: #fff;background: url(http://www.xiandanke.cn/Image/intro-bg.jpg) no-repeat bottom center;background-color: #000;background-size: cover;}
        .my-navbar {padding:20px 0;transition: background 0.5s ease-in-out, padding 0.5s ease-in-out;}
        .my-navbar a{background:transparent !important;color:#fff !important}
        .my-navbar a:hover {color:#45bcf9 !important;background:transparent;outline:0}
        .my-navbar a {transition: color 0.5s ease-in-out;}/*-webkit-transition ;-moz-transition*/
        .top-nav {padding:0;background:#000;}
        button.navbar-toggle {background-color:#fbfbfb;}/*整个背景都是transparent透明的，会看不到，所以再次覆盖一下*/
        button.navbar-toggle > span.icon-bar {background-color:#dedede}
    </style>
</head>
<body>
<nav class="navbar navbar-fixed-top my-navbar" role="navigation">
    <div class="container-fluid">
        <div >
            <a class="navbar-brand" href="#"  id="userHref" style="float: right;margin-right: 150px">
                <div class="fa" id="hrefContent"></div>
            </a>
        </div>
        <div >
            <a class="navbar-brand" href="#"  id="tieba1" style="float: right;margin-right: 150px">
                贴吧
            </a>
        </div>
        <div >
            <a class="navbar-brand" href="./upLoadHouseInfo"  id="upLoadHouseInfo" style="float: right;margin-right: 150px">
                上传房源
            </a>
        </div>
    </div>
</nav>
<div class="bg">
    <div class="grid-total" align="center">
        <div class="grid-search-left"></div>
        <div class="grid-search-center">
            <div class="grid-left"><input type="text"  class="myform-control" id="searchContent"></div>
            <div class="grid-right"><a class="search-button btn-primary no-line " onclick="firstSearch()">搜索</a></div>
        </div>
        <div class="grid-search-right"></div>
        <div style="clear:both"></div>

        <div class="grid-options">

        </div>
        <div class="grid-sort"></div>
        <div id="postBarList" class="products">

        </div>
        <div style="padding-left:100px;">
            <ul class="pagination"></ul>
        </div>
    </div>
</div>
<script>
    function init() {
        $("#postBarList").html();
        postBarList
    }
</script>
</body>
</html>
