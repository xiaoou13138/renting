<%--
  Created by IntelliJ IDEA.
  User: zuowy
  Date: 2017/4/11
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    long time = System.currentTimeMillis();
%>
<!doctype html>
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

        <div class="form-group">
            <label for="image" class="col-sm-2 control-label">上传图片(其他普通图片):</label>
            <div class="col-sm-8" style="height: 350px;" id="image">
                <iframe src="./uploader?pictureType=3&time=<%=time%>" width="100%" height="100%" allowTransparency="true" style="border: hidden;"></iframe>
            </div>
        </div>

        <button type="button" class="btn btn-primary btn-lg btn-block" style="width: 200px; margin: auto;margin-bottom: 50px" onclick="saveInfo()">提交</button>
    </form>
</div>
<script>
    var nowtime ;
    var needLogin;
    $(document).ready(function () {
        nowtime = <%=time%>;
        needLogin = '${data.needLogin}';
        if(needLogin == '1'){
            var confirmLayer = layer.confirm("请先登录", {
                btn: ['确定'] //按钮
            },function () {
                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
            });
        }
        uploderInit({time:nowtime},"uploadImage");//初始化
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
        beginLoad("保存成功","保存失败",5000,function () {
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        });
        doPostAjaxAndLoad("createCard_saveCard",json,function (data) {

        });

    }
</script>
</body>
</html>
