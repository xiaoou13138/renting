package com.ncu.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ncu.data.ViewData;
@Controller
public class TestController extends BaseController{
	/**
	 * 用户请求网页
	 * @return
	 */
	@RequestMapping(value="/test")
	public ModelAndView choiceRegist()throws Exception{
		ModelAndView mv = this.getModelAndView();
		ViewData data = new ViewData();
		mv.setViewName("test");
		mv.addObject("data",data);
		return mv;
	} 
}
