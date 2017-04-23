<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
    <head> 
		<script src="js/head.js"></script>
		<title>登录</title>
	</head>
	<body>
		<div class="container " style="background-color: #F0FFFF">
			<div class="center-block" style="width: 600px;margin: 200px auto">
				<form class="form-horizontal">
					<div class="form-group">
						<label for="code" class="col-sm-2 control-label">账号:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="code" placeholder="请输入邮箱">
						</div>
					</div>
					<div class="form-group">
						<label for="password" class="col-sm-2 control-label">密码:</label>
						<div class="col-sm-10">
							<input type="password" class="form-control" id="password" placeholder="密码">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<div class="checkbox">
								<label>
									<input type="checkbox" onclick="savePaw();" id="saveid"> 记住密码
								</label>
							</div>
						</div>
					</div>
					<div class="form-group center-block" style="margin-top: 80px">
						<p class="col-sm-6">
							<button type="button" class="btn btn-primary btn-lg btn-block" onclick="register()">注册</button>
						</p>
						<p class="col-sm-6">
							<button type="button" class="btn btn-primary btn-lg btn-block" onclick="checkPassword();">登录</button>
						</p>
					</div>
				</form>
			</div>
		</div>
    <script type="text/javascript">
    	//后台校验用户名密码
		var codeObj = $("#code");
		var passwordObj = $("#password");
		function checkPassword(){
			var codeVal = codeObj.val();
			var passwordVal = passwordObj.val();
			codeObj.qtip('destroy',true);
			passwordObj.qtip('destroy',true);
			//校验账号和密码是否为空
			if(codeVal == ""){
				codeObj.qtip(getRentingTips('账号不能为空'));
				codeObj.focus();
				return false;
			}
			
			if(!checkMail(codeVal)){
				codeObj.qtip(getRentingTips('账号不是合法的邮箱'));
				return false;
			}
			if(passwordVal == ""){
				passwordObj.qtip(getRentingTips('密码不能为空'));
				passwordObj.focus();
				return false;
			}
			//提交用户数据
			doPostAjax("login_login",{"code":codeVal,"password":passwordVal},function (rtnData) {
                if("Y" == rtnData.result){
                    //saveCookie();
                    location.href = "./houses";
                }else{
                    codeObj.qtip(getRentingTips('账号或者密码错误'));
                    codeObj.focus();
                }
            })

		}
		jQuery(function() {
			var codeValC = $.cookie('codeVal');
			var passwordValC = $.cookie('passwordVal');
			if (typeof(codeValC) != "undefined"
					&& typeof(passwordValC) != "undefined") {
				codeObj.val(codeValC);
				passwordObj.val(passwordValC);
				$("#saveid").attr("checked", true);
			}
		});	
		
		function register(){
			location.href = "./regist";
		}
	</script>
	</body>
</html>