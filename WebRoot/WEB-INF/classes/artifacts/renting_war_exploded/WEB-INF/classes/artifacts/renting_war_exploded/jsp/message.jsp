<%--
  Created by IntelliJ IDEA.
  User: xiaoou
  Date: 2017/4/2
  Time: 0:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<head>
    <title>Title</title>
    <script src="js/head.js"></script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-xs-6 " id="messageBlock">

            <div class="row wechat-content-block">
                <div class="col-md-2 text-right"><a>用户A</a>：</div>
                <div class="col-md-7"><a class="wechat-content" href="javascript:void(0)" onclick="gotoBar(1)">哈哈哈</a></div>
                <div class="col-md-3">
                    <div class="row">
                        <div class="col-xs-12">时间 </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12"><a href="javascript:void(0)" onclick="reply(2)">回复</a></div>
                    </div>
                </div>
            </div>

        </div>
    </div>
    <div class="row">
        <div class="col-xs-6 text-center">
            <ul class="pagination " ></ul>
        </div>
    </div>
</div>
<script>
    $(document).ready(function () {
        getMessageByFriendId(0,10,true);
    });
    function getMessageByFriendId(begin,end,isFirst){
        doPostAjax("message_getMessage",{begin:begin,end:end},function (data) {
            if(data.result == "Y"){
                $("#messageBlock").html(createHtml(data));
                if(isFirst){
                    setPage(data.count,function (begin,end) {
                        getMessageByFriendId(begin,end,false)
                    });
                }
            }
        })
    }
    function createHtml(contentValueList){
        var htmlArray = new Array();
        $.each(contentValueList.contentList,function (index,value,array) {
            var html = "<div class='row wechat-content-block'>"
            +"<div class='col-md-2 text-right'><a>"+value.friendName +"</a>：</div>"
            +"<div class='col-md-6'><a class='wechat-content' href='javascript:void(0)' onclick='gotoBar("+value.postBarId+")'>"+value.content+"</a></div>"
            +"<div class='col-md-4'>"
            +"<div class='row'>"
            +"<div class='col-xs-12' style='font-size: 15px'>"+value.createTime+"</div>"
            +"</div>"
            +"<div class='row'>"
            +"<div class='col-xs-12'><a href='javascript:void(0)' onclick='reply("+value.friendId+")'>回复</a></div>"
            +"</div>"
            +"</div>"
            +"</div>";
            htmlArray.push(html);
        });
        return String.prototype.concat.apply("", htmlArray);
    }
    function gotoBar(postBarId){
        window.open( './cardDetail?postId='+postBarId)
    }
    function reply(firendId) {
        layer.prompt({title: '回复', formType: 2,area: ['893px', '100px']}, function(text, index){
            layer.close(index);
            var json = {
                content:text,
                receiverId:firendId
            };
            var load = layer.load(1, {
                shade: [0.1,'#fff'] //0.1透明度的白色背景
            });
            var notSuccess = true;
            doPostAjax("cardDetail_savePrivateReplyInfo",json,function (data) {
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
