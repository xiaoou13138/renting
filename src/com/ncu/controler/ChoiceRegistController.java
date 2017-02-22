package com.ncu.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ncu.data.ViewData;
@Controller
public class ChoiceRegistController extends BaseController{
	
	/**
	 * 用户请求网页
	 * @return
	 */
	@RequestMapping(value="/choiceToRegist")
	public ModelAndView choiceRegist()throws Exception{
		ModelAndView mv = this.getModelAndView();
		ViewData data = new ViewData();
		mv.setViewName("choiceToRegist");
		mv.addObject("data",data);
		return mv;
	} 

}
