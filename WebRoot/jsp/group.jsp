<%--
  Created by IntelliJ IDEA.
  User: xiaoou
  Date: 2017/4/1
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<head>
    <title>已经加入的组</title>
    <script src="js/head.js"></script>

</head>
<body>
<div class="container-fluid" style="background-color:#e7e9ff">
    <div class="panel panel-primary" style="background-color: white">
        <div class="panel-heading">
            <h3 class="panel-title  text-left">已经加入的组</h3>
        </div>
        <div class="panel-body" style="height: 756px">
            <div class="row">
                <button type="button" class="btn btn-primary pull-left" style="margin-left: 1%;" onclick="addGroup()">加入组</button>
                <button type="button" class="btn btn-primary pull-left" style="margin-left: 2%;" onclick="createGroup()">新增组</button>
                <button type="button" class="btn btn-primary pull-right" style="margin-right: 1%;" onclick="showGroupMessage()">消息<span class="badge" id="messageNum"></span></button>
            </div>
            <div class="row">
                <table class="table table-bordered" style=" margin: 10px" id="groupTable">
                    <thead>
                    <tr>
                        <td id="groupId" class="hide">组ID</td>
                        <td id="groupName">组名</td>
                        <td id="userTypeInGroup">我的角色</td>
                        <td id="groupAddress">区域</td>
                        <td id="groupNum">组最大人数</td>
                        <td id="currentNum">当前人数</td>
                        <td id="infor">组描述</td>
                        <td ></td>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
                <div class="text-center">
                    <ul class="pagination" id="groupShowPage"></ul>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function () {
        groupViewInit();
    });
    //初始化当前页面的分页组件
    function groupViewInit(){
        getGroupViewData(0,10,true);
        //查询用户有没有消息需要通知
        queryGroupMessgae();
    }

    function queryGroupMessgae() {
        doPostAjax("group_queryGroupMessage",{},function (data) {
            if(data.result == "Y"){
                $("#messageNum").text(data.messageNum);
            }
        });
    }

    //ajax调用后台数据
    function getGroupViewData(begin,end,isFirst){
        try{
            doPostAjaxAndDealPage("group_getGroupInfo",{"begin":begin,"end":end},function (data) {
                var htmlArray = new Array();
                $.each(data.groupList,function (index,value,array) {
                    htmlArray.push(createTableHtml(value));
                });
                $("#groupTable tbody").html(String.prototype.concat.apply("", htmlArray));
                //分页的重新初始化
                if(isFirst){
                    setPageById("groupShowPage",data.count,function (begin,end) {
                        getGroupViewData(begin,end,false);
                    });
                }
            });
        }catch (e){
            alert(e);
        }

    }

    function createTableHtml(viewVlaue) {
        var html = "<tr>";
        var groupId = "";
        $.each($("#groupTable thead tr td"),function (index,value,array) {
            var key =$(value).attr("id");

            if(key != undefined){
                if(key == "groupId"){
                    html = html+"<td class='hide'>"+viewVlaue[key]+"</td>";
                    groupId = viewVlaue[key];
                }else{
                    html = html+"<td>"+viewVlaue[key]+"</td>";
                }

            }
        });
        html = html+"<td><a onclick='openGroupView("+groupId+")'>成员查看/</a><a onclick='exitGroup("+groupId+")'>退出</a></td>"+"</tr>";
        return html;z
    }
    //查看组的详细内容
    function openGroupView(groupId){
        layer.open({
            type: 2,
            title: '用户成员',
            shadeClose: true,
            shade: false,
            maxmin: true, //开启最大化最小化按钮
            area: ['893px', '600px'],
            content: './member?groupId='+groupId
        });
    }
    //退出组
    function exitGroup(groupId){
        layer.confirm('确定要退出？', {
            btn: ['确定','取消'] //按钮
        }, function(){
            doPostAjax("group_exitGroup",{"groupId":groupId},function (data) {
                if(data.result == "Y"){
                    layer.msg('已经退出', {icon: 1});
                    getGroupViewData(0,10);
                }
            });
        });
    }
    //加入一个团
    function addGroup() {
        layer.open({
            type: 2,
            title: '加入团',
            shadeClose: true,
            shade: false,
            maxmin: true, //开启最大化最小化按钮
            area: ['893px', '600px'],
            content: './addGroup'
        });
    }
    /**
     * 打开新增团的页面
     */
    function createGroup() {
        layer.open({
            type: 2,
            title: '新增团',
            shadeClose: true,
            shade: false,
            maxmin: true, //开启最大化最小化按钮
            area: ['893px', '600px'],
            content: './createGroup'
        });
    }
    function showGroupMessage() {
        layer.open({
            type: 2,
            title: '申请消息',
            shadeClose: true,
            shade: false,
            maxmin: true, //开启最大化最小化按钮
            area: ['893px', '600px'],
            content: './showApplyGroupMessage'
        });
    }
</script>
</body>
</doctypehtml>
