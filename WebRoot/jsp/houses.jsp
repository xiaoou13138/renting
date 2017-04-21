<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>租赁系统</title>
<script src="js/head.js"></script>
<script src="http://api.map.baidu.com/api?v=2.0&ak=09BOANftFs7uVaipXxFGsRF2txzTOiT9" type="text/javascript"></script>
</head>
	<style type="text/css">
        html, body {width:100%;height:100%;} /*非常重要的样式让背景图片100%适应整个屏幕*/  
        .my-navbar {padding:10px 0;background-color: white}
        .my-navbar a{background:transparent !important;color: #000000 !important}
        .my-navbar a:hover {color:#45bcf9 !important;background:transparent;outline:0}  
        .my-navbar a {transition: color 0.5s ease-in-out;}
        .top-nav {padding:0;background:#000;}  
        button.navbar-toggle {background-color:#fbfbfb;}/*整个背景都是transparent透明的，会看不到，所以再次覆盖一下*/  
        button.navbar-toggle > span.icon-bar {background-color:#dedede}
    </style>
<body>
<nav class="navbar navbar-fixed-top my-navbar bottom-shadow" role="navigation" style="background-color:#87CEEB">
    <div class="container-fluid" style="background-color:#87CEEB">
		<div >
			<a class="navbar-brand" onclick="change('./housesShow')"  style="float: left;margin-right: 40px">
				主页
			</a>
		</div>
        <div >
            <a class="navbar-brand" href="#"  id="userHref" style="float: right;margin-right: 40px">
                <div class="fa" id="hrefContent"></div>
            </a>
        </div>
		<div >
			<a class="navbar-brand" onclick="change('./postBar')"  style="float: right;margin-right: 40px">
				贴吧
			</a>
		</div>
		<div >
			<a class="navbar-brand" onclick="change('./upLoadHouseInfo')"  id="upLoadHouseInfo" style="float: right;margin-right: 40px">
				上传房源
			</a>
		</div>
		<div >
			<a class="navbar-brand" onclick="change('./addGroup')"   style="float: right;margin-right: 40px">
				合租组团
			</a>
		</div>
    </div>
</nav>
<div style="height:100%">
	<iframe src="./housesShow" width="100%" height="100%" allowTransparency="true" id="bottonFrame"></iframe>
</div>
<script>
	//查询条件和分页的参数
	 $(document).ready(function(){
		 $(window).scroll(function () {
			 if ($(".navbar").offset().top > 50) {$(".navbar-fixed-top").addClass("top-nav");
			 }else {$(".navbar-fixed-top").removeClass("top-nav");}
		 })
	 });
	 function change(address){
         $("#bottonFrame",parent.document.body).attr("src",address);
	 }
    var userName =  "";
    var messageNum = 0;
    var userId = "";
    jQuery(function() {
        userId =  '${userId}';
        userName =  '${userName}';
        messageNum = '${messageNum}';
        messageNum =2;
        //判断用户是否已经登录，如果登录
        if(userName != "" && messageNum !=""){
            changeUserIcon(true,userName);
        }else{
            changeUserIcon(false,userName);
        }
        if(userName != ""){
            $("#userHref").qtip(showUserMessage());
        }

    });
    //根据是否有用户改变样式
    function changeUserIcon(hasUserInfo,userName){
        if(hasUserInfo == true){
            $("#userHref").click(function () {
                change("./management");
            });
            $("#hrefContent").addClass("userCond");
            $("#hrefContent").text("个人信息管理");
        }else{
            $("#userHref").attr("href","http://localhost:8080/renting/login");
            $("#userHref").click(function () {
            });
            $("#hrefContent").text("注册/登录");
            $("#hrefContent").removeClass("userCond");
        }
    }
    function showUserMessage(){
        var tips = {
            content: {
                text:"" +
                "<div style=\"width: 200px;\"><span style=\"float: left;\">" +
                userName+
                "     您有"+
                messageNum+
                "条私信" +
                "</span><span style=\"float: right;\"><a onclick=\"delUserInfoCookie()\">退出</a></span></div>"
            },
            show: {
                effect: function() {
                    $(this).fadeTo(500, 1);
                }
            },
            hide: {
                fixed: true,
                delay: 300
            },
            position: {
                my: 'top center',
                at: 'bottom center'
            },
            style: {
                classes: 'qtip-youtube'
            }
        };
        return tips;
    }
    function delUserInfoCookie(){
        $.cookie('codeVal', '', {
            expires : -1
        });
        $.cookie('passwordVal', '', {
            expires : -1
        });
        changeUserIcon(false)
    }

</script>
        
</body>
</html>