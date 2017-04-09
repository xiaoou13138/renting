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
    <title>Title</title>
    <script src="js/head.js"></script>
<body>
<div class ="userInfobg" style="padding: 50px 250px 150px 250px;">
    <div style="height:800px;">
        <div style="float: left; width:20%; height: 800px;" class="base-boder">
            <div style="height: 24%; margin:0 auto;" class="divColor">
                <a style=" " href="#"  id="" style="float: right;margin-right: 150px">
                    <img class="img-circle" src="showImage?imageFile=default_user_image.png">
                </a>
            </div>
            <div style="height: 70%" class="divColor">
                <ul class="nav nav-pills nav-stacked" id="managementTab">
                    <li role="presentation"><a href="#upLoadHouseInfo" data-toggle="tab" onclick="">上传房屋信息</a></li>
                    <li role="presentation"><a href="#editUserInfo" data-toggle="tab" onclick="">个人信息维护</a></li>
                    <li role="presentation"><a href="#group" data-toggle="tab" onclick="groupViewInit()">组团</a></li>
                </ul>
            </div>
        </div>
        <div style="float:left; width:80%;  height: 800px;"  class="divColor">
            <div class="tab-content">
                <div class="tab-pane active" id="upLoadHouseInfo"><jsp:include page="upLoadHouseInfo.jsp"></jsp:include></div>
                <div class="tab-pane" id="editUserInfo"><jsp:include page="editUserInfo.jsp"></jsp:include></div>
                <div class="tab-pane" id="group"><jsp:include page="group.jsp"></jsp:include></div>
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
    function choiceCertificationType(){
        uploader.addButton({
            id: '#filePicker',
            innerHTML: '点击选择图片'
        });
    }
    function getMap() {
        loadScript();
    }
</script>

</body>
</html>
