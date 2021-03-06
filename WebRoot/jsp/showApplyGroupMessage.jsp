<%--
  Created by IntelliJ IDEA.
  User: zuowy
  Date: 2017/4/10
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<head>
    <title>Title</title>
    <script src="js/head.js"></script>
</head>
<body>
<div class="container" style="height: 1000px">
    <div class="row">
        <table class="table table-bordered" style="width: 800px; margin: auto" id="groupTable">
            <thead>
            <tr>
                <td id="applyId" class="hide">消息主键</td>
                <td id="applyUserId">申请人编码</td>
                <td id="applyUserName">申请人名称</td>
                <td id="groupId">申请的团编码</td>
                <td id="groupName">申请的团的名称</td>
                <td >状态</td>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
        <div style="width: 800px" class="center-block">
            <ul class="pagination center-block" ></ul>
        </div>
    </div>
</div>
<script>
    $(document).ready(function () {
        getMessage();
    });
    function getMessage(){

        doPostAjax("showMessage_getMessage",{},function (data) {
           if(data.result =="Y"){
               var htmlArray = new Array();
               $.each(data.messageList,function (index,value,array) {
                   htmlArray.push(createTableHtml(value));
               });
               $("#groupTable tbody").html(String.prototype.concat.apply("", htmlArray));
           }
        });
    }
    function createTableHtml(viewVlaue) {
        var html = "<tr>";
        var applyId = "";
        $.each($("#groupTable thead tr td"),function (index,value,array) {
            var key =$(value).attr("id");
            if(key != undefined){
                if(key == "applyId"){
                    html = html+"<td class='hide'>"+viewVlaue[key]+"</td>";
                    applyId = viewVlaue[key];
                }else{
                    html = html+"<td>"+viewVlaue[key]+"</td>";
                }

            }
        });
        if(viewVlaue.state == 1){
            html = html+"<td><a onclick='dealMessage("+applyId+",1"+")'>同意/</a><a onclick='dealMessage("+applyId+",2"+")'>拒绝</a></td>"+"</tr>";
        }else if(viewVlaue.state == 2){
            html = html+"<td>"+"已批准加入"+"</td></tr>";
        }else if(viewVlaue.state == 3){
            html = html+"<td>"+"被拒绝加入"+"</td></tr>";
        }

        return html;
    }
    function dealMessage(applyId,dealType){
        var dealTypeStr = "";
        if(dealType ==1){
            dealTypeStr = "accept";
            doPostAjax("showMessage_dealMessage",{"applyId":applyId,"dealType":dealTypeStr},function (data) {
                if(data.result =="Y"){
                    layer.confirm('批准成功', {
                        btn: ['确定'] //按钮
                    });
                    location.reload();
                }
            })
        }else if(dealType ==2){
            dealTypeStr = "refuse";
            layer.prompt({title: '请输入拒绝理由,不填写则点击取消', formType: 2,end:function () {
                doPostAjax("showMessage_dealMessage",{"applyId":applyId,"dealType":dealTypeStr,text:text},function (data) {
                    if(data.result =="Y"){
                        layer.confirm('操作成功', {
                            btn: ['确定'] //按钮
                        });
                        location.reload();
                    }
                })
            }}, function(text, index){
                layer.close(index);
                content = text;
            });
            var content = "";
            layer.prompt({title: '请输入拒绝理由,不填写理由则点击取消', formType: 2,end:function () {
                doPostAjax("showMessage_dealMessage",{"applyId":applyId,"dealType":dealTypeStr,content:content},function (data) {
                    if(data.result =="Y"){
                        layer.confirm('操作成功', {
                            btn: ['确定'] //按钮
                        });
                        location.reload();
                    }
                });
            }}, function(text, index){
                layer.close(index);
                content = text;
            });
        }

    }
</script>
</body>
</html>
