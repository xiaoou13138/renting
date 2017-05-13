package com.ncu.controler;

import com.ncu.data.ViewData;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by zuowy on 2017/5/2.
 */
@Controller
@Scope("prototype")
public class UploaderController extends BaseController{
    @RequestMapping(value="/uploader")
    public ModelAndView getView()throws Exception{
        ModelAndView mv = this.getModelAndView();
        ViewData data = this.getRtnViewData();
        mv.setViewName("uploader");
        mv.addObject("data",data);
        return mv;
    }
}
