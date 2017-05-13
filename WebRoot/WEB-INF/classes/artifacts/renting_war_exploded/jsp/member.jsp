<%--
  Created by IntelliJ IDEA.
  User: zuowy
  Date: 2017/4/1
  Time: 18:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<head>
    <title>Title</title>
    <script src="js/head.js"></script>
</head>
<body>
<div class="container">
    <table class="table table-bordered" style="width: 800px;" id="memberTable">
        <thead>
        <tr>
            <td id="userId" class="hide">主键</td>
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
        groupId = getParam("groupId");
        getGroupViewData(groupId,0,10)
    }
    function getGroupViewData(groupId,begin,end){
        try{
            doPostAjax("member_getInfo",{"groupId":groupId,"begin":begin,"end":end},function (data) {
                var htmlArray = new Array();
                $.each(data.userList,function (index,value,array) {
                    htmlArray.push(createMemberTableHtml(value));
                });
                $("#memberTable tbody").html(String.prototype.concat.apply("", htmlArray));
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
                    userId = viewValue[key];
                }else{
                    if(viewValue[key] == undefined){
                        html = html+"<td></td>";
                    }else{
                        html = html+"<td>"+viewValue[key]+"</td>";
                    }
                }

            }
        });

        html = html+"<td><a onclick='gotoView("+userId+")'>私信</a></td>"+"</tr>";
        return html;
    }
    function gotoView(userId){
        layer.prompt({title: '回复', formType: 2,area: ['893px', '100px']}, function(text, index){
            layer.close(index);
            var json = {
                content:text,
                receiverId:userId
            };
            var load = layer.load(1, {
                shade: [0.1,'#fff'] //0.1透明度的白色背景
            });
            var notSuccess = true;
            doPostAjax("cardDetail_savePrivateReplyInfo",json,function (data) {
                if(data.result == "Y"){
                    layer.close(load);
                    layer.confirm('发送私信成功', {
                        btn: ['确定'] //按钮
                    },function () {
                        location.reload();
                    });
                    notSuccess = false;

                }else{
                    layer.close(load);layer.confirm('发送私信失败:', {btn: ['确定'] });
                }
            });
            setTimeout(function () {
                if(notSuccess){
                    layer.close(load);layer.confirm('发送私信失败', {btn: ['确定'] });
                }
            }, 10000);
        });
    }
</script>
</body>
</html>
