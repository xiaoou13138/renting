<%--
  Created by IntelliJ IDEA.
  User: xiaoou
  Date: 2017/4/1
  Time: 18:13
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
    <table class="table table-bordered" style="width: 800px;" id="memberTable">
        <thead>
        <tr>
            <td i d="userId" class="hide">主键</td>
            <td id="name">名称</td>
            <td id="userTypeInGroup">组员类型</td>
            <td id="sex">性别</td>
            <td id="age">年龄</td>
            <td id="phoneNum">手机号码</td>
            <td></td>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
    <div style="padding-left:100px;">
        <ul class="pagination"></ul>
    </div>
</div>
<script>
    var groupId = "";
    $(document).ready(function () {
        groupViewInit();
    });
    //页面的初始化方法
    function groupViewInit(){
        debugger;
        groupId = getParam("groupId");
        getGroupViewData(groupId,0,10)
    }
    function getGroupViewData(groupId,begin,end){
        try{
            doPostAjax("member_getInfo",{"groupId":groupId,"begin":begin,"end":end},function (data) {
                $.each(data.userList,function (index,value,array) {
                    $("#memberTable tbody").html(createMemberTableHtml(value));
                });
                setPage( data.count,function (begin,end) {
                    getGroupViewData(groupId,begin,end);
                });
            });
        }catch (e){
            alert(e);
        }

    }
    function createMemberTableHtml(viewValue) {
        var html = "<tr>";
        var userId = "";

        $.each($("#memberTable thead tr td"),function (index,value,array) {
            var key =$(value).attr("id");

            if(key != undefined){
                if(key == "userId"){
                    html = html+"<td class='hide'>"+viewValue[key]+"</td>";
                    userId = viewVlaue[key];
                }else{
                    if(viewValue[key] == undefined){
                        html = html+"<td></td>";
                    }else{
                        html = html+"<td>"+viewValue[key]+"</td>";
                    }
                }

            }
        });
        html = html+"<td><a onclick='gotoView("+userId+")'>联系</a></td>"+"</tr>";
        return html;
    }
    function gotoView(userId){
        layer.open({
            type: 2,
            title: '私信',
            shadeClose: true,
            shade: false,
            maxmin: true, //开启最大化最小化按钮
            area: ['893px', '600px'],
            content: './message?userId='+userId
        });
        /*window.open("./message");*/
        location.href="./message";
    }
</script>
</body>
</html>
