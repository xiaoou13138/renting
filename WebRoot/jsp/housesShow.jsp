<%--
  Created by IntelliJ IDEA.
  User: zuowy
  Date: 2017/4/11
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>房间展示</title>
    <script src="js/head.js"></script>
</head>
<body>
<div class="bg" id="mainBlock">
    <div class="container" style="height:100%" id="mainContainer">
        <div class="row" style="margin-top: 50px">
            <div class="grid-search-center center-block" >
                <div class="grid-left"><input type="text"  class="myform-control" id="searchContent"></div>
                <div class="grid-right"><a class="search-button btn-primary no-line " onclick="houseShowSearch(0,10,true,3)">搜索</a></div>
            </div>
        </div>
        <div class="row box-border" >
            <div class="container-fluid" id = "products">

            </div>
        </div>
        <div>
            <ul class="pagination"></ul>
        </div>
    </div>
</div>
<script>
    var userType;
    var viewType;
    $(document).ready(function(){
        userType = '${userType}';
        viewType = getParam("viewType");
        houseShowSearch(0,10,true,3);
        if(viewType == 2){
            $("#mainBlock").attr("class","bg-no-padding");
        }
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
        try{
            var information = "";
            if(info.information !=undefined){
                information = info.information;
            }
            var html = "<div class='row house-show-block'>"
                +"<div class='col-xs-3'>"
                +"<div class='border-just-right' style=\""+createImageHtml(info.mainPicture,200,200)+"\"></div>"
                +"</div>"
                +"<div class='col-xs-6'>"
                +"<div class='container-fluid'>"
                +"<div class='row text-left house-show-title'>"
                +"<a href = './houseDetails?houseId="+info.houseId+"'>"+info.houseName+"</a>"
                +"</div>"
                +"<div class='row house-show-content text-left'><FONT color=#000000>"
                +info.houseType+"&nbsp;"+info.houseArea+"平方米" +"<p><FONT color=#666666>描述:"+information
                +"</div>"
                +"<div class='row house-show-content text-left'><FONT color=#03A89E>"
                +"地址:"+info.houseAddress
                +"</div>"
                +"</div>"
                +"</div>";
            var rightBlock ;
            if(userType == "admin" && viewType == 2){
                rightBlock = "<div class='col-xs-3'><div class='row'><a href='javascript:void(0)' onclick='deleteHouseInfo("+info.houseId+")' class='pull-right' style='padding-right: 50px;font-size: 20px;color: red'>删除</a></div><div class='row admin-money-div'><FONT color=#FF9912>"+info.money+"元/月"+"</div></div>";
                $("#mainContainer").attr("class","container-fluid");
            }else{
                rightBlock = "<div class='col-xs-3 money-div'><FONT color=#FF9912>"+info.money+"元/月"+"</div>";
            }
            html = html+rightBlock+"</div>";
            return html;
        }catch (e){
            alert(e);
        }


    }

    function deleteHouseInfo(houseId) {
        //删除房子
        var confirmLayer = layer.confirm("确定要删除该房子吗", {
            btn: ['确定','取消'] //按钮
        },function () {
            //确定删除用户信息
            beginLoad("删除成功","删除失败",5000,function () {
                location.reload();
            });
            doPostAjaxAndLoad("adminHouseManage_dealAction",{actionType:1,houseId:houseId},function (data) {
            });
            layer.close(confirmLayer);
        });
    }
</script>
</body>
</html>
