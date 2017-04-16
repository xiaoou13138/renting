<%--
  Created by IntelliJ IDEA.
  User: xiaoou
  Date: 2017/4/6
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>贴吧</title>
    <script src="js/head.js"></script>
</head>
<body>
<div class="bg" style="background-color: #e7e9ff;">
    <div style="width: 80%;margin:auto">
        <div class="container-fluid" >
            <div class="row">
                <div class="col-md-offset-3 col-md-5"><input type="text"  class="form-control" id="searchContent"></div>
                <a class="col-md-1 search-button btn-primary no-line" onclick="firstSearch()">搜索</a>
            </div>
            <div class="row" style="margin-top: 50px">
                <div class="col-md-9" style="background-color: #e7e9ff" id="card">
                    <div class="row">
                        <div class=" col-md-offset-2 col-md-2">
                            <select class="form-control" id ="houseLimit">
                                <option value="0">请选择</option>
                                <option value="1">有房</option>
                                <option value="2">无房</option>
                            </select>
                        </div>
                        <div class=" col-md-offset-2 col-md-2">
                            <select class="form-control" id ="sexLimit">
                                <option value="">请选择</option>
                                <option value="男">男</option>
                                <option value="女">女</option>
                            </select>
                        </div>
                    </div>
                    <div class="container-fluid" id="cards" style="background-color: #ffffff">
                    </div>
                    <div>
                        <ul class="pagination"></ul>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="row">
                        <div class="col-md-6"><button type="button" class="btn btn-primary pull-left" style="margin-left: 16%;" onclick="createCard()">发布帖子</button></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function () {
        //获取帖子的内容
        getPostCardInfo(0,10,true);
    });
    function getPostCardInfo(begin,end,isFirst){
        try{
            var searchContent =$("#searchContent").val();
            var houseLimit = $("#houseLimit").val();//是否有发房
            var sexLimit =  $("#sexLimit").val();//性别限制
            var json = {
                "begin":begin,
                "end":end,
                "searchContent":searchContent,
                "houseLimit":houseLimit,
                "sexLimit":sexLimit
            };
            doPostAjax("postBar_getPostCardInfo",json,function (data) {
                if(data.result =="Y"){
                    var htmlArray  = new Array();
                    $.each(data["postBarList"],function (index,value,array) {
                        htmlArray.push(createPostInfoHtml(value));
                    });
                    $("#cards").html(String.prototype.concat.apply("", htmlArray));
                    if(isFirst){
                        setPage(data.count,function (begin,end) {
                            getPostCardInfo(begin,end,false);
                        });
                    }
                }
            });
        }catch (e){
            alert(e);
        }
    }
    function createPostInfoHtml(value){
        debugger;
        var pictureHtmlArray  = new Array();
        if(value["pictureList"]){
            $.each(value["pictureList"],function (index,value,array) {
                pictureHtmlArray.push(createPictureHtml(value));
            });
        }
        var html = "<div class='row' style='padding: 10px;border-bottom:1px solid #502c26;'><div class='row' style='padding: 10px;'><a href='./cardDetail?postId="
            +value.postId+"'><p class='lead text-left'>"+value.title+"</p></a></div><div class='row ' style='text-align: left; color: #000;padding: 10px;'>"
            +value.content+"</div><div class='row'>"+
            String.prototype.concat.apply("", pictureHtmlArray)
            +"</div></div>"
        return html;
    }
    function createPictureHtml(value){
        return "<div class='postBar-picture pull-left' style=\"background:url('./showImage?imageFile="+value
        +"');background-repeat: no-repeat;background-position: center center;background-size:150px 150px; \"></div>"

    }
    //发布帖子
    function createCard(){
        layer.open({
            type: 2,
            title: '发布帖子',
            shadeClose: true,
            shade: false,
            maxmin: true, //开启最大化最小化按钮
            area: ['893px', '600px'],
            content: './createCard'
        });
    }
</script>
</body>
</html>
