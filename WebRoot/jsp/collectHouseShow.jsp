<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>收藏的房子</title>
    <script src="js/head.js"></script>
</head>
<body>
<div class="container-fluid" style="background-color: #e7e9ff">
    <div class="panel panel-primary" >
        <div class="panel-heading">
            <h3 class="panel-title  text-left">我收藏的房源</h3>
        </div>
        <div class="panel-body" style="height: 756px;overflow: auto">
            <div class="container-fluid">
                <div class="row" id = "products" >
                </div>
            </div>
            <div class="text-center">
                <ul class="pagination" style=""></ul>
            </div>
        </div>
    </div>

</div>
<script>
    $(document).ready(function(){
        houseShowSearch(0,10,true,1);
    });
    function houseShowSearch(begin,end,isFirst,showType){
        var json = {
            "begin":begin,
            "end":end,
            showType:showType
        };
        var searchContent = $("#searchContent").val();
        if(searchContent != undefined && searchContent!= ""){
            json.searchContent =searchContent;
        }
        doPostAjaxAndDealPage("house_getProduct",json,function (data) {
            if(data.result="Y"){
                var htmlArray  = new Array();
                $.each(data["houseView"],function (index,value,array) {
                    htmlArray.push(createHtmlByHouseInfo(value));
                });
                $("#products").html(String.prototype.concat.apply("", htmlArray));
                if(isFirst){
                    setPage(data.count,function (begin,end) {
                        houseShowSearch(begin,end,false,showType);
                    })
                }
            }else{
                alert("获取信息失败，请检查网络");
            }
        });
    }

    function createHtmlByHouseInfo(info){
        var html = "<div class='row house-show-block border-just-bottom'>"
            +"<div class='col-xs-3'>"
            +"<div class='border-just-right' style=\""+createImageHtml(info.mainPicture,180,180)+"\"></div>"
            +"</div>"
            +"<div class='col-xs-6'>"
            +"<div class='container-fluid'>"
            +"<div class='row text-left house-show-title'>"
            +"<a href = './houseDetails?houseId="+info.houseId+"'>"+info.houseName+"</a>"
            +"</div>"
            +"<div class='row house-show-content text-left'>"+"描述:"
            +info.houseType+"&nbsp;"+info.houseArea+"平方米"+"&nbsp;"+info.information
            +"</div>"
            +"<div class='row house-show-content text-left'>"
            +"地址:"+info.houseAddress
            +"</div>"
            +"</div>"
            +"</div>"
            +"<div class='col-xs-3 '>"
            +"<div class='row' style='height: 100px'>"+"<button type='button' class='btn btn-primary pull-right'  style='margin-right: 10px' onclick='cancelCollect("+info.houseId+")'>取消收藏</button>"+"</div>"
            +"<div class='row text-center' style='line-height: 20px;;height: 100px;font-size: 25px;color: red;'>"+info.money+"元/月"+"</div>"
            +"</div>"
            +"</div>";
        return html;
    }
    
    function cancelCollect(houseId) {
        var collectLayer = layer.confirm('确定取消收藏吗', {
            btn: ['确定'] //按钮
        },function () {
            beginLoad("","",5000,function () {
                location.reload();
            });
            doPostAjaxAndLoad("houseDetail_collect",{houseId:houseId,collectState:false},function (data) {
                if(data.result== "Y"){
                    location.reload();
                }
            });
            collectLayer.close(collectLayer);
        });
    }




</script>
</body>
</html>
