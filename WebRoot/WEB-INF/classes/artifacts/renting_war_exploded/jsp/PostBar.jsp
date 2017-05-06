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
                <div class="col-md-offset-2 col-md-5"><input type="text"  class="form-control" id="searchContent"></div>
                <a class="col-md-1 search-button btn-primary no-line" onclick="getPostCardInfo(0,10,true)">搜索</a>
            </div>
            <div class="row" style="margin-top: 50px">
                <div class="col-md-9" style="background-color: #e7e9ff" id="card">
                    <div class="row">
                        <div class="col-md-offset-1 col-md-2 text-right">
                            <label for="houseLimit" class="">是否有房:</label>
                        </div>
                        <div class=" col-md-2">
                            <select class="form-control" id ="houseLimit">
                                <option value="0">请选择</option>
                                <option value="1">有房</option>
                                <option value="2">无房</option>
                            </select>
                        </div>

                        <div class="col-md-offset-1 col-md-2 text-right">
                            <label for="sexLimit" class="control-label">性别:</label>
                        </div>
                        <div class=" col-md-2">
                            <select class="form-control" id ="sexLimit">
                                <option value="">请选择</option>
                                <option value="男">男</option>
                                <option value="女">女</option>
                            </select>
                        </div>
                    </div>
                    <div class="row" style="hight:20px">
                        <p>&nbsp;</p>
                    </div>
                    <div class="container-fluid" id="cards" style="background-color: #ffffff">
                    </div>
                    <div>
                        <ul class="pagination"></ul>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="row">
                        <div class="col-md-6"><button id="createButton" type="button" class="btn btn-primary pull-left" style="margin-left: 16%;" onclick="createCard()">发布帖子</button></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var viewType;
    var userType;
    $(document).ready(function () {
        //获取帖子的内容
        viewType = getParam("viewType");
        userType = '${userType}';
        if(userType == 'admin' && viewType == 2){
            $("#createButton").hide();
        }

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
                "title":searchContent,
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
        var pictureHtmlArray  = new Array();
        if(value["pictureList"]){
            $.each(value["pictureList"],function (index,value,array) {
                pictureHtmlArray.push(createPictureHtml(value));
            });
        }
        var sexLimit="";
        var houseLimit="";
        if(value.sexLimit){
            sexLimit = "<span class='label label-warning' style='font-size:16px;padding-left: 10px'>"+value.sexLimit+"</span>"
        }
        if(value.houseLimit){
            houseLimit = "<span class='label label-warning' style='font-size:16px ;padding-left: 10px'>"+value.houseLimit+"</span>"
        }
        var title = "<div class='col-md-12 text-left'><a href='./cardDetail?postId="
            +value.postId+"' class='lead '>"
            +value.title+"</a>"+sexLimit+houseLimit+"</div>";
        if(viewType == 2 && userType=='admin'){
            title =title
                +"<div class='col-md-3'><a href='javascript:void(0)' onclick='deletePostCard("+value.postId+")' style='font-size: 20px;color: red;padding-right: 10px' class='pull-right'>删除</a></div>"
        }
        var html = "<div class='row' style='padding: 10px;border-bottom:1px solid #502c26;'><div class='row' style='padding: 10px;'>"
            +title
            +"</div><div class='row ' style='text-align: left; color: #000;padding: 10px;'>"
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
            area: ['893px', '500px'],
            content: './createCard'
        });
    }

    function deletePostCard(postId) {
        var confirmLayer = layer.confirm("确定要删除该帖子吗", {
            btn: ['确定','取消'] //按钮
        },function () {
            //确定删除用户信息
            beginLoad("删除成功","删除失败",5000,function () {
                location.reload();
            });
            doPostAjaxAndLoad("adminPostBarManage_dealAction",{actionType:1,postId:postId},function (data) {
            });
            layer.close(confirmLayer);
        });
    }
</script>
</body>
</html>
