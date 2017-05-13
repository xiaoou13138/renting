package com.ncu.controler;

import javax.annotation.Resource;

import com.ncu.util.APPUtil;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.infinispan.commons.hash.Hash;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ncu.data.ViewData;
import com.ncu.service.interfaces.IHouseSV;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
@Scope("prototype")
public class HousesController extends BaseController{
	public static Logger log = Logger.getLogger(LoginController.class);
	
	@Resource(name="HouseSVImpl")
	private IHouseSV houseSV;
	/**
	 * 用户请求网页
	 * @return
	 */
	@RequestMapping(value="/houses")
	public ModelAndView getView()throws Exception{
		ModelAndView mv = this.getModelAndView();
		JSONObject data = this.getRtnJSONObject();
		mv.setViewName("houses");
		mv.addObject("data",data);
		return mv;
	}
	
	@RequestMapping(value="/house_getProduct",produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object getProducts(){
		JSONObject rtnJSONObject = this.getRtnJSONObject();
		ViewData data = this.getViewData();
		try{
			JSONObject dataJSONObject = data.getJSONObject("DATA");
			String begin = APPUtil.getSafeStringFromJSONObject(dataJSONObject,"begin");
			String end = APPUtil.getSafeStringFromJSONObject(dataJSONObject,"end");
			long showType = dataJSONObject.getLong("showType");
			long userId = getLongParamFromSession("userId");
			if(showType == 1){
				HashMap map = houseSV.queryHouseInfoByUserIdAndQueryType(userId,1,Integer.parseInt(begin),Integer.parseInt(end));
				rtnJSONObject.putAll(map);
			}else if(showType == 2){
				//查询用户上传的房源
				HashMap map = houseSV.queryHouseInfoByLandlordIdForController(userId,Integer.parseInt(begin),Integer.parseInt(end));
				rtnJSONObject.putAll(map);
			} else if(showType ==3){//获取房屋信息  主页
				int beginI = dataJSONObject.getInt("begin");
				int endI =dataJSONObject.getInt("end");
				String searchContent = dataJSONObject.getString("searchContent");
				HashMap map = houseSV.queryHouseInfoByCondition(searchContent,beginI,endI);
				rtnJSONObject.putAll(map);
			}else if(showType == 4){
				HashMap map = houseSV.queryHouseInfoByUserIdAndQueryType(userId,2,Integer.parseInt(begin),Integer.parseInt(end));
				rtnJSONObject.putAll(map);
			}

		}catch (Exception e){
			e.printStackTrace();
		}
		return rtnJSONObject;
	}
	@RequestMapping(value="/house_delHouse",produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object delHouse(){
		String rtn = "Y";
		JSONObject rtnJSONObject = this.getRtnJSONObject();
		ViewData data = this.getViewData();
		try{
			JSONObject dataJSONObject = data.getJSONObject("DATA");
			long userId = getLongParamFromSession("userId");
			long houseId = APPUtil.getSafeLongParamFromJSONObject(dataJSONObject,"houseId");
			if(userId > 0 && houseId >0){
				houseSV.delHouseInfoByUserIdAndHouseId(userId,houseId);
			}
		}catch (Exception e){
			rtn = "N";
			rtnJSONObject.put("rtnMessage",e.getMessage());
		}
		rtnJSONObject.put("result",rtn);
		return rtnJSONObject;
	}

	
	
}
