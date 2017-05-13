package com.ncu.controler;

import com.ncu.data.ViewData;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by zuowy on 2017/4/15.
 */
@Controller
@Scope("prototype")
public class CreateGroupController extends BaseController{
    /**
     * 新增组的页面请求
     * @return
     */
    @RequestMapping(value="/createGroup")
    public ModelAndView getView()throws Exception{
        ModelAndView mv = this.getModelAndView();
        ViewData data = this.getRtnViewData();
        mv.setViewName("createGroup");
        mv.addObject("data",data);
        return mv;
    }
}
