<%--
  Created by IntelliJ IDEA.
  User: xiaoou
  Date: 2017/4/11
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>发布帖子</title>
    <script src="js/head.js"></script>
</head>
<body>
<div class="container">
    <form class="form-horizontal">

        <div class="form-group has-check">
            <label for="cardTitle" class="col-sm-2 control-label">帖子标题:</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="cardTitle" hasCheck="true">
            </div>
        </div>

        <div class="form-group ">
            <label for="houseLimit" class="col-sm-2 control-label">室友类型:</label>
            <div class="col-sm-4">
                <select class="form-control" id ="houseLimit">
                    <option value="0">请选择</option>
                    <option value="1">有房</option>
                    <option value="2">无房</option>
                </select>
            </div>
        </div>

        <div class="form-group ">
            <label for="sexLimit" class="col-sm-2 control-label">性别要求:</label>
            <div class="col-sm-4">
                <select class="form-control" id ="sexLimit">
                    <option value="">请选择</option>
                    <option value="男">男</option>
                    <option value="女">女</option>
                </select>
            </div>
        </div>

        <div class="form-group has-check">
            <label for="cardContent" class="col-sm-2 control-label">帖子内容:</label>
            <div class="col-sm-6">
                <textarea class="form-control" rows="5" id = "cardContent"></textarea>
            </div>
        </div>

        <div class="form-group has-check">
            <label  class="col-sm-2 control-label">上传图片:</label>
            <div class="col-sm-8">
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
                                <div class="btns">
                                    <div id="filePicker2"></div><div class="uploadBtn">开始上传</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <button type="button" class="btn btn-primary btn-lg btn-block" style="width: 200px; margin: auto;margin-bottom: 50px" onclick="saveInfo()">提交</button>
    </form>
</div>
<script>
    var nowtime ;
    $(document).ready(function () {
        nowtime = getCurrentTimeMillis();
        uploderInit("uploader","dndArea","filePicker",{time:nowtime},"postBarUploadImage");//初始化
    });
    //保存帖子信息
    function saveInfo(){
        var cardTitle = $("#cardTitle");//帖子标题
        var houseLimit = $("#houseLimit");//房子标签 是否有房
        var sexLimit = $("#sexLimit");//性别标签 男女
        var cardContent = $("#cardContent");// 帖子内容
        if(cardTitle.val()==""|| cardTitle.val()==undefined){
            cardTitle.focus();
            layer.msg('帖子标题必须要填写');
        }
        if(cardContent.val()==""|| cardContent.val()==undefined){
            cardContent.focus();
            layer.msg('帖子内容必须要填写');
        }
        var json = {
            "cardTitle":cardTitle.val(),
            "houseLimit":houseLimit.val(),
            "sexLimit":sexLimit.val(),
            "cardContent":cardContent.val(),
            "time":nowtime
        };
        var load = layer.load(1, {
            shade: [0.1,'#fff'] //0.1透明度的白色背景
        });
        var notSuccess = true;
        doPostAjax("createCard_saveCard",json,function (data) {
            if(data.result =="Y"){
                layer.close(load);
                layer.confirm('保存成功', {
                    btn: ['确定'] //按钮
                });
                notSuccess = false;
            }else{
                layer.close(load);layer.confirm('保存失败', {btn: ['确定'] });
            }
        });
        setTimeout(function () {
            if(notSuccess){
                layer.close(load);layer.confirm('保存失败', {btn: ['确定'] });
            }
        }, 10000);
    }
</script>
</body>
</html>
