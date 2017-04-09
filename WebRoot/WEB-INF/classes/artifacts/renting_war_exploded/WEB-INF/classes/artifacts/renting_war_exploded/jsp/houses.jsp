<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>房间展示页面</title>
<script src="js/head.js"></script>
<script src="http://api.map.baidu.com/api?v=2.0&ak=09BOANftFs7uVaipXxFGsRF2txzTOiT9" type="text/javascript"></script>
</head>
<style type="text/css">  
        html, body {width:100%;height:100%;} /*非常重要的样式让背景图片100%适应整个屏幕*/  
        .bg {display: table;width: 100%;height: 100%;padding: 100px 0;text-align: center;color: #fff;background: url(http://www.xiandanke.cn/Image/intro-bg.jpg) no-repeat bottom center;background-color: #000;background-size: cover;}  
        .my-navbar {padding:20px 0;transition: background 0.5s ease-in-out, padding 0.5s ease-in-out;}  
        .my-navbar a{background:transparent !important;color:#fff !important}  
        .my-navbar a:hover {color:#45bcf9 !important;background:transparent;outline:0}  
        .my-navbar a {transition: color 0.5s ease-in-out;}/*-webkit-transition ;-moz-transition*/  
        .top-nav {padding:0;background:#000;}  
        button.navbar-toggle {background-color:#fbfbfb;}/*整个背景都是transparent透明的，会看不到，所以再次覆盖一下*/  
        button.navbar-toggle > span.icon-bar {background-color:#dedede}
    </style>
<body>
<nav class="navbar navbar-fixed-top my-navbar" role="navigation">
    <div class="container-fluid">
        <div >
            <a class="navbar-brand" href="#"  id="userHref" style="float: right;margin-right: 150px">
                <div class="fa" id="hrefContent"></div>
            </a>
        </div>
		<div >
			<a class="navbar-brand" href="#"  id="tieba1" style="float: right;margin-right: 150px">
				贴吧
			</a>
		</div>
		<div >
			<a class="navbar-brand" href="./upLoadHouseInfo"  id="upLoadHouseInfo" style="float: right;margin-right: 150px">
				上传房源
			</a>
		</div>
    </div>
</nav>
    <div class="bg">
    	<div class="grid-total" align="center">
    		<div class="grid-search-left"></div>
    		<div class="grid-search-center">
    			<div class="grid-left"><input type="text"  class="myform-control" id="searchContent"></div>
    			<div class="grid-right"><a class="search-button btn-primary no-line " onclick="firstSearch()">搜索</a></div>
    		</div>
    		<div class="grid-search-right"></div>
    		<div style="clear:both"></div>
    		
    		<div class="grid-options">

			</div>
    		<div class="grid-sort"></div>
	    	<div id="products" class="products">

	    	</div>
			<div style="padding-left:100px;">
				<ul class="pagination"></ul>
			</div>
    	</div>
    </div>
    <script>
		//查询条件和分页的参数

		 $(document).ready(function(){
             firstSearch();
			 $(window).scroll(function () {
				 if ($(".navbar").offset().top > 50) {$(".navbar-fixed-top").addClass("top-nav");
				 }else {$(".navbar-fixed-top").removeClass("top-nav");}
			 })
		 });
		function firstSearch() {
            var json = {
                "begin":0,
                "end":10
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
                    setPage(data.count,function (begin,end) {
                        notFirstSearch(begin,end);
                    });
                }else{
                    alert("获取信息失败，请检查网络");
                }
            });
        }
		function notFirstSearch(begin,end){
            var json = {
                "begin":begin,
                "end":end
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
				}else{
					alert("获取信息失败，请检查网络");
				}
			});
		}
		function createHtmlByHouseInfo(info){
			debugger;
			var html = "<div class=\"house-info\">"
			+"<div class=\"house-picture-div pull-left\" style=\"background: url('./showImage?imageFile="
				+info.mainPicture
				+"'"
				+");background-repeat: no-repeat;background-position: center center;background-size:200px 200px;\"></div>"
				+"<div class=\"house-text-div pull-left\"><div><a href=\""
				+"./houseDetails?houseId="+info.houseId+"\""//这里是链接
				+"><p class=\"lead text-left \" style=\"font-size: 25px\">"
				+info.houseName
				+"</p></a></div>"
				+"<div>"
				+"<p class=\"lead text-left\" style=\"font-size: 20px\">"
				+info.houseType+"&nbsp;"+info.houseArea+"平方米"+"&nbsp;"+info.information
				+"</p></div><div><a href=\""
				+"#\""
				+"><p class=\"lead text-left\" style=\"font-size: 18px\">"
				+info.houseAddress
				+"</p></a></div>"
				+"</div><div class=\"money-div pull-left\">"
				+"<p  class=\"text-left\" style=\"font-size: 18px\">"
				+info.money
				+"元/月</p></div></div>";
			return html;
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
				 $("#userHref").attr("href","http://localhost:8080/renting/management")
				 $("#hrefContent").addClass("userCond");
				 $("#hrefContent").text("个人信息管理");
			 }else{
				 $("#userHref").attr("href","http://localhost:8080/renting/login");
				 $("#hrefContent").text("注册/登录");
				 $("#hrefContent").removeClass("userCond");
			 }

		 }
		 $(window).scroll(function () {
			 if ($(".navbar").offset().top > 50) {$(".navbar-fixed-top").addClass("top-nav");
			 }else {$(".navbar-fixed-top").removeClass("top-nav");}
		 })
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