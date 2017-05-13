package com.ncu.controler;

import com.ncu.data.ViewData;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by zuowy on 2017/4/1.
 */
@Controller
@Scope("prototype")
public class ManagementController extends BaseController {
    @RequestMapping(value="/management")
    public ModelAndView toLogin()throws Exception{
        ModelAndView mv = this.getModelAndView();
        ViewData data = this.getRtnViewData();
        mv.setViewName("management");
        mv.addObject("data",data);
        return mv;
    }
}
