<%--
  Created by IntelliJ IDEA.
  User: zuowy
  Date: 2017/4/21
  Time: 20:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<head>
    <title>房子预约详细</title>
    <script src="js/head.js"></script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-xs-12">
            <table class="table table-bordered" style=" margin: 10px" id="groupTable">
                <thead>
                <tr>
                    <td id="appointmentId" class="hide">申请标识</td>
                    <td id="appointmentUserName">预约人名称</td>
                    <td id="phone">预约手机号码</td>
                    <td id="appointmentType">预约类型</td>
                    <td id="appointmentTime">预约时间</td>
                    <td id="action">操作</td>
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
<script>
    var houseId;
    $(document).ready(function () {
        houseId = parseInt(getParam("houseId"));
        getAppointmentViewData(0,10,true);
    });
    //ajax调用后台数据
    function getAppointmentViewData(begin,end,isFirst){
        try{
            doPostAjaxAndDealPage("showMessage_action",{"houseId":houseId,"begin":begin,"end":end,actionType:1},function (data) {
                var htmlArray = new Array();
                $.each(data.appointmentList,function (index,value,array) {
                    htmlArray.push(createTableHtml(value));
                });
                $("#groupTable tbody").html(String.prototype.concat.apply("", htmlArray));
                //分页的重新初始化
                if(isFirst){
                    setPage(data.count,function (begin,end) {
                        getAppointmentViewData(begin,end,false);
                    });
                }
            });
        }catch (e){
            alert(e);
        }
    }
    function createTableHtml(viewValue) {
        var html = "<tr>";
        var appointmentId = "";
        $.each($("#groupTable thead tr td"),function (index,value,array) {
            var key =$(value).attr("id");
            if(key == "appointmentId"){
                html = html+"<td class='hide'>"+viewValue[key]+"</td>";
                appointmentId = viewValue[key];
            }else if(key == "action"){
                html = html+"<td><a onclick='inputReason("+appointmentId+")'>拒绝并删除</a></td></tr>";
            }else{
                html = html+"<td>"+viewValue[key]+"</td>";
            }
        });
        return html;
    }
    function inputReason(appointmentId) {
        var content = "";
        layer.prompt({title: '请输入拒绝理由,不填写理由则点击取消', formType: 2,end:function () {
            doPostAjax("showMessage_action",{appointmentId:appointmentId,actionType:2,content:content},function (data) {
                if(data.result =="Y"){
                    layer.confirm('删除成功', {
                        btn: ['确定'] //按钮
                    });
                    location.reload();
                }
            });
        }}, function(text, index){
            layer.close(index);
            content = text;
        });
        /*layer.prompt({title: '请输入拒绝理由,可不填写', formType: 2}, function(text, index){
            layer.close(index);
            doPostAjax("showMessage_action",{appointmentId:appointmentId,actionType:2,content:text},function (data) {
                if(data.result =="Y"){
                    layer.confirm('删除成功', {
                        btn: ['确定'] //按钮
                    });
                    location.reload();
                }
            });
        });*/

    }
</script>
</body>
</html>
