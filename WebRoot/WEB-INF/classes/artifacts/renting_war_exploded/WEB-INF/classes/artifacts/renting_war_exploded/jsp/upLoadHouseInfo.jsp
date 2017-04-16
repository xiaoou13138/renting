<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <script src="js/head.js"></script>
    <title>登记房屋信息</title>

  </head>
  
  <body>
  <div class="bg">
	  <div class="container" >
		  <div class="panel panel-primary" style="background-color: white">
			  <div class="panel-heading">
				  <h3 class="panel-title  text-left">上传房源</h3>
			  </div>
			  <div class="panel-body">
				  <form class="form-horizontal">
					  <div class="form-group has-check" >
						  <label  class="col-sm-2 control-label">出租方式:</label>
						  <div class="col-sm-2" id="rentingType">
							  <label class="radio-inline">
								  <input type="radio" name="inlineRadioOptions"  value="1"> 整租
							  </label>
							  <label class="radio-inline">
								  <input type="radio" name="inlineRadioOptions"  value="2"> 合租
							  </label>
						  </div>
					  </div>

					  <div class="form-group has-check">
						  <label for="houseName" class="col-sm-2 control-label">房屋名称(标题):</label>
						  <div class="col-sm-4">
							  <input type="text" class="form-control" id="houseName" hasCheck="true">
						  </div>
					  </div>

					  <div class="form-group has-check">
						  <label for="houseType" class="col-sm-2 control-label">住宅类型:</label>
						  <div class="col-sm-4">
							  <select class="form-control" id ="houseType">
								  <option>公寓</option>
							  </select>
						  </div>
					  </div>

					  <div class="form-group has-check">
						  <label for="cityChoice" class="col-sm-2 control-label">住宅地址:</label>
						  <div class="col-sm-2">
							  <div class="input-group">
								  <div class="input-group-addon">城市</div>
								  <input type="text" id="cityChoice"  class="form-control">
								  <input type="hidden" id="province" value="">
								  <input type="hidden" id="city" value="">
							  </div>
						  </div>
						  <div class="col-sm-2">
							  <div class="input-group">
								  <div class="input-group-addon">具体位置</div>
								  <input type="text" id="addressDeatail"  class="form-control">
							  </div>
						  </div>
					  </div>

					  <div class="form-group has-check">
						  <label for="houseArea" class="col-sm-2 control-label">房屋面积:</label>
						  <div class="col-sm-2">
							  <div class="input-group">
								  <input type="text" class="form-control" id="houseArea" hasCheck="true" onkeyup="validateNum(this)">
								  <div class="input-group-addon">平方米</div>
							  </div>
						  </div>
					  </div>

					  <div class="form-group has-check">
						  <label for="price" class="col-sm-2 control-label">价格:</label>
						  <div class="col-sm-2">
							  <div class="input-group">
								  <input type="text" class="form-control" id="price" onkeyup="validateNum(this)">
								  <div class="input-group-addon">元/月</div>
							  </div>
						  </div>
					  </div>

					  <div class="form-group has-check">
						  <label for="depositType" class="col-sm-2 control-label">押金方式:</label>
						  <div class="col-sm-4">
							  <input type="text" class="form-control" id="depositType" hasCheck="true">
							  </select>
						  </div>
					  </div>

					  <div class="form-group has-check">
						  <label for="houseArea" class="col-sm-2 control-label">房屋类型:</label>
						  <div class="col-sm-2">
							  <div class="input-group ">
								  <input type="text" class="form-control" id="room" onkeyup="validateNum(this)">
								  <div class="input-group-addon">室</div>
							  </div>
						  </div>
						  <div class="col-sm-2">
							  <div class="input-group ">
								  <input type="text" class="form-control" id="hall" onkeyup="validateNum(this)">
								  <div class="input-group-addon">厅</div>
							  </div>
						  </div>
						  <div class="col-sm-2">
							  <div class="input-group ">
								  <input type="text" class="form-control" id="toilet" onkeyup="validateNum(this)">
								  <div class="input-group-addon">卫</div>
							  </div>
						  </div>
					  </div>

					  <div class="form-group">
						  <label for="depositType" class="col-sm-2 control-label">房屋设备情况:</label>
						  <div class="col-sm-4" id="facility">
							  <label class="checkbox-inline">
								  <input type="checkbox" id="TV" value="TV"> 电视
							  </label>
							  <label class="checkbox-inline">
								  <input type="checkbox" id="conditioner" value="conditioner"> 空调
							  </label>
							  <label class="checkbox-inline">
								  <input type="checkbox" id="microwave" value="microwave"> 微波炉
							  </label>
							  <label class="checkbox-inline">
								  <input type="checkbox" id="washer" value="washer"> 洗衣机
							  </label>
							  <label class="checkbox-inline">
								  <input type="checkbox" id="fridge" value="fridge"> 冰箱
							  </label>
						  </div>
					  </div>

					  <div class="form-group">
						  <label for="information" class="col-sm-2 control-label">描述:</label>
						  <div class="col-sm-6">
							  <textarea class="form-control" rows="5" id = "information"></textarea>
						  </div>
					  </div>

					  <div class="form-group has-check">
						  <label for="depositType" class="col-sm-2 control-label">上传图片(注意:u第一张图片是主要的展示图片):</label>
						  <div class="col-sm-8">
							  <div id="wrapper">
								  <div id="container">
									  <!--头部，相册选择和格式选择-->
									  <div id="uploader">
										  <div class="queueList">
											  <div id="dndArea" class="placeholder">
												  <div id="filePicker"></div>
												  <p>或将文件拖到这里，单次最多可选300份</p>
											  </div>
										  </div>
										  <div class="statusBar" style="display:none;">
											  <div class="progress">
												  <span class="text">0%</span>
												  <span class="percentage"></span>
											  </div><div class="info"></div>
											  <div  class="btns">
												  <div id="filePicker2"></div><div class="uploadBtn">开始上传</div>
											  </div>
										  </div>
									  </div>
								  </div>
							  </div>
						  </div>
					  </div>
					  <button type="button" class="btn btn-primary btn-lg btn-block" style="width: 200px; margin: auto;margin-bottom: 50px" onclick="saveInfo()">提交</button>
				  </form>
			  </div>
		  </div>
	  </div>
  </div>

  <script>
      var nowtime ;
      $(document).ready(function () {
          nowtime = getCurrentTimeMillis();
          uploderInit("uploader","dndArea","filePicker",{time:nowtime},"uploadImage");//初始化
          upLoadHouseInfoViewInit();
      });
	  function upLoadHouseInfoViewInit() {
          var cityPicker = new IIInsomniaCityPicker({
              data: cityData,
              target: '#cityChoice',
              valType: 'k-v',
              hideCityInput: '#city',
              hideProvinceInput: '#province',
              callback: function(city_id){
                 // alert(city_id);
              }
          });
          cityPicker.init();
      }
	 function saveInfo() {
         var rentingType = $('#rentingType input:radio:checked');
         var houseName = $('#houseName');
         var houseType = $('#houseType');
         var houseArea = $('#houseArea');
         var price = $("#price");
         var room = $("#room");
         var hall = $("#hall");
         var toilet = $("#toilet");
         var information = $("#information");
         var province = $("#province");
         var city = $("#city");
         var addressDeatail = $("#addressDeatail");
         var address = "";
         var depositType = $("#depositType");

         if(province.val() == undefined || city.val() == undefined || addressDeatail.val() == undefined ){
             layer.msg('位置必须要填写');
             addressDeatail.focus();
             return;
         }else{
             var cityVal = city.val();
             var provinceVal = province.val();
             address = provinceVal+cityVal+addressDeatail.val();
		 }

         if(rentingType.val() == undefined){
             layer.msg('出租方式必须要填写');
             $('#rentingType').focus();
             return;
		 }
		 if(houseName.val() == ""){
             layer.msg('房屋名称必须要填写');
             houseName.focus();
             return;
		 }
		 if(houseArea.val()==""){
             layer.msg('房屋面积必须要填写');
             houseArea.focus();
             return;
		 }
		 if(price.val()==""){
             layer.msg('价格必须要填写');
             price.focus();
             return;
		 }
         if(room.val()==""){
             layer.msg('室必须要填写');
             room.focus();
             return;
         }
         if(hall.val()==""){
             layer.msg('厅必须要填写');
             hall.focus();
             return;
         }
         if(toilet.val()==""){
             layer.msg('卫必须要填写');
             toilet.focus();
             return;
         }


         var json = {
             "rentingType":rentingType.val(),
             "houseName":houseName.val(),
             "houseType":houseType.val(),
             "houseArea":houseArea.val(),
             "price":price.val(),
             "room":room.val(),
             "hall":hall.val(),
             "toilet":toilet.val(),
			 "information":information.val(),
			 "address":address,
             "depositType":depositType.val(),
         };
		 var checkBoxObj = $('#facility input:checkbox:checked');
         var array = new Array;
         for(var i = 0;i<checkBoxObj.length;i++){
             array.push(checkBoxObj[i].value);
		 }
         json.facility=array;
         var load = layer.load(1, {
             shade: [0.1,'#fff'] //0.1透明度的白色背景
         });
         var notSuccess = true;
         doPostAjax("upLoadHouseInfo_upLoad",json,function (data) {
             if(data.result == "Y"){
                 layer.close(load);
                 layer.confirm('保存成功', {
                     btn: ['确定'] //按钮
                 });
                 notSuccess = false;
			 }else{
                 layer.close(load);layer.confirm('保存失败', {btn: ['确定'] });
			 }
         });
         setTimeout(function () {
             if(notSuccess){
                 layer.close(load);layer.confirm('保存失败', {btn: ['确定'] });
			 }
         }, 10000);
     }
  </script>
  </body>
</html>
