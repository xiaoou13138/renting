<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<% String choiceType=request.getParameter("choiceType"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="js/head.js"></script>
<link rel="stylesheet" href="css/rentingStyle.css">
<link rel="stylesheet" href="css/loginAndRegist.css">
<title>注册</title>
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
									<span class="input-group-addon"><i class="fa fa-login fa" aria-hidden="true"></i></span>
									<input onblur="checkTrueMail()" id="code" type="text" class="form-control" name="email" id="email"  placeholder="请输入邮箱"/>
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
							
							<label for="passwordConfirm" class="cols-sm-2 control-label">密码确认:</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
									<input onblur="checkPassword()" id="passwordConfirm" type="password" class="form-control" name="password" id="password"  placeholder="请确认密码"/>
								</div>
							</div>
						
						</div>
						
						<div class="form-group">
							<label for="telphone" class="cols-sm-2 control-label">手机号码:</label>
								<div class="cols-sm-10">
									<div class="input-group">
										<span class="input-group-addon"><i class="fa fa-phone fa-lg" aria-hidden="true"></i></span>
										<input onblur="checkTelphone()" id="telphone" type=text class="form-control" name="telphone"   placeholder="请输入手机号码"/>
									</div>
							</div>
						</div>
						
						<div class="form-group">
							<label for="name" class="cols-sm-2 control-label">姓名:</label>
								<div class="cols-sm-10">
									<div class="input-group">
										<span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
										<input onblur="checkName()" id="name" type="text" class="form-control" name="name"  placeholder="请输入姓名"/>
									</div>
							</div>
						</div>
						
						<div class="form-group">
							<label for="password" class="cols-sm-2 control-label" style="float:left;padding-right:10px">性别:</label>
								<div class="cols-sm-10">
									<div class="input-group">
										<div class="btn-group">
										  <button id='sexButton'  type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
										  style="width:80px">
										     性别<span class="caret"></span>
										  </button>
										  <ul id='myDropdown' class="dropdown-menu">
										    <li><a href="#">男</a></li>
										    <li><a href="#">女</a></li>
										  </ul>
										</div>
									</div>
							</div>
						</div>
						
						<div class="form-group">
							<a type="button" id="button" class="btn btn-primary btn-lg btn-block login-button" onclick="register();">注册</a>
						</div>
					</form>
				</div>
			</div>
		</div>
		<script type="text/javascript">
		
		var codeObj = $("#code");//账号的输入框对象
		var passwordObj = $("#password");//密码输入框对象
		var passwordConfirmObj = $("#passwordConfirm");//密码确认输入框对象
		var telphoneObj = $("#telphone");
		var nameObj = $("#name");
		var sexObj = $("#sexButton");
		jQuery(function() {
			$('#myDropdown li').on('click', function(){
				$('#sexButton').text($(this).text());
				$('#sexButton').val($(this).text());
				$('#sexButton').qtip('destroy',true);
			}	);
		});
		//校验手机号码
		function checkTelphone(){
		debugger;
			var telphoneVal = telphoneObj.val();
			telphoneObj.qtip('destroy',true);
		  	if(telphoneVal == ""){
		  		telphoneObj.qtip(getRentingTips('手机号码不能为空'));
		  		telphoneObj.focus();
		  		return;
		  	}
		  	if(!checkPhone(telphoneVal)){
		  		telphoneObj.qtip(getRentingTips('手机号码不正确'));
		  		telphoneObj.focus();
		  		return;
		  	}
		}
		//校验邮箱
		function checkTrueMail(){
			codeObj.qtip('destroy',true);
			var codeVal = codeObj.val();//账号的值
		 	if(codeVal == ""){
		 		codeObj.qtip(getRentingTips('邮箱不能为空'));
				codeObj.focus();
	 			return;
		 	}
		 	if(!checkMail(codeVal)){
		 		codeObj.qtip(getRentingTips('邮箱格式不正确'));
				codeObj.focus();
				return;
		 	}
		}
		//校验密码
		function checkPassword(){
			var passwordVal = passwordObj.val();//密码的值
			var passwordConfirmVal = passwordConfirmObj.val();//确认密码的值
			passwordObj.qtip('destroy',true);
			passwordConfirmObj.qtip('destroy',true);
			if(passwordVal == ""){
		 		passwordObj.qtip(getRentingTips('密码不能为空'));
		 		passwordObj.focus();
		 		return;
		 	}
		 	if(passwordConfirmVal == ""){
		 		passwordConfirmObj.qtip(getRentingTips('密码不能为空'));
		 		passwordConfirmObj.focus();
		 		return;
		 	}
		  	if(passwordVal != passwordConfirmVal){
		  		passwordConfirmObj.qtip(getRentingTips('两次密码不相同'));
		  		passwordConfirmObj.focus();
		  		return;
		  	}
		}
		//校验名字
		function checkName(){
			var nameVal = nameObj.val();
			nameObj.qtip('destroy',true);
			if(nameVal == ""){
		  		nameObj.qtip(getRentingTips('姓名不能为空'));
		  		nameObj.focus();
		  		return;
		  	}
		}
		function register(){
			var codeVal = codeObj.val();//账号的值
			var passwordVal = passwordObj.val();//密码的值
			var passwordConfirmVal = passwordConfirmObj.val();//确认密码的值
			var telphoneVal = telphoneObj.val();
			var nameVal = nameObj.val();
			var sexVal = sexObj.val();
			var userType = getParam("choiceType");
		  	
		  	sexObj.qtip('destroy',true);
		  	
		  	if(sexVal == ""){
			  	sexObj.qtip(getRentingTips('性别不能为空'));
			  	sexObj.focus();
			  	return;
		  	}
		 	  var json = {
		 	  				"code":codeVal,
		 	  				"password":passwordVal,
		 	  				"time":new Date().getTime(),
		 	  				"telPhone":telphoneVal,
		 	  				"name":nameVal,
		 	  				"sex":sexVal,
		 	  				"userType":userType
		 	  };
			  //调用ajax保存用户的信息
			  $.ajax({
					type: "POST",
					url: 'register_register',
			    	data: {USERINFO:JSON.stringify(json)},
					dataType:'json',
					cache: false,
					success: function(data){
						if("success" == data.result){
						//跳转到注册成功的页面
							window.location.href="successRegister?code="+codeVal;
						}else if("codeError" == data.result){
						//
							$("#code").qtip(getRentingTips('账号已经被人使用'));
							$("#code").focus();
						}
					}
				});
		}
		
		
		</script>
</body>
</html>