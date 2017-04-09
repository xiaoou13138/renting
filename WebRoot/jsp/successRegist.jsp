<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    <title>注册成功</title>
    <script src="js/head.js"></script>
    <script src="1.js?ver=1"></script>
	<link rel="stylesheet" href="css/rentingStyle.css">
	<link rel="stylesheet" href="css/loginAndRegist.css">
    <style>
	.div-left{margin:3 3 3 10;width:20%;height:200px;border:1px solid #F00;float:left  ;padding:20 20 20 20}
	.div-right{margin:3 3 3 10;width:75%;height:200px;border:1px solid #F00;float:left;text-align:left}
	.clear{clear:both}
	.sucess-font{color:green;font-weight:bold;}
    </style>
    

  </head>
  
  <body>
    	<div class="container">
    		<div class="row main" style="border:1px solid #F00">
    			<div class="div-left">
    				<div class="success-image"></div>
    			</div>
    			<div class="div-right">
    			<div class="row main" style="padding-left:80px" >
    			<font class="sucess-font" id="content"></font></div>
    			</div>
    			<div class="clear"></div>
    		</div>
    	</div>
  </body>
  <script type="text/javascript">
  	jQuery(function() {
			var content = $("#content");
			//var text = "恭喜你，您的账号"+getParam("code")+"已经注册成功";
			var text = "恭喜你，您的账号已经注册成功</br>三秒后将转到登录页面";
			content.html(text);
			delayURL("./login",3000);
		});	
  </script>
</html>
