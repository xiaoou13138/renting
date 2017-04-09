package com.ncu.controler;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ncu.data.ViewData;
@Controller
@Scope("prototype")
public class SuccessRegistController extends BaseController{
	/**
	 * 用户请求网页
	 * @return
	 */
	@RequestMapping(value="/successRegist")
	public ModelAndView toLogin()throws Exception{
		ModelAndView mv = this.getModelAndView();
		ViewData data = new ViewData();
		mv.setViewName("successRegist");
		mv.addObject("data",data);
		return mv;
	}

}
