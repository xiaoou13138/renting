<%--
  Created by IntelliJ IDEA.
  User: zuowy
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
    <div class="container ">
        <div class="row">
            <div class="col-xs-10 box-border">
                <div class="row"  style='padding: 10px;border-bottom:2px solid #502c26; margin-bottom: 20px' id="head">
                    <div class="col-xs-1" id="hostName" style="margin-top: 40px">

                    </div>
                    <div class="col-xs-1" style="color: #0f0f0f">
                    </div>
                    <div class="col-xs-7" style="color: #0f0f0f">
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

                </div>

                <div class="row" >
                    <div class="col-xs-12" id="cardList">
                        <div class="row">
                            <div class="col-xs-1" style="color: #0f0f0f">
                            </div>
                            <div class="col-xs-2">
                                <div class="img-circle"style=" hight:100%;weight:100%;" src="showImage?imageFile=default_user_image.png">
                                </div>
                                <div class="col-xs-1" style="color: #0f0f0f">
                                </div>
                                <div class="col-xs-8" style="color: #0f0f0f">
                                    <div style='text-align: left; color: #000;'>
                                        内容
                                    </div>
                                    <div id="pictureList1">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
                <div>
                    <ul class="pagination"></ul>
                </div>
            </div>
            <div class="col-xs-2">
                <div class="row" style="padding-bottom: 10px">
                    <button type="button" class="btn btn-primary pull-left" style="margin-left: 16%;" onclick="reply(1)">评论</button>
                </div>
                <div class="row">
                    <button type="button" class="btn btn-primary pull-left" style="margin-left: 16%;" onclick="reply(2)">私信</button>
                </div>
            </div>
        </div>
</div>
</div>
<script>
    var postId = 0;
    var userType;
    var viewType;
    var hostId;
    $(document).ready(function () {
        postId = getParam("postId");
        viewType = getParam("viewType");
        userType = '${userType}';
        cardDetailViewInit(postId,0,10,true);

    });
    function cardDetailViewInit(postId,begin,end,isFirst){
        if(postId !=0){
            doPostAjax("cardDetail_getCardDetailInfo",{postId:postId,begin:begin,end:end},function (data) {
                if(data.result =="Y"){
                    hostId = data['head']['hostId'];
                    $("#hostName").text(data['head']['hostName']);
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
        var html="<div class='row ' style='border-bottom:1px dashed #000;'>"
            +"<div class='col-xs-2'>"
            +"<div class='row'><div class='col-xs-12 '>"
            +"</div></div>"
            +"<div class='row'><div class='col-xs-12 text-center' style='margin-top: 1px'>"+value.senderName+":"+"</div></div>"
            +" </div>"
            +"<div class='col-xs-9' style='color: #0f0f0f'>"
            +"<div style='text-align: left; color: #000;'>"
            +value["content"]
            +"</div>"
            +"</div>";
            if("admin"== userType && viewType ==2){
                html = html+"<div class='col-xs-1'><a href='javascript:void(0)' onclick='deleteComment("+value["messageId"]+")' '>删除</a></div>";
            }
            html = html+"</div>";
            return html ;
    }

    function reply(type){
        var title = "";
        if(type == 1){
            title = "评论";
        }else{
            title = "私信";
        }
        layer.prompt({title: title, formType: 2,area: ['893px', '100px']}, function(text, index){
            layer.close(index);
            var json = {
                postId:postId,
                content:text,
                actionType:type,
                hostId:hostId
            };
            beginLoad("回复成功","回复失败",5000,function () {
                location.reload();
            });
            doPostAjaxAndLoad("cardDetail_saveReplyInfo",json,function (data) {
            });
        });
    }

    function deleteComment(messageId) {
        var confirmLayer = layer.confirm("确定要删除该评论吗", {
            btn: ['确定','取消'] //按钮
        },function () {
            //确定删除用户信息
            beginLoad("删除成功","删除失败",5000,function () {
                location.reload();
            });
            doPostAjaxAndLoad("adminPostBarManage_dealAction",{messageId:messageId,actionType:2},function (data) {
            });
        });
    }
</script>
</body>
</html>
