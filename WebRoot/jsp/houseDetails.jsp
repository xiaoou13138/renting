<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <title>Title</title>
    <script src="js/head.js"></script>
    <script src="http://api.map.baidu.com/api?v=2.0&ak=09BOANftFs7uVaipXxFGsRF2txzTOiT9" type="text/javascript"></script>
    <style>
        #allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
    </style>
</head>
<body>
<div class="bg">
    <div class="house-details ">
        <div class="container" style="background-color: #ffffff">
            <div class="row house-details-block">
                <div class="col-md-6 box-border" style="height: 500px">
                    <div id="gallery" class="ad-gallery">
                        <div class="ad-image-wrapper"></div>
                        <div class="ad-controls"></div>
                        <div class="ad-nav">
                            <div class="ad-thumbs">
                                <ul class="ad-thumb-list" id="detailImageList">
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-6 box-border" style="height: 500px">
                    <div class="row house-details-text-div ">
                        <div class="col-md-3 text-right" >租金:</div>
                        <div class="col-md-9 text-left" id="price"></div>
                    </div>
                    <div class="row house-details-text-div">
                        <div class="col-md-3 text-right" >房屋概况:</div>
                        <div class="col-md-9 text-left" id="dsc"></div>
                    </div>
                    <div class="row house-details-text-div">
                        <div class="col-md-3 text-right" >地址:</div>
                        <div class="col-md-9 text-left" id="address"></div>
                    </div>
                    <div class="row house-details-text-div">
                        <div class="col-md-3 text-right" >手机号码:</div>
                        <div class="col-md-9 text-left" id="phone"></div>
                    </div>
                    <div class="row house-details-text-div" style="padding-top: 20px;padding-left: 20px">
                        <div class="col-md-2 text-left" ><button type="button" class="btn btn-warning" onclick="collect()" id="collect">收藏</button></div>
                        <div class="col-md-2 text-left" ><div class="dropdown">
                            <button class="btn btn-default dropdown-toggle btn-warning" type="button" id="appointment" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                预约
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu" aria-labelledby="appointment">
                                <li><a href="javascript:void(0);" onclick="appointment('personal')">个人预约</a></li>
                                <li><a href="javascript:void(0);" onclick="appointment('group')">团体预约</a></li>
                            </ul>
                        </div></div>

                    </div>
                </div>
            </div>

            <div class="row house-details-block box-border" id="information">
                <h1 class="h1">房源描述</h1>
            </div>

            <div class="row house-details-block box-border" id="facility">
                <h1 class="h1">配套设施</h1>
            </div>

            <div class="row house-details-block " style="height: 500px;">
                <h1 class="h1">地图</h1>
                <div id="allmap"></div>
            </div>
        </div>
    </div>
</div>
<script>
    var houseId = 0;
    var userId = 0;
    var collectState = false;
    $(document).ready(function () {
        userId =  '${userId}';
        houseDetailsViewInit();
    });
    function houseDetailsViewInit() {
        houseId = getParam("houseId");

        doPostAjax("houseDetails_getBaseInfo",{"houseId":houseId},function (data) {
            var pictureHtmlArray = new Array;
            $.each(data.pictures,function (index,value,array) {
                pictureHtmlArray.push(createImageHtml(value));
            });
            $("#detailImageList").html(String.prototype.concat.apply("", pictureHtmlArray));
            $('.ad-gallery').adGallery();//先初始化图片插件
            //图片右边的信息的添加
            $("#price").text(data.money+"元/月");
            $("#dsc").text(data.houseArea+"平方米  "+data.houseType);
            $("#address").text(data.houseAddress);
            $("#phone").text(data.phone);

            $("#information").append(data.information);//房源描述
            collectState = changeCollectState(data.collect);

                //配套设施
            var facilityHtml = new Array;
            $.each(data.facility,function (index,value,array) {
                facilityHtml.push(createFaclityHtml(value));
            });
            $("#facility").append(String.prototype.concat.apply("", facilityHtml));
            map(data.houseAddress);
    });
        //需要的数据有  图片 房子的信息  房源描述 配套设施 位置  放到地图中去
    }

    function createFaclityHtml(value) {
        var html = "<div>"+value+"&nbsp</div>";
        return html;
    }

    function createImageHtml(imageName){
       var  html="<li><a href='showImage?imageFile="+imageName+"'><img src='showImage?imageFile="+imageName+"'/></a></li>";
       return html;
    }
    
    function map(address) {
        var map = new BMap.Map("allmap");
        var point = new BMap.Point(116.331398,39.897445);
        map.centerAndZoom(point,12);
        // 创建地址解析器实例
        var myGeo = new BMap.Geocoder();
        // 将地址解析结果显示在地图上,并调整地图视野
        myGeo.getPoint(address, function(point){
            if (point) {
                map.centerAndZoom(point, 16);
                map.addOverlay(new BMap.Marker(point));
            }else{
                alert(address+"您选择地址没有解析到结果!");
            }
        }, "南昌市");
    }
    //收藏按钮
    function collect() {
        debugger;
        if(userId ==0 || userId ==undefined){
            layer.confirm('请先登录', {
                btn: ['确定','取消'], //按钮
            },function () {
                parent.location.href='./login'
            });
        }else{
            var load = layer.load(1, {
                shade: [0.1,'#fff'] //0.1透明度的白色背景
            });
            var notSuccess = true;
            var message = "";
            if(collectState){
                message = "取消收藏";
            }else{
                message = "收藏";
            }
            doPostAjax("houseDetail_collect",{houseId:houseId,collectState:collectState},function (data) {
                if(data.result =="Y"){
                    layer.close(load);
                    notSuccess = false;
                    collectState = changeCollectState(collectState);
                }else{
                    layer.close(load);layer.confirm(message+"成功", {btn: ['确定'] });
                }
            });
            setTimeout(function () {
                if(notSuccess){
                    layer.msg(message+"失败");
                    layer.close(load);
                }
            }, 10000);
        }
    }

    function changeCollectState(state){
        debugger;
        if(state){
            $("#collect").text("已收藏");
            return false;
        }else{
            $("#collect").text("收藏");
            return true;
        }
    }
    /**
     * 预约申请
     * @param type
     */
    function appointment(type) {
        if(userId > 0 ){
            if(type == "personal"){
                var json = {
                    houseId:houseId,
                    appointmentType:1
                };
                var load = layer.load(1, {
                    shade: [0.1,'#fff'] //0.1透明度的白色背景
                });
                var notSuccess = true;
                doPostAjax("houseDetail_appointment",json,function (data) {
                    if(data.result =="Y"){
                        layer.close(load);
                        notSuccess = false;
                        layer.confirm('预约成功', {
                            btn: ['确定'] //按钮
                        });
                    }else{
                        notSuccess = false;
                        layer.close(load);
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
            }else if(type =="group"){
                //团体预约
                layer.open({
                    type: 2,
                    title: '用户的组',
                    shadeClose: true,
                    shade: false,
                    maxmin: true, //开启最大化最小化按钮
                    area: ['893px', '600px'],
                    content: './appointmentGroupTableShow?houseId='+houseId
                });
            }
        }else{
            layer.confirm('请先登录', {
                btn: ['确定','取消'], //按钮
            },function () {
                parent.location.href='./login'
            });
        }

    }
</script>
</body>
</html>
