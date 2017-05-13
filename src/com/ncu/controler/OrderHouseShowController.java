package com.ncu.controler;

import com.ncu.data.ViewData;
import com.ncu.service.interfaces.IAppointmentSV;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by zuowy on 2017/4/17.
 */
@Controller
@Scope("prototype")
public class OrderHouseShowController extends BaseController {
    @Resource(name="AppointmentSVImpl")
    private IAppointmentSV appointmentSV;
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

    @RequestMapping(value="/orderHouseShow_dealAction",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object dealAction(){
        JSONObject rtnJSONObject = this.getRtnJSONObject();
        String rtn = "Y";
        try{
            ViewData viewData = this.getViewData();
            JSONObject viewObject= viewData.getJSONObject("DATA");
            int actionType = viewObject.getInt("actionType");
            long userId = getLongParamFromSession("userId");
            if(userId <=0) {
                throw new Exception("用户请先登录");
            }
            if(actionType ==  1 ){//获取用户表格信息
                long appointmentId = viewObject.getLong("appointmentId");
                appointmentSV.deleteAppointmentForController(userId,appointmentId,"",2);
            }
        }catch (Exception e){
            e.printStackTrace();
            rtnJSONObject.put("errMessage",e.getMessage());
            rtn="N";
        }
        rtnJSONObject.put("result",rtn);
        return rtnJSONObject;
    }

}
