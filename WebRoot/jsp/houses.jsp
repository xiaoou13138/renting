<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>房间展示页面</title>
<script src="js/head.js"></script>
<link rel="stylesheet" href="css/rentingStyle.css">
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
            <div class="navbar-header">  
                <button type="button" class="navbar-toggle" data-toggle="collapse"  
                        data-target="#example-navbar-collapse">  
                    <span class="sr-only">切换导航</span>  
                    <span class="icon-bar"></span>  
                    <span class="icon-bar"></span>  
                    <span class="icon-bar"></span>  
                </button>  
                <a class="navbar-brand" href="#">菜鸟教程</a>  
            </div>  
            <div class="collapse navbar-collapse" id="example-navbar-collapse">  
                <ul class="nav navbar-nav">  
                    <li class="active"><a href="#">iOS</a></li>  
                    <li><a href="#">SVN</a></li>  
                    <li>  
                        <a href="#">Asp.Net</a>  
                    </li>  
                </ul>  
            </div>  
        </div>  
    </nav>
    <div class="bg">
    	<div class="grid-total" align="center">
    		<div class="grid-search-left"></div>
    		<div class="grid-search-center">
    			<div class="grid-left"><input type="text"  class="myform-control"></div>
    			<div class="grid-right"><a class="search-button btn-primary no-line ">搜索</a></div>
    		</div>
    		<div class="grid-search-right"></div>
    		<div style="clear:both"></div>
    		
    		<div class="grid-options"></div>
    		<div class="grid-sort"></div>
	    	<div id="products" class="products">
	    	</div>
    	</div>
    </div>
    <script>  
     $(document).ready(function(){
     debugger;
     	getProductViews(1,10);
     });
        $(window).scroll(function () {  
            if ($(".navbar").offset().top > 50) {$(".navbar-fixed-top").addClass("top-nav");  
            }else {$(".navbar-fixed-top").removeClass("top-nav");}  
        })
        function getProductViews(begin,end){
        	var json = {
        		"begin":begin,
        		"end":end
        	};
        	if(begin>0){
        		$.ajax({
					type: "POST",
					url: 'house_getProduct',
			    	data: {HOUSEINFO:JSON.stringify(json),time:new Date().getTime()},
					dataType:'json',
					cache: false,
					success: function(data){
					debugger;
						if("success" == data.result){
							$("#products").html(data.html);
							
						}else if("usererror" == data.result){
							$("#code").qtip(getRentingTips('图片错误'));
							$("#code").focus();
						}else{
							$("#password").qtip(getRentingTips('图片错误'));
							$("#password").focus();
						}
					}
				});
        	}
        }
        </script>
        
</body>
</html>