<%--
  Created by IntelliJ IDEA.
  User: xiaoou
  Date: 2017/4/10
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="js/head.js"></script>
</head>
<body>
<div class="bg">
    <div class="container" style="height: 1000px;background-color: white">
        <div class="row">
            <div class="grid-search-center center-block" style="margin-top: 100px">
                <div class="grid-left"><input type="text"  class="myform-control" id="searchContent"></div>
                <div class="grid-right"><a class="search-button btn-primary no-line " onclick="getGroupInfo(0,10,true)">搜索</a></div>
            </div>
        </div>
        <div class="row">
            <table class="table table-bordered" style="margin: auto" id="groupTable">
                <thead>
                <tr>
                    <td id="groupId" >组ID</td>
                    <td id="groupName">组名</td>
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
            <ul class="pagination center-block" ></ul>
        </div>
    </div>
</div>

<script>
    $(document).ready(function () {
        getGroupInfo(0,10,true);
    });

    function getGroupInfo(begin,end,isFirst){
        var searchContent = $("#searchContent").val();
        try{
            doPostAjaxAndDealPage("group_getJoinGroupInfo",{"searchContent":searchContent,"begin":begin,"end":end},function (data) {
                var htmlArray = new Array();
                $.each(data.groupList,function (index,value,array) {
                    htmlArray.push(createTableHtml(value));
                });
                $("#groupTable tbody").html(String.prototype.concat.apply("", htmlArray));
                //分页的重新初始化
                if(isFirst){
                    setPage(data.count,function (begin,end) {
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
                    html = html+"<td >"+viewVlaue[key]+"</td>";
                    groupId = viewVlaue[key];
                }else{
                    html = html+"<td>"+viewVlaue[key]+"</td>";
                }

            }
        });
        html = html+"<td><a onclick='openGroupView("+groupId+")'>成员查看/</a><a onclick='addGroup("+groupId+")'>加入</a></td>"+"</tr>";
        return html;
    }

    function addGroup(groupId) {
        doPostAjax("group_addGroup",{"groupId":groupId},function (data) {
            if(data.result =="Y"){
                layer.confirm('申请成功,已经发送申请消息', {
                    btn: ['确定'] //按钮
                });
            }else{
                layer.confirm(data.rtnMessage, {
                    btn: ['确定'] //按钮
                });
            }
        })
    }
    /**
     * 成员查看函数
     * @param groupId
     */
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
</script>
</body>
</html>
