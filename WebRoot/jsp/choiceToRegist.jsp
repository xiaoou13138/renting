<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>选择注册用户类型</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script src="js/head.js"></script>
	<link rel="stylesheet" href="css/rentingStyle.css">
  </head>
  <style type="text/css">
  </style>
  <body>
  	<div style="padding:400px 400px 100px 400px">
  		<div class="form-group" style="float:left;width:300px">
			<a type="button" id="button1" class="btn btn-primary btn-lg btn-block login-button" onclick="choice('normal');">普通用户注册</a>
		</div>
		<div class="form-group" style="width:300px;float:right">
			<a type="button" id="button2" class="btn btn-primary btn-lg btn-block login-button" onclick="choice('landlord');">房东用户注册</a>
		</div>
  	</div>
  	<script type="text/javascript">
  		function choice(choice){
  			location.href = "./register?choiceType="+choice;
  		}
  	</script>
  </body>
</html>
