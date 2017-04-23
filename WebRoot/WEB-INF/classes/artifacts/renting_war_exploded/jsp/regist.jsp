<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="js/head.js"></script>
<title>注册</title>
</head>
<body>
<div class="container" style="background-color: #F0FFFF">
	<div class="center-block" style="width: 500px;margin: 40px auto">
		<form class="form-horizontal">
			<div class="form-group" style="text-align: center;font-size: 40px;">
				<label  class="col-sm-6 control-label">注册</label>
			</div>

			<div class="form-group has-check">
				<label for="code" class="col-sm-3 control-label">账号:</label>
				<div class="col-sm-8">
					<input onblur="checkTrueMail()" type="text" class="form-control" id="code"  placeholder="请输入邮箱">
				</div>
			</div>

			<div class="form-group has-check">
				<label for="userName" class="col-sm-3 control-label">账户名称:</label>
				<div class="col-sm-8">
					<input  type="text" class="form-control" id="userName"  placeholder="请输入账户名称">
				</div>
			</div>

			<div class="form-group has-check">
				<label for="password" class="col-sm-3 control-label">密码:</label>
				<div class="col-sm-8">
					<input type="password" class="form-control" id="password"  placeholder="请输入密码">
				</div>
			</div>

			<div class="form-group has-check">
				<label for="passwordConfirm" class="col-sm-3 control-label">密码确认:</label>
				<div class="col-sm-8">
					<input  type="password" class="form-control" id="passwordConfirm"  placeholder="请输入确认">
				</div>
			</div>

			<div class="form-group has-check">
				<label for="telphone" class="col-sm-3 control-label">手机号码:</label>
				<div class="col-sm-8">
					<input onblur="checkTelphone()" type="text" class="form-control" id="telphone"  placeholder="请输入手机号码">
				</div>
			</div>

			<div class="form-group ">
				<label for="name" class="col-sm-3 control-label">真实姓名:</label>
				<div class="col-sm-8">
					<input  type="text" class="form-control" id="name"  placeholder="请输入姓名">
				</div>
			</div>

			<div class="form-group ">
				<label for="name" class="col-sm-3 control-label">年龄:</label>
				<div class="col-sm-8">
					<input onblur="validateNum(this)" type="text" class="form-control" id="age"  placeholder="请输入年龄">
				</div>
			</div>

			<div class="form-group has-check">
				<label for="sex" class="col-sm-3 control-label">性别:</label>
				<div class="col-sm-8">
					<select class="form-control" id ="sex">
						<option value="男">男</option>
						<option value="女">女</option>
						<option value="不公开">不公开</option>
					</select>
				</div>
			</div>
			<div class="form-group center-block" style="margin-top: 80px;margin-left: 190px">
				<p class="col-sm-6">
					<button type="button" class="btn btn-primary btn-lg btn-block" onclick="regist()">确认注册</button>
				</p>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
var canSave = false;
var codeObj = $("#code");//账号的输入框对象
var passwordObj = $("#password");//密码输入框对象
var passwordConfirmObj = $("#passwordConfirm");//密码确认输入框对象
var telphoneObj = $("#telphone");
var nameObj = $("#name");
var userNameObj = $("#userName");

//校验手机号码
function checkTelphone(){
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
//校验账号是否是邮箱,如果是邮箱就校验邮箱是否重复
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
	doPostAjax("regist_checkCode",{"code":codeVal},function (data) {
		if(data.result =="N"){
            codeObj.qtip(getRentingTips('账号已经存在'));
            codeObj.focus();
		}
    })
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

function regist(){
    checkTelphone();
    checkTrueMail();
    checkPassword();
	var codeVal = codeObj.val();//账号的值
	var passwordVal = passwordObj.val();//密码的值
	var telphoneVal = telphoneObj.val();
	var nameVal = nameObj.val();
	var sexVal = $("#sex").val();
	var userType = getParam("choiceType");
	var userName = userNameObj.val();
	var age =  $("#age").val();
	  var json = {
					"code":codeVal,
					"password":passwordVal,
					"time":new Date().getTime(),
					"telPhone":telphoneVal,
					"sex":sexVal,
					"userType":userType,
					"userName":userName,
	  };
	  if(age != ""){
          json.age =age;
	  }
	  if(nameVal !=""){
	      json.name=nameVal;
	  }
	  //调用ajax保存用户的信息
	doPostAjax("regist_regist",json,function (data) {
        if("Y" == data.result){
            //跳转到注册成功的页面
            layer.confirm('注册成功！', {
                btn: ['确定'] //按钮
            }, function(){
                location.href="./login";
            });
        }else {
            $("#code").qtip(getRentingTips(data.rtnMessage));
            $("#code").focus();
        }
    });
}


</script>
</body>
</html>