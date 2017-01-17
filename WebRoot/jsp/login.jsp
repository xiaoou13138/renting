<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
    <head> 
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link rel="stylesheet" href="css/rentingStyle.css">

		<title>登录</title>
	</head>
	<body>
		<div class="container">
			<div class="row main">
				<div class="main-login main-center">
					<form class="" method="post" action="#">
					
						<div class="form-group">
							<label for="email" class="cols-sm-2 control-label">账户:</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
									<input type="text" class="form-control" name="email" id="email"  placeholder="请输入邮箱"/>
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label for="password" class="cols-sm-2 control-label">密码:</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
									<input type="password" class="form-control" name="password" id="password"  placeholder="请输入密码"/>
								</div>
							</div>
						</div>
						<div style="float:left;padding-left:10%;">
							<a src="" style="float: right;margin-top:3px;margin-left:2px;text-decoration:none;" class="btn-link">
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
						<div class="form-group ">
							<input type="submit" value="确定" class="btn btn-primary btn-lg btn-block login-button">
						</div>
						
					</form>
				</div>
			</div>
		</div>

    <script src="js/bootstrap.min.js"></script>
	</body>
</html>