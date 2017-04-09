package com.ncu.controler;

import com.ncu.data.ViewData;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by xiaoou on 2017/4/2.
 */
@Controller
@Scope("prototype")
public class MessageController extends BaseController {
    /**
     * 用户请求网页
     * @return
     */
    @RequestMapping(value="/message")
    public ModelAndView getView()throws Exception{
        ModelAndView mv = this.getModelAndView();
        ViewData data = this.getViewData();
        mv.setViewName("message");
        mv.addObject("data",data);
        return mv;
    }
}
