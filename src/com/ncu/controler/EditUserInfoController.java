package com.ncu.controler;

import com.ncu.data.ViewData;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by xiaoou on 2017/4/15.
 */
@Controller
@Scope("prototype")
public class EditUserInfoController extends BaseController {

    @RequestMapping(value="/editUserInfo")
    public ModelAndView getView()throws Exception{
        ModelAndView mv = this.getModelAndView();
        ViewData data = this.getRtnViewData();
        mv.setViewName("editUserInfo");
        mv.addObject("data",data);
        return mv;
    }
}