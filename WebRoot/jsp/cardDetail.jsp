<%--
  Created by IntelliJ IDEA.
  User: xiaoou
  Date: 2017/4/13
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>帖子详细</title>
    <script src="js/head.js"></script>
</head>
<body>
<div class="bg">
    <div class="container">

        <div class="row"  style='padding: 10px;border-bottom:2px solid #502c26; margin-bottom: 20px' id="head">
            <div class="col-md-2">
                    <img class="img-circle"style=" hight:100%;weight:100%;" src="showImage?imageFile=default_user_image.png" id="headImg">
                </div>
            <div class="col-md-1" style="color: #0f0f0f">
            </div>
        <div class="col-md-7" style="color: #0f0f0f">
            <div class="row"style="hight:20px">
                <p>&nbsp;</p>
            </div>
            <div class="row">
                <p class='lead text-left' id="title">标题</p>
            </div>
            <div style='text-align: left; color: #000;' id="headContent">
                内容
            </div>
        </div>
        <div class="col-md-2"><button type="button" class="btn btn-primary pull-left" style="margin-left: 16%;" onclick="reply()">回复</button></div>
    </div>
    <div class="row" id="cardList">
        <div class="row">
            <div class="col-md-1" style="color: #0f0f0f">
            </div>
            <div class="col-md-2">
                <div class>
                    < class="img-circle"style=" hight:100%;weight:100%;" src="showImage?imageFile=default_user_image.png">
            </div>
            <div class="col-md-1" style="color: #0f0f0f">
            </div>
            <div class="col-md-8" style="color: #0f0f0f">
                <div style='text-align: left; color: #000;'>
                    内容
                </div>
                <div id="pictureList1">
                </div>
            </div>
        </div>
    </div>
    <div style="">
        <ul class="pagination" id="detailPage"></ul>
    </div>
</div>
</div>
<script>
    var postId = 0;
    $(document).ready(function () {
        postId = getParam("postId");
        cardDetailViewInit(postId,0,10,true);
    });
    function cardDetailViewInit(postId,begin,end,isFirst){
        if(postId !=0){
            doPostAjax("cardDetail_getCardDetailInfo",{postId:postId,begin:begin,end:end},function (data) {
                if(data.result =="Y"){
                    if(begin == 0){
                        $("#title").text(data["head"]["title"]);
                        $("#headContent").text(data["head"]["content"]);
                        $("#head").show();
                    }else{
                        $("#head").hide();
                    }
                    var htmlArray = new Array();
                    $.each(data["cardList"],function (index,value,array) {
                        htmlArray.push(createCardHtml(value));
                    });
                    $("#cardList").html(String.prototype.concat.apply("", htmlArray));
                    if(isFirst){
                        setPage(data.count,function (begin,end) {
                            cardDetailViewInit(postId,begin,end,false);
                        });
                    }
                }else{
                    layer.confirm('服务器返回数据失败', {
                        btn: ['确定'] //按钮
                    });
                }
            })
        }
    }
    function createCardHtml(value){
        var html="<div class='row'>"
            +"<div class='col-md-1' style='color: #0f0f0f'>"
            +" </div>"
            +"<div class='col-md-2'>"
            +"<a style=' ' href='#'  >"
            +"<img class='img-circle'  src='showImage?imageFile=default_user_image.png'>"
            +"</a>"
            +" </div>"
            +"<div class='col-md-1' style='color: #0f0f0f'>"
            +" </div>"
            +"<div class='col-md-8' style='color: #0f0f0f'>"
            +"<div style='text-align: left; color: #000;'>"
            +value["content"]
            +"</div>"
            +"</div>"
            +"</div>";
        return html;
    }

    function reply(){
        layer.prompt({title: '回复', formType: 2,area: ['893px', '100px']}, function(text, index){
            layer.close(index);
            var json = {
                postId:postId,
                content:text
            };
            var load = layer.load(1, {
                shade: [0.1,'#fff'] //0.1透明度的白色背景
            });
            var notSuccess = true;
            doPostAjax("cardDetail_saveReplyInfo",json,function (data) {
                if(data.result == "Y"){
                    layer.close(load);
                    layer.confirm('保存成功', {
                        btn: ['确定'] //按钮
                    },function () {
                        location.reload();
                    });
                    notSuccess = false;

                }else{
                    layer.close(load);layer.confirm('保存失败:', {btn: ['确定'] });
                }
            });
            setTimeout(function () {
                if(notSuccess){
                    layer.close(load);layer.confirm('保存失败', {btn: ['确定'] });
                }
            }, 10000);
        });
    }
</script>
</body>
</html>
