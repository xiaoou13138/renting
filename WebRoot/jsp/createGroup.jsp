<%--
  Created by IntelliJ IDEA.
  User: zuowy
  Date: 2017/4/10
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="js/head.js"></script>
</head>
<body>
<div class="container">
    <form class="form-horizontal">
        <div class="form-group" style="text-align: center;font-size: 40px;">
            <label  class="col-sm-6 control-label">新增组信息</label>
        </div>

        <div class="form-group has-check">
            <label for="groupName" class="col-sm-3 control-label">组的名称:</label>
            <div class="col-sm-4">
                <input  type="text" class="form-control" id="groupName">
            </div>
        </div>

        <div class="form-group has-check">
            <label for="groupNumber" class="col-sm-3 control-label">组的最大人数:</label>
            <div class="col-sm-4">
                <input  type="text" class="form-control" id="groupNumber" onkeyup="validateNum(this)">
            </div>
        </div>

        <div class="form-group ">
            <label for="cityChoice" class="col-sm-3 control-label">组的地址:</label>
            <div class="col-sm-3">
                <div class="input-group">
                    <div class="input-group-addon">城市</div>
                    <input type="text" id="cityChoice"  class="form-control">
                    <input type="hidden" id="province" value="">
                    <input type="hidden" id="city" value="">
                </div>
            </div>
            <div class="col-sm-4">
                <div class="input-group">
                    <div class="input-group-addon">具体位置</div>
                    <input type="text" id="addressDeatail"  class="form-control">
                </div>
            </div>
        </div>

        <div class="form-group ">
            <label for="groupInfor" class="col-sm-3 control-label">组的描述信息:</label>
            <div class="col-sm-4">
                <input  type="text" class="form-control" id="groupInfor">
            </div>
        </div>
        <button type="button" class="btn btn-primary btn-lg btn-block" style="width: 200px; margin: auto;margin-bottom: 50px" onclick="postGroupInfo()">提交</button>
    </form>
</div>
<script>
    $(document).ready(function () {
        var cityPicker = new IIInsomniaCityPicker({
            data: cityData,
            target: '#cityChoice',
            valType: 'k-v',
            hideCityInput: '#city',
            hideProvinceInput: '#province',
            callback: function(city_id){
                // alert(city_id);
            }
        });
        cityPicker.init();
    });
    function postGroupInfo() {
        var groupName = $("#groupName");
        var groupNumber = $("#groupNumber");
        var groupInfor = $("#groupInfor");
        var address =  $("#province").val()+$("#city").val()+$("#addressDeatail").val();
        var groupInfor = $("#groupInfor");
        if(groupName.val() == "" || groupName.val() == undefined){
            layer.msg('组的名称必须要填写');
            groupName.focus();
            return;
        }
        if(groupNumber.val() == "" || groupNumber.val() == undefined){
            layer.msg('组的最大人数必须要填写');
            groupNumber.focus();
            return;
        }
        beginLoad("保存成功","保存失败",5000,function () {
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        });
        doPostAjaxAndLoad("group_createGroup",{"groupName":groupName.val(),"groupNumber":groupNumber.val(),"address":address,"groupInfor":groupInfor.val()},function (data) {

        });
    }
</script>
</body>
</html>
