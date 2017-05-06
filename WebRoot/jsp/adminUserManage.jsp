<%--
  Created by IntelliJ IDEA.
  User: xiaoou
  Date: 2017/5/5
  Time: 11:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="js/head.js"></script>
</head>
<body>
<div class="container-fluid" style="background-color:#e7e9ff">
    <div class="panel panel-primary" style="background-color: white">
        <div class="panel-heading">
            <h3 class="panel-title  text-left">用户管理</h3>
        </div>
        <div class="panel-body" style="height: 756px">
            <div class="row">
                <div class="col-md-offset-3 col-md-4">
                    <input type="text"  class="myform-control" id="searchContent" placeholder="用户名或者账号">
                </div>
                <div class="col-md-2">
                    <a class="search-button btn-primary no-line " onclick="getTableData(0,10,true)">查询</a>
                </div>
            </div>
            <div class="row">
                <button type="button" class="btn btn-primary pull-right" style="margin-left: 1%;" onclick="deleteUser()">删除</button>
            </div>
            <div class="row">
                <table class="table table-bordered" style=" margin: 10px" id="userTable">
                    <thead>
                    <tr>
                        <td type="checkBox" data-key="userId"></td>
                        <td type="normal" data-key="userId">用户主键</td>
                        <td type="normal" data-key="userCode">账号</td>
                        <td type="normal" data-key="userName">用户名</td>
                        <td type="normal" data-key="realName">真实名称</td>
                        <td type="normal" data-key="phone">手机号码</td>
                        <td type="normal" data-key="age">年龄</td>
                        <td type="normal" data-key="sex">性别</td>
                        <td type="normal" data-key="createDate">创建时间</td>
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
        getTableData(0,10,true);
    });
    function getTableData(begin,end,isFirst) {
        var searchContent = $("#searchContent").val();
        var searchType = 2;
        if(checkMail(searchContent)){
            searchType=1;
        }
        beginLoad("","",5000);
        doPostAjaxAndLoad("adminUserManage_dealAction",{begin:begin,end:end,actionType:1,searchContent:searchContent,searchType:searchType},function (data) {
            if(data.result == "Y"){
                debugger;
                createTableHtmlUtil("userTable",data.userList);
                if(isFirst){
                    setPage(data.count,function (begin,end) {
                        getTableData(begin,end,false);
                    });
                }
            }
        });

    }

    function deleteUser() {
        var array = getTableCheckData("userTable");
        var confirmLayer = layer.confirm("确定要删除该用户吗", {
            btn: ['确定','取消'] //按钮
        },function () {
            //确定删除用户信息
            beginLoad("删除成功","删除失败",5000,function () {
                location.reload();
            });
            doPostAjaxAndLoad("adminUserManage_dealAction",{actionType:2,userList:array},function (data) {
            });
            layer.close(confirmLayer);
        });
    }
</script>
</body>
</html>
