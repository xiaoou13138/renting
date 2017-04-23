<%--
  Created by IntelliJ IDEA.
  User: zuowy
  Date: 2017/3/26
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>信息管理</title>
    <script src="js/head.js"></script>
    <style>
        .panel-body {
            height: 750px;
        }
    </style>
<body>
<div class="bg">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-3">
                <div style="height: 40%;" >
                    <a href="javascript:void 0">
                        <img class="img-circle"style="height:100%; weight:100%;"  src="showImage?imageFile=default_user_image.png">
                    </a>
                </div>
                <div style="height: 7%">
                </div>
                <div style="height: 70%">
                    <ul class="nav nav-pills nav-stacked" id="managementTab">
                        <li role="presentation"><a href="javascript:void(0)" data-toggle="tab" onclick="manageChange('./editUserInfo')">个人信息维护</a></li>
                        <li role="presentation"><a href="javascript:void(0)" data-toggle="tab" onclick="manageChange('./group')">已加入组</a></li>
                        <li role="presentation"><a href="javascript:void(0)" data-toggle="tab" onclick="manageChange('./collectHouseShow');">收藏列表</a></li>
                        <li role="presentation"><a href="javascript:void(0)" data-toggle="tab" onclick="manageChange('./orderHouseShow');">我的预约</a></li>
                        <li role="presentation"><a href="javascript:void(0)" data-toggle="tab" onclick="manageChange('./myHouse')">我的房源</a></li>
                        <li role="presentation"><a href="javascript:void(0)" data-toggle="tab" onclick="manageChange('./message')">我的私信</a></li>
                    </ul>
                </div>
            </div>
            <div class="col-md-9">
                <div class="tab-content" style="height: 820px">
                    <div class="tab-pane active" id="main"><iframe src="./editUserInfo" width="100%" height="100%" allowTransparency="true" id="rightFrame"></iframe></div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function(){
        $('#managementTab a:first').tab('show');//初始化显示哪个tab

        $('#managementTab a').click(function(e) {
            e.preventDefault();//阻止a链接的跳转行为
            $(this).tab('show');//显示当前选中的链接及关联的content
        });
    });
    function manageChange(address){
        $("#rightFrame").attr("src",address);
    }
</script>

</body>
</html>
