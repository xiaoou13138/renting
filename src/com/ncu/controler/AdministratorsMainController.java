package com.ncu.controler;

import com.ncu.data.ViewData;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by xiaoou on 2017/4/21.
 */
@Controller
@Scope("prototype")
public class AdministratorsMainController extends BaseController {
    /**
     * 系统管理员的页面请求
     * @return
     */
    @RequestMapping(value="/administratorsMain")
    public ModelAndView getView()throws Exception{
        ModelAndView mv = this.getModelAndView();
        ViewData data = this.getRtnViewData();
        //先查询是不是系统管理员
        String userType = getStringParamFromSession("userType");
        if(!"admin".equals(userType)){
            return null;
        }
        mv.setViewName("administratorsMain");
        mv.addObject("data",data);
        return mv;
    }
}
