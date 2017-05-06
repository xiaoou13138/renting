<%--
  Created by IntelliJ IDEA.
  User: xiaoou
  Date: 2017/4/1
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="js/head.js"></script>
</head>
<body>
<div class="container-fluid" style="background-color: #e7e9ff">
    <div class="panel panel-primary" >
        <div class="panel-heading">
            <h3 class="panel-title  text-left">编辑个人信息</h3>
        </div>
        <div class="panel-body" style="height: 756px">
            <form class="form-horizontal">
                <div class="form-group has-check">
                    <label for="telphone" class="col-sm-3 control-label">手机号码:</label>
                    <div class="col-sm-4">
                        <input onkeyup="checkTelphone()" type="text" class="form-control" id="telphone" hasCheck="true" placeholder="请输入手机号码">
                    </div>
                </div>

                <div class="form-group has-check">
                    <label for="realName" class="col-sm-3 control-label">真实姓名:</label>
                    <div class="col-sm-4">
                        <input onblur="checkName()" type="text" class="form-control" id="realName" hasCheck="true" placeholder="请输入姓名">
                    </div>
                </div>

                <div class="form-group has-check">
                    <label for="age" class="col-sm-3 control-label">年龄:</label>
                    <div class="col-sm-4">
                        <input onblur="validateNum()" type="text" class="form-control" id="age" hasCheck="true" placeholder="请输入年龄">
                    </div>
                </div>

                <div class="form-group has-check">
                    <label for="sex" class="col-sm-3 control-label">性别:</label>
                    <div class="col-sm-4">
                        <select class="form-control" id ="sex">
                            <option>男</option>
                            <option>女</option>
                            <option>不公开</option>
                        </select>
                    </div>
                </div>

                <button type="button" class="btn btn-primary btn-lg btn-block center-block" style="width: 200px;margin-bottom: 50px" onclick="updateUserInfo()">保存修改</button>
            </form>
        </div>
    </div>
</div>
<script>
    $(document).ready(function () {
        setViewData(${data});
        return;
        var telphone = '${data.telphone}';
        var realName = '${data.realName}';
        var age = '${data.age}';
        var sex = '${data.sex}';
        if(telphone){
            $("#telphone").val(telphone);
        }
        if(realName){
            $("#realName").val(realName);
        }
        if(age){
            $("#age").val(age);
        }
        $("#sex").val(sex);
    });
    function updateUserInfo() {
        var telphone =  $("#telphone").val();
        var realName =  $("#realName").val();
        var age =  $("#age").val();
        var sex =  $("#sex").val();
        var json = {telphone:telphone,realName:realName,age:age,sex:sex};
        json.actionType = 1;
        doPostAjax("editUserInfo_viewAction",json,function (data) {
            if(data.result == "Y"){
                layer.confirm('保存成功', {
                    btn: ['确定'] //按钮
                });
            }
        });
    }
</script>
</body>
</html>
