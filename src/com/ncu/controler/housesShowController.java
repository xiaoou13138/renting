package com.ncu.controler;

import com.ncu.data.ViewData;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by zuowy on 2017/4/11.
 */
@Controller
@Scope("prototype")
public class housesShowController extends BaseController {
    /**
     * 新增组的页面请求
     * @return
     */
    @RequestMapping(value="/housesShow")
    public ModelAndView getView()throws Exception{
        ModelAndView mv = this.getModelAndView();
        ViewData data = this.getRtnViewData();
        mv.setViewName("housesShow");
        mv.addObject("data",data);
        return mv;
    }
    /**
     * 收藏房子页面
     * @return
     */
    @RequestMapping(value="/collectHouseShow")
    public ModelAndView getCollectView()throws Exception{
        ModelAndView mv = this.getModelAndView();
        ViewData data = this.getRtnViewData();
        mv.setViewName("collectHouseShow");
        mv.addObject("data",data);
        return mv;
    }

    /**
     * 房东上传的房子
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/myHouse")
    public ModelAndView getMyView()throws Exception{
        ModelAndView mv = this.getModelAndView();
        ViewData data = this.getRtnViewData();
        mv.setViewName("myHouse");
        mv.addObject("data",data);
        return mv;
    }

}
