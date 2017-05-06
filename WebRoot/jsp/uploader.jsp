<%--
  Created by IntelliJ IDEA.
  User: xiaoou
  Date: 2017/5/2
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上传图片</title>
    <script src="js/head.js"></script>
    <script src="js/webuploader.js"></script>
    <script src="js/upload.js"></script>
    <link href="css/webuploader.css" rel="stylesheet" type="text/css">
    <link href="css/upload.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="wrapper">
    <div id="container">
        <!--头部，相册选择和格式选择-->
        <div id="uploader">
            <div class="queueList">
                <div id="dndArea" class="placeholder">
                    <div id="filePicker"></div>
                    <p>或将文件拖到这里，单次最多可选300份</p>
                </div>
            </div>
            <div class="statusBar" style="display:none;">
                <div class="progress">
                    <span class="text">0%</span>
                    <span class="percentage"></span>
                </div><div class="info"></div>
                <div  class="btns">
                    <div id="filePicker2"></div><div class="uploadBtn">开始上传</div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>


    $(document).ready(function () {
        var pictureType = getParam("pictureType");
        var time = getParam("time");
        uploderInit({time:time,pictureType:pictureType},"uploadImage");//初始化
    });
</script>
</body>
</html>
