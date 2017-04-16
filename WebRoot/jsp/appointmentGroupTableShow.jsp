<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>可预约的组</title>
    <script src="js/head.js"></script>
</head>
<body>
<div class="container" >
    <div class="row">
        <table class="table table-bordered" style="width: 800px; margin: auto" id="groupTable">
            <thead>
            <tr>
                <td id="radioToChoice"></td>
                <td id="groupId" class="hide">组ID</td>
                <td id="groupName">组名</td>
                <td id="groupAddress">区域</td>
                <td id="groupNum">组最大人数</td>
                <td id="currentNum">当前人数</td>
                <td id="infor">组描述</td>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
        <div>
            <ul class="pagination center-block"></ul>
        </div>
    </div>
    <div class="row" style="margin-top: 50px;">
        <button type="button" class="btn btn-primary center-block" onclick="confirmAppointment()">确定预约</button>
    </div>
</div>
<script>
    var houseId = 0;
    $(document).ready(function () {
        getGroupViewData(0,10,true);
        houseId = getParam("houseId");
    });
    function getGroupViewData(begin,end,isFirst){
        try{
            doPostAjaxAndDealPage("group_getAppointmentGroupInfo",{"begin":begin,"end":end},function (data) {
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
                    html = html+"<td class='hide'>"+viewVlaue[key]+"</td>";
                    groupId = viewVlaue[key];
                }else if(key  =="radioToChoice"){
                    html = html+"<td><input type='radio' name='appointment'  value='"+viewVlaue["groupId"]+"'></td>";

                }else{
                    html = html+"<td>"+viewVlaue[key]+"</td>";
                }
            }
        });
        return html;
    }
    
    function confirmAppointment() {
        debugger;
        var groupId = $('#groupTable input:radio:checked').val();
        if(groupId == undefined){
            layer.confirm('请先选择一个组', {
                btn: ['确定'], //按钮
            });
            return ;
        }
        var json = {
            groupId:groupId,
            houseId:houseId
        };
        var load = layer.load(1, {
            shade: [0.1,'#fff'] //0.1透明度的白色背景
        });
        var notSuccess = true;
        doPostAjax("group_confirmAppointment",json,function (data) {
           if(data.result =="Y"){
               layer.close(load);
               notSuccess = false;
               layer.confirm('预约成功', {
                   btn: ['确定'] //按钮
               });
           }else{
               layer.close(load);
               notSuccess = false;
               layer.confirm(data.rtnMessage, {
                   btn: ['确定'] //按钮
               });
           }
        });
        setTimeout(function () {
            if(notSuccess){
                layer.msg("预约失败");
                layer.close(load);
            }
        }, 10000);

    }
</script>
</body>
</html>
