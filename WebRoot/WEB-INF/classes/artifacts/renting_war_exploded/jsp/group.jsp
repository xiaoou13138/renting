<%--
  Created by IntelliJ IDEA.
  User: xiaoou
  Date: 2017/4/1
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container">
    <table class="table table-bordered" style="width: 800px; margin: auto" id="groupTable">
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
    <div>
        <ul class="pagination"></ul>
    </div>

</div>
<script>
    $(document).ready(function () {
        getGroupViewData(0,10);
    });
    //初始化当前页面的分页组件

    //ajax调用后台数据
    function getGroupViewData(begin,end){
        try{
            doPostAjaxAndDealPage("group_getGroupInfo",{"begin":begin,"end":end},function (data) {
                $.each(data.groupList,function (index,value,array) {
                    $("#groupTable tbody").html(createTableHtml(value));
                });
                //分页的重新初始化
                setPage(data.count,function (begin,end) {
                    getGroupViewData(begin,end);
                });
            });
        }catch (e){
            alert(e);
        }

    }
    function getViewDataList(){

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
        html = html+"<td><a onclick='openGroupView("+groupId+")'>成员查看</a></td>"+"</tr>";
        return html;
    }
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
