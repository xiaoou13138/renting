<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
    <head> 
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script src="js/head.js"></script>
		<link rel="stylesheet" href="css/rentingStyle.css">
		
		<title>登录</title>
	</head>
	<body>
		<div class="container" >
			<div class="row main">
				<div class="main-login main-center" id="loginBox">
					<form class="" method="post" action="#">
					
						<div class="form-group">
							<label for="email" class="cols-sm-2 control-label">账号:</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
									<input id="code" type="text" class="form-control" name="email" id="email"  placeholder="请输入邮箱"/>
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label for="password" class="cols-sm-2 control-label">密码:</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
									<input id="password" type="password" class="form-control" name="password" id="password"  placeholder="请输入密码"/>
								</div>
							</div>
							<div style="float:left;padding-left:10%;">
							<a onclick="register()">
								<font color="white">注册</font>
							</a>
						</div>
						<div style="float:right;padding-right:10%;">
							<div style="float: left;margin-top:3px;margin-right:2px;">
								<font color="white">记住密码</font>
							</div>
							<div style="float: left;">
								<input name="form-field-checkbox" id="saveid" type="checkbox"
									onclick="savePaw();" style="padding-top:0px;" />
							</div>
						</div>
						</div>
						<div class="form-group">
							<a type="button" id="button" class="btn btn-primary btn-lg btn-block login-button" onclick="checkPassword();">登录</a>
						</div>
					</form>
				</div>
			</div>
		</div>
    <script type="text/javascript">
    	//后台校验用户名密码
		function checkPassword(){
			if(check()){
				var code = $("#code").val();
				var password = $("#password").val();
				var json = {"code":code,"password":password};
				
				$.ajax({
					type: "POST",
					url: 'login_login',
			    	data: {USERINFO:JSON.stringify(json),time:new Date().getTime()},
					dataType:'json',
					cache: false,
					success: function(data){
						if("success" == data.result){
							saveCookie();
							location.href = "./houses";
						}else if("usererror" == data.result){
							$("#code").qtip(getRentingTips('账号错误'));
							$("#code").focus();
						}else{
							$("#password").qtip(getRentingTips('密码错误'));
							$("#password").focus();
						}
					}
				});
			}
		}
		//前台校验
		function check(){
			var code = $("#code").val();
			var password= $("#password").val();
			//校验账号和密码是否为空
			if(code == ""){
				$("#code").qtip(getRentingTips('账号不能为空'));
				$("#code").focus();
				return false;
			}else{
				$("#code").qtip('destroy',true);
				if(!checkMail(code)){
					return false;
				}
			}
			if(password == ""){
				$("#password").qtip(getRentingTips('密码不能为空'));
				$("#password").focus();
				return false;
			}else{
				$("#password").qtip('destroy', true);
			}
			//$("#loginBox").qtip(getRentingTips('正在登陆'));
			return true;
		}
		
		
		
		function register(){
			location.href = "./register";
		}

	</script>
	</body>
</html>