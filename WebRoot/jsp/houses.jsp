<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!doctype html>
<head>
<title>租赁系统</title>
<script src="js/head.js"></script>
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
<body style="overflow: hidden">
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
			<a class="navbar-brand" onclick="javascript:window.open('upLoadHouseInfo?viewType=1')"  id="upLoadHouseInfo" style="float: right;margin-right: 40px">
				上传房源
			</a>
		</div>
		<div >
			<a class="navbar-brand" onclick="change('./addGroup')"   style="float: right;margin-right: 40px">
				合租组团
			</a>
		</div>
		<div >
			<a class="navbar-brand" onclick="change('./administratorsMain')"   style="float: right;margin-right: 40px" id="admin">
				系统管理员
			</a>
		</div>
    </div>
</nav>
<div style="height:100%">
	<iframe src="./housesShow" width="100%" height="100%" allowTransparency="true" id="bottonFrame"></iframe>
</div>
<script>
	//查询条件和分页的参数
     var userName =  "";
     var messageNum = 0;
     var userId = "";
     var userType ;
	 $(document).ready(function(){
		 $(window).scroll(function () {
			 if ($(".navbar").offset().top > 50) {$(".navbar-fixed-top").addClass("top-nav");
			 }else {$(".navbar-fixed-top").removeClass("top-nav");}
		 });
		 userId =  '${data.userId}';
		 userName =  '${data.userName}';
		 messageNum = '${data.messageNum}';
		 userType = '${data.userType}';

		 debugger;
		 //判断用户是否已经登录，如果登录
		 if(userName != "" && messageNum !=""){
			 changeUserIcon(true,userName);
		 }else{
			 changeUserIcon(false,userName);
		 }
		 if(userName != ""){
			 $("#userHref").qtip(showUserMessage());
		 }
		 if(userType != "admin"){
             $("#admin").hide();
         }
	 });
	 function change(address){
		 $("#bottonFrame",parent.document.body).attr("src",address);
	 }
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
		changeUserIcon(false);
		//要删除session里面的数据
        doPostAjax("login_clearSession",{},function (data) {
            if(data.result =="Y"){
                location.href="./houses";
            }
        });

	}

</script>
        
</body>
</html>