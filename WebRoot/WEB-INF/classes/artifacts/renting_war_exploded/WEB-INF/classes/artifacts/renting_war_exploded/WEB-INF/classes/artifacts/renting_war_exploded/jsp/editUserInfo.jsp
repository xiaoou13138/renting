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
</head>
<body>
<div class="container">
    <form class="form-horizontal">
        <div class="form-group" style="text-align: center;font-size: 40px;">
            <label  class="col-sm-6 control-label">个人信息编辑</label>
        </div>

        <div class="form-group has-check">
            <label for="telphone" class="col-sm-3 control-label">手机号码:</label>
            <div class="col-sm-4">
                <input onkeyup="checkTelphone()" type="text" class="form-control" id="telphone" hasCheck="true" placeholder="请输入手机号码">
            </div>
        </div>

        <div class="form-group has-check">
            <label for="name" class="col-sm-3 control-label">真实姓名:</label>
            <div class="col-sm-4">
                <input onblur="checkName()" type="text" class="form-control" id="name" hasCheck="true" placeholder="请输入姓名">
            </div>
        </div>

        <div class="form-group has-check">
            <label for="name" class="col-sm-3 control-label">年龄:</label>
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

        <button type="button" class="btn btn-primary btn-lg btn-block" style="width: 200px; margin-left: 350px;margin-bottom: 50px" onclick="saveInfo()">提交</button>
    </form>

</div>
</body>
</html>
