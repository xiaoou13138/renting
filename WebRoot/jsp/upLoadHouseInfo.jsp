<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <script src="1.js?ver=1"></script>
    <script src="js/head.js"></script>
	<link rel="stylesheet" href="css/rentingStyle.css">
	<link rel="stylesheet" href="css/loginAndRegist.css">
	<link rel="stylesheet" href="css/webuploader.css">
    <link rel="stylesheet" href="css/upload.css">
	<script src="js/webuploader.js"></script>
	<script src="js/upload.js"></script>
    <title>登记房屋信息</title>
    <style>
    label {
	  display: inline-block;
	  max-width: 100%;
	  margin-bottom: 5px;
	  font-weight:normal !important; 
	  font-size:20px; !important; 
	  height:100%;
	  line-height:50px;
	}
	.float-left{
		float:left;
	}
	.float-right{
		float:right;
	}
	.star{
	color:red;
	font-size:30px;
	height:100%;
	line-height:60px;
	
	
 	}
 	.upLoadRow{
		height:100px;
		margin:10 10 10 10;
	}
	.upLoadLeft{
		width:20%;
		height:50px;
		margin:25 0 25 10;
		text-align:right;
	}
	.upLoadRight{
		width:70%;
		height:50px;
		margin:25 10 25 0;
		text-align:left;
	}
	.headFont{
		font-size:20px;
		font-weight:normal !important; 
		height:100%;
		line-height:50px;
		padding-left:10px;
		padding-right:20px;
	}
	.checkboxDiv{
		float:left;
		width:100px;
		margin:5 0 5 5;
	}
    </style>
    <script src="js/head.js"></script>
    <script src="js/fileinput.js"></script>
    <link rel="stylesheet" href="css/fileinput.css">
  </head>
  
  <body>
  <div class="container boder">
  	<div class="main row boder">
  		<h1>房屋基本信息</h1>
  		<div class="row main">
  			<div class="upLoadRow boder">
  				<div class="upLoadLeft float-left boder">
	  				<div class="headFont float-right">出租方式:</div>
	  				<div class="star float-right">*</div>
  				</div>
  				<div class="upLoadRight float-left boder">
  					<input type="radio" name="optionsRadios" id="rentingTypeRadio1" value="" />
  					<label style="margin-right:15px">整租</label>
  					<input type="radio" name="optionsRadios" id="rentingTypeRadio2" value=""/>
  					<label>合租</label>
  				</div>
  			</div>
  			
  			<div class="upLoadRow boder">
  				<div class="upLoadLeft float-left boder">
	  				<div class="headFont float-right">您的身份:</div>
	  				<div class="star float-right">*</div>
  				</div>
  				<div class="upLoadRight float-left boder">
  					<input type="radio" name="optionsRadios1" id="rentingTypeRadio1" value="" />
  					<label style="margin-right:15px">个人</label>
  					<input type="radio" name="optionsRadios1" id="rentingTypeRadio2" value=""/>
  					<label>代理</label>
  				</div>
  			</div>
  			
  			<div class="upLoadRow boder">
  				<div class="upLoadLeft float-left boder">
	  				<div class="headFont float-right">小区名称:</div>
	  				<div class="star float-right">*</div>
  				</div>
  				<div class="upLoadRight float-left boder">
  					<input type="text" class="myform-control" style="height:80%;margin:5 0 5 0" id="rentingTypeRadio1" value="" />
  				</div>
  			</div>
  			
  			<div class="upLoadRow boder">
  				<div class="upLoadLeft float-left boder">
	  				<div class="headFont float-right">详细地址:</div>
	  				<div class="star float-right">*</div>
  				</div>
  				<div class="upLoadRight float-left boder">
  					<input type="text" class="myform-control" style="height:80%;margin:5 0 5 0" id="rentingTypeRadio1" value="" />
  				</div>
  			</div>
  			
  			<div class="upLoadRow boder">
  				<div class="upLoadLeft float-left boder">
	  				<div class="headFont float-right">房屋类型:</div>
	  				<div class="star float-right">*</div>
  				</div>
  				<div class="upLoadRight float-left boder">
					<div class="input-group" style="margin:6 20 6 0;width:110px;float:left">
						<input type="text" class="form-control" style="width:80px;" aria-label="Amount (to the nearest dollar)">
						<span class="input-group-addon" style="width:20px;height:100% ">室</span>
					</div>
					<div class="input-group" style="margin:6 20 6 0;width:110px;float:left">
						<input type="text" class="form-control" style="width:80px;" aria-label="Amount (to the nearest dollar)">
					  	<span class="input-group-addon" style="width:20px;height:100%">厅</span>
					</div>
					<div class="input-group" style="margin:6 20 6 0;width:110px;float:left">
						<input type="text" class="form-control" style="width:80px;" aria-label="Amount (to the nearest dollar)">
					  	<span class="input-group-addon" style="width:20px;height:100%">卫</span>
					</div>
  				</div>
  			</div>
  			
  			<div class="upLoadRow boder">
  				<div class="upLoadLeft float-left boder">
	  				<div class="headFont float-right">房屋面积:</div>
	  				<div class="star float-right">*</div>
  				</div>
  				<div class="upLoadRight float-left boder">
  					<div class="input-group" style="margin:6 20 6 0;width:110px;float:left">
						<input type="text" class="form-control" style="width:80px;" aria-label="Amount (to the nearest dollar)">
						<span class="input-group-addon" style="width:20px;height:100% ">平米</span>
					</div>
  				</div>
  			</div>
  			
  			<div class="upLoadRow boder">
  				<div class="upLoadLeft float-left boder">
	  				<div class="headFont float-right">楼层分布:</div>
	  				<div class="star float-right">*</div>
  				</div>
  				<div class="upLoadRight float-left boder">
  					<div class="input-group" style="margin:6 20 6 0;width:110px;float:left">
  						<span class="input-group-addon" style="width:20px;height:100% ">第</span>
						<input type="text" class="form-control" style="width:80px;" aria-label="Amount (to the nearest dollar)">
						<span class="input-group-addon" style="width:20px;height:100% ">层</span>
					</div>
					<div class="input-group" style="margin:6 20 6 0;width:110px;float:left">
  						<span class="input-group-addon" style="width:20px;height:100% ">共</span>
						<input type="text" class="form-control" style="width:80px;" aria-label="Amount (to the nearest dollar)">
						<span class="input-group-addon" style="width:20px;height:100% ">层</span>
					</div>
  				</div>
  			</div>
  			
  			<div class="upLoadRow boder">
  				<div class="upLoadLeft float-left boder">
	  				<div class="headFont float-right">房屋情况:</div>
	  				<div class="star float-right">*</div>
  				</div>
  				<div class="upLoadRight float-left boder">
  					<div class="input-group" style="margin:6 20 6 0;width:110px;float:left">
  						<span class="input-group-addon" style="width:20px;height:100% ">朝向</span>
  						<select class="form-control" style="width:60px">
  						<option value="0">东</option>
  						<option value="1">南</option>
  						<option value="2">西</option>
  						<option value="4">北</option>
  						<option value="5">南北</option>
  						<option value="6">东西</option>
  						<option value="7">东南</option>
  						<option value="8">西南</option>
  						<option value="9">东北</option>
  						<option value="10">西北</option>
  						</select>
					</div>
					
					<div class="input-group" style="margin:6 20 6 0;width:110px;float:left">
  						<span class="input-group-addon" style="width:20px;height:100% ">装修情况</span>
  						<select class="form-control" style="width:100px">
  						<option value="0">豪华装修</option>
  						<option value="1">精装修</option>
  						<option value="2">中等装修</option>
  						<option value="3">普通装修</option>
  						<option value="4">简单装修</option>
  						<option value="5">毛坯</option>
  						</select>
					</div>
					
					<div class="input-group" style="margin:6 20 6 0;width:110px;float:left">
  						<span class="input-group-addon" style="width:20px;height:100% ">住宅类型</span>
  						<select class="form-control" style="width:100px">
  						<option value="0">普通住宅</option>
  						<option value="1">公寓</option>
  						<option value="2">别墅</option>
  						<option value="3">平方</option>
  						<option value="4">四合院</option>
  						<option value="5">酒店公寓</option>
  						<option value="6">商住两用</option>
  						<option value="7">其他</option>
  						</select>
					</div>
  				</div>
  			</div>
  			
  			<div class="upLoadRow boder">
  				<div class="upLoadLeft float-left boder">
	  				<div class="headFont float-right">房屋配置:</div>
	  				<div class="star float-right">*</div>
  				</div>
  				<div class="upLoadRight float-left boder">
  				<div class="checkboxDiv">
  					<p>
	  					<input type="checkbox" name="optionsRadios"  value="" class="" id="allocation1"/>
	  					<label for="allocation1">电视</label>
	  				</p>
  				</div>
  				<div class="checkboxDiv">
  					<p>
	  					<input type="checkbox" name="optionsRadios"  value="" class="" id="allocation2"/>
	  					<label for="allocation2">空调</label>
	  				</p>
  				</div>
	  				
  				</div>
  			</div>
  			<div class="upLoadRow boder">
  				<div class="upLoadLeft float-left boder">
	  				<div class="headFont float-right">租金要求:</div>
	  				<div class="star float-right">*</div>
  				</div>
  				<div class="upLoadRight float-left boder">
  					<div class="input-group" style="margin:6 20 6 0;width:110px;float:left">
						<input type="text" class="form-control" style="width:80px;" aria-label="Amount (to the nearest dollar)">
						<span class="input-group-addon" style="width:20px;height:100% ">元/月</span>
					</div>
					<div class="input-group" style="margin:6 20 6 0;width:110px;float:left">
  						<span class="input-group-addon" style="width:20px;height:100% ">押金要求</span>
  						<select class="form-control" style="width:100px">
  						<option value="0">押一付三</option>
  						<option value="1">公寓</option>
  						<option value="2">别墅</option>
  						<option value="3">平方</option>
  						<option value="4">四合院</option>
  						<option value="5">酒店公寓</option>
  						<option value="6">商住两用</option>
  						<option value="7">其他</option>
  						</select>
					</div>
  				</div>
  			</div>
  		
  		</div>
  		<h1>房源描述及上传图片</h1>
  		<div class="upLoadRow boder">
  				<div class="upLoadLeft float-left boder">
	  				<div class="headFont float-right">房源标题:</div>
	  				<div class="star float-right">*</div>
  				</div>
  				<div class="upLoadRight float-left boder">
	  				<input type="text" class="myform-control" style="height:80%;margin:5 0 5 0" id="rentingTypeRadio1" value="" />
  				</div>
		</div>
		<div class="upLoadRow boder" style="height:220px">
  				<div class="upLoadLeft float-left boder">
	  				<div class="headFont float-right">房源描述:</div>
	  				<div class="star float-right">*</div>
  				</div>
  				<div class="upLoadRight float-left boder">
  					<textarea rows="8" cols="" class="form-control"></textarea>
  				</div>
		</div>
		<div class="upLoadRow boder" style="height:500px">
  				<div class="upLoadLeft float-left boder">
	  				<div class="headFont float-right">房源照片:</div>
	  				<div class="star float-right">*</div>
  				</div>
  				<div class="upLoadRight float-left boder">
  					<div id="wrapper">
				        <div id="container">
				            <!--头部，相册选择和格式选择-->
				            <div id="uploader">
				                <div class="queueList">
				                    <div id="dndArea" class="placeholder">
				                        <div id="filePicker"></div>
				                        <p>或将照片拖到这里，单次最多可选300张</p>
				                    </div>
				                </div>
				                <div class="statusBar" style="display:none;">
				                    <div class="progress">
				                        <span class="text">0%</span>
				                        <span class="percentage"></span>
				                    </div><div class="info"></div>
				                    <div class="btns">
				                        <div id="filePicker2"></div><div class="uploadBtn">开始上传</div>
				                    </div>
				                </div>
				            </div>
				        </div>
   					 </div>
				</div>
		</div>
  	</div>
  </div>
  <script> 
  </script>
  </body>
</html>
