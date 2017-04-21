package com.ncu.controler;

import com.ncu.data.ViewData;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by xiaozuo on 2017/4/17.
 */
@Controller
@Scope("prototype")
public class OrderHouseShowController extends BaseController {
    /**
     * 用户请求网页
     * @return
     */
    @RequestMapping(value="/orderHouseShow")
    public ModelAndView getView()throws Exception{
        ModelAndView mv = this.getModelAndView();
        ViewData data = this.getRtnViewData();
        mv.setViewName("orderHouseShow");
        mv.addObject("data",data);
        return mv;
    }
}
