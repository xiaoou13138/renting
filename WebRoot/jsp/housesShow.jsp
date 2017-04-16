<%--
  Created by IntelliJ IDEA.
  User: xiaoou
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
<div class="bg">
    <div class="container" style="height: 100%">
        <div class="row">
            <div class="grid-search-center center-block" style="margin-top: 50px">
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
    $(document).ready(function(){
        houseShowSearch(0,10,true,3);
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
        var html = "<div class='row house-show-block'>"
        +"<div class='col-md-3'>"
        +"<div class='border-just-right' style=\""+createImageHtml(info.mainPicture,200,200)+"\"></div>"
        +"</div>"
        +"<div class='col-md-6'>"
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
        +"<div class='col-md-3 money-div'>"
        +info.money+"元/月"
        +"</div>"
        +"</div>";
        return html;
    }


</script>
</body>
</html>
