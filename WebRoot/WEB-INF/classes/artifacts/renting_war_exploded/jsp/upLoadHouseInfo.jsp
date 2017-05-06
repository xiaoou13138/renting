<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	int viewType = Integer.parseInt(request.getParameter("viewType").toString());
	long time = System.currentTimeMillis();
%>
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
					  <div class="form-group form-radio" >
						  <label  class="col-sm-2 control-label">出租方式:</label>
						  <div class="col-sm-2 " >
							  <label class="radio-inline">
								  <input type="radio" name="rentingType"  value="1" checked="checked"> 整租
							  </label>
							  <label class="radio-inline">
								  <input type="radio" name="rentingType"  value="2"> 合租
							  </label>
						  </div>
					  </div>

					  <div class="form-group has-check form-text">
						  <label for="houseName" class="col-sm-2 control-label">房屋名称(标题):</label>
						  <div class="col-sm-4 ">
							  <input type="text" class="form-control " id="houseName" hasCheck="true">
						  </div>
					  </div>

					  <div class="form-group has-check form-select">
						  <label for="houseType" class="col-sm-2 control-label">住宅类型:</label>
						  <div class="col-sm-4">
							  <select class="form-control" id ="houseType">
								  <option>公寓</option>
							  </select>
						  </div>
					  </div>

					  <div class="form-group has-check form-text">
						  <label for="cityChoice" class="col-sm-2 control-label">住宅地址:</label>
						  <div class="col-sm-2">
							  <div class="input-group ">
								  <div class="input-group-addon">城市</div>
								  <input type="text" id="cityChoice"  class="form-control not-save">
								  <input type="hidden" id="province"  value="">
								  <input type="hidden" id="city"  value="">
							  </div>
						  </div>
						  <div class="col-sm-4">
							  <div class="input-group">
								  <div class="input-group-addon">具体位置</div>
								  <input type="text" id="detailAddress"  class="form-control">
							  </div>
						  </div>
					  </div>

					  <div class="form-group has-check form-text">
						  <label for="houseArea" class="col-sm-2 control-label">房屋面积:</label>
						  <div class="col-sm-2">
							  <div class="input-group">
								  <input type="text" class="form-control" id="houseArea" hasCheck="true" onkeyup="validateNum(this)">
								  <div class="input-group-addon">平方米</div>
							  </div>
						  </div>
					  </div>

					  <div class="form-group has-check form-text">
						  <label for="money" class="col-sm-2 control-label">价格:</label>
						  <div class="col-sm-2">
							  <div class="input-group">
								  <input type="text" class="form-control" id="money" onkeyup="validateNum(this)">
								  <div class="input-group-addon">元/月</div>
							  </div>
						  </div>
					  </div>

					  <div class="form-group has-check form-text">
						  <label for="depositType" class="col-sm-2 control-label">押金方式:</label>
						  <div class="col-sm-4">
							  <input type="text" class="form-control" id="depositType" hasCheck="true">
							  </select>
						  </div>
					  </div>

					  <div class="form-group has-check form-text">
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

					  <div class="form-group form-checkbox">
						  <label for="depositType" class="col-sm-2 control-label">房屋设备情况:</label>
						  <div class="col-sm-6" id="facility">
							  <label class="checkbox-inline">
								  <input type="checkbox"  value="TV"> 电视
							  </label>
							  <label class="checkbox-inline">
								  <input type="checkbox"  value="conditioner"> 空调
							  </label>
							  <label class="checkbox-inline">
								  <input type="checkbox"  value="microwave"> 微波炉
							  </label>
							  <label class="checkbox-inline">
								  <input type="checkbox"  value="washer"> 洗衣机
							  </label>
							  <label class="checkbox-inline">
								  <input type="checkbox"  value="fridge"> 冰箱
							  </label>
						  </div>
					  </div>

					  <div class="form-group form-text">
						  <label for="information" class="col-sm-2 control-label">描述:</label>
						  <div class="col-sm-6">
							  <textarea class="form-control" rows="5" id = "information"></textarea>
						  </div>
					  </div>
					  <%if(viewType == 2){ %>
					  <div class="form-group form-text">
						  <label for="information" class="col-sm-2 control-label">图片:</label>
						  <div class="col-sm-4">
							  <button type="button" class="btn btn-primary btn-block" style="width: 200px; margin: auto;margin-bottom: 50px" onclick="showPictureList()">图片列表</button>
						  </div>
					  </div>
					  <% }%>
					  <div class="form-group">
						  <label for="depositType" class="col-sm-2 control-label">上传图片(主图片):</label>
						  <div class="col-sm-8" style="height: 350px;">
							  <iframe src="./uploader?pictureType=1&time='<%=time%>'" width="100%" height="100%" allowTransparency="true" style="border: hidden;"></iframe>
						  </div>
					  </div>

					  <div class="form-group">
						  <label for="depositType" class="col-sm-2 control-label">上传图片(其他普通图片):</label>
						  <div class="col-sm-8" style="height: 350px;">
							  <iframe src="./uploader?pictureType=2&time='<%=time%>'" width="100%" height="100%" allowTransparency="true" style="border: hidden;"></iframe>
						  </div>
					  </div>

					  <button type="button" class="btn btn-primary btn-lg btn-block" style="width: 200px; margin: auto;margin-bottom: 50px" onclick="saveInfo()">提交</button>
				  </form>
			  </div>
		  </div>
	  </div>
  </div>

  <script>
	  var houseId ;
	  var nowtime;
	  var viewType = getParam("viewType");
      $(document).ready(function () {
          nowtime = getCurrentTimeMillis();
          upLoadHouseInfoViewInit();
          if(viewType == 1){//新增

		  }else if(viewType ==2){//修改
              setViewData(${data});
              $("#cityChoice").val(${data}["city"]);
              houseId = getParam("houseId");
              getTableData(0,10,true);
		  }

      });


	 function saveInfo() {
         $("#address").val($("#province").val()+$("#city").val());
         if(!validForm()){
             return;
         }
		 var json = getViewData();
         json.viewType =viewType;
         json.time = nowtime;
         json.houseId = houseId;
         debugger;
		 beginLoad("保存成功","保存失败",5000,function () {
			 window.close();
         });
		 doPostAjaxAndLoad("upLoadHouseInfo_upLoad",json,function (data) {

         });
     }

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
      function showPictureList() {
          openIFrame("图片列表","./HousePictureManage?houseId="+houseId);
      }
  </script>
  </body>
</html>
