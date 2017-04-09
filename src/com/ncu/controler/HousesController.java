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
	public ModelAndView toLogin()throws Exception{
		ModelAndView mv = this.getModelAndView();
		ViewData data = this.getRtnViewData();
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
			String searchContent = APPUtil.getSafeStringFromJSONObject(dataJSONObject,"searchContent");
			HashMap condition = new HashMap();
			condition.put("searchContent",searchContent);
			HashMap map = houseSV.queryHouseInfoByCondtion(condition,begin,end);
			rtnJSONObject.putAll(map);
		}catch (Exception e){
			e.printStackTrace();
		}
		return rtnJSONObject;
	}
	
	
	
}
