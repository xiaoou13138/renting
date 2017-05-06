<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>预约管理</title>
    <script src="js/head.js"></script>
</head>
<body>
<div class="container-fluid" style="background-color: #e7e9ff">
    <div class="panel panel-primary" style="background-color: white">
        <div class="panel-heading">
            <h3 class="panel-title  text-left">我的房源</h3>
        </div>
        <div class="panel-body" style="height: 756px;overflow: auto">
            <div class="container-fluid" >
                <div class="row">
                    <div class="row" >
                        <div class="container-fluid" id = "myHouse">

                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12 center-block">
                        <div class="text-center">
                            <ul class="pagination"></ul>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
<script>
    $(document).ready(function(){
        myHouseShowSearch(0,10,true);
    });
    function myHouseShowSearch(begin,end,isFirst){
        var json = {
            "begin":begin,
            "end":end,
            showType:2
        };
        var searchContent = $("#searchContent").val();
        if(searchContent != undefined && searchContent!= ""){
            json.searchContent =searchContent;
        }
        doPostAjaxAndDealPage("house_getProduct",json,function (data) {
            if(data.result="Y"){
                var htmlArray  = new Array();
                $.each(data["houseView"],function (index,value,array) {
                    htmlArray.push(createHtmlByMyHouseInfo(value));
                });
                $("#myHouse").html(String.prototype.concat.apply("", htmlArray));
                if(isFirst){
                    setPage(data.count,function (begin,end) {
                        myHouseShowSearch(begin,end,false);
                    })
                }
            }else{
                alert("获取信息失败，请检查网络");
            }
        });
    }

    function createHtmlByMyHouseInfo(info){
        var html = "<div class='row house-show-block border-just-bottom-top' style='height:80px'>"
            +"<div class='col-xs-2'>"
            +"<div class='border-just-right' style=\""+createImageHtml(info.mainPicture,80,80)+"\"></div>"
            +"</div>"
            +"<div class='col-xs-8 text-left house-show-title'>"
            +"<a href = './houseDetails?houseId="+info.houseId+"'>"+info.houseName+"</a>"
            +"</div>"
            +"<div class='col-xs-2'><div class='row'><div class='col-xs-12'><a href='javascript:void(0)' onclick='updateInfo("+info.houseId+")'>修改</a><a href='javascript:void(0)' onclick='delInfo("+info.houseId+")'>/删除</a></div></div>"
            +"<div class='row'><div class='col-xs-12'><a onclick='appointmentDetail("+info.houseId+")'>预约详细</a><span class='label label-warning' style='font-size:16px'>"+info.appointmentCount+"</span></div></div>"
            +"</div>"
            +"</div>";
        return html;
    }
    function updateInfo(houseId) {
        window.open("./upLoadHouseInfo?viewType=2&houseId="+houseId);
    }
    function appointmentDetail(houseId) {
        layer.open({
            type: 2,
            title: '预约详细',
            shadeClose: true,
            shade: false,
            maxmin: true, //开启最大化最小化按钮
            area: ['893px', '600px'],
            content: './houseAppointment?houseId='+houseId
        });
    }
    function delInfo(houseId) {
        layer.confirm('确定删除吗', {
            btn: ['确定','取消'] //按钮
        },function () {
            var load = layer.load(1, {
                shade: [0.1,'#fff'] //0.1透明度的白色背景
            });
            var notSuccess = true;
            doPostAjax("house_delHouse",{houseId:houseId},function (data) {
                if(data.result =="Y"){
                    layer.close(load);
                    notSuccess = false;
                    location.reload();
                }else{
                    layer.close(load);
                    layer.msg("删除失败:"+data.rtnMessage);
                }
                setTimeout(function () {
                    if(notSuccess){
                        layer.close(load);layer.msg("删除失败");
                    }
                }, 10000);
            })
        });
    }
</script>
</body>
</html>
