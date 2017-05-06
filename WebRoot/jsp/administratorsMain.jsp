<%--
  Created by IntelliJ IDEA.
  User: xiaozuo
  Date: 2017/4/18
  Time: 12:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>系统管理员主页</title>
    <script src="js/head.js"></script>
    <style>
        .panel-body {
            height: 750px;
        }
    </style>
</head>
<body>
<div class="bg container-fluid">
        <div class="row" style="background-color: #00b7ee">
            <h1>系统管理员</h1>
        </div>
        <div class="row">
            <div class="col-md-3">
                <div style="height: 70%">
                    <ul class="nav nav-pills nav-stacked" id="administratorTab">
                        <li role="presentation"><a href="javascript:void(0)" data-toggle="tab" onclick="manageChange('./adminUserManage')">用户信息管理</a></li>
                        <li role="presentation"><a href="javascript:void(0)" data-toggle="tab" onclick="manageChange('./housesShow?viewType=2');">房源信息管理</a></li>
                        <li role="presentation"><a href="javascript:void(0)" data-toggle="tab" onclick="manageChange('./addGroup?viewType=2');">合租团队管理</a></li>
                        <li role="presentation"><a href="javascript:void(0)" data-toggle="tab" onclick="manageChange('./postBar?viewType=2')">贴吧管理</a></li>
                    </ul>
                </div>
            </div>
            <div class="col-md-9">
                <div class="tab-content" style="height: 820px">
                    <div class="tab-pane active" id="admin"><iframe src="./adminUserManage" width="100%" height="100%" allowTransparency="true" id="rightFrame" style="border: hidden;"></iframe></div>
                </div>
            </div>
        </div>
    </div>
</body>
<script>
    function manageChange(address){
        $("#rightFrame").attr("src",address);
    }
</script>
</html>
