package com.ncu.controler;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ncu.data.ViewData;
import com.ncu.service.interfaces.IHouseSV;
@Controller
public class HousesController extends BaseController{
	public static Logger log = Logger.getLogger(LoginController.class);
	
	@Resource(name="HouseSVImpl")
	private IHouseSV sv;
	/**
	 * 用户请求网页
	 * @return
	 */
	@RequestMapping(value="/houses")
	public ModelAndView toLogin()throws Exception{
		ModelAndView mv = this.getModelAndView();
		ViewData data = new ViewData();
		mv.setViewName("houses");
		mv.addObject("data",data);
		return mv;
	}
	
	@RequestMapping(value="/house_getProduct",produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object getProducts() throws Exception{
		JSONObject productObjects = new JSONObject();
		ViewData data = this.getViewData();
		JSONObject object = data.getJSONObject("HOUSEINFO");
		int begin = object.getInt("begin");//房子分页的开始
		int end = object.getInt("end");//房子分页的结束
		
		String html = sv.createHtml(begin,end);
		productObjects.put("html", html);
		productObjects.put("result", "success");
		
		return productObjects;
	}
	
	
	
}
