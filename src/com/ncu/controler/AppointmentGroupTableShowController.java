package com.ncu.controler;

import com.ncu.data.ViewData;
import com.ncu.service.interfaces.IAppointmentSV;
import com.ncu.service.interfaces.IGroupsSV;
import com.ncu.util.APPUtil;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * Created by xiaoou on 2017/4/14.
 */
@Controller
@Scope("prototype")
public class AppointmentGroupTableShowController extends BaseController {
    @Resource(name="GroupsSVImpl")
    private IGroupsSV groupsSV;

    @Resource(name="AppointmentSVImpl")
    private IAppointmentSV appointmentSV;
    /**
     * 新增组的页面请求
     * @return
     */
    @RequestMapping(value="/appointmentGroupTableShow")
    public ModelAndView getView()throws Exception{
        ModelAndView mv = this.getModelAndView();
        ViewData data = this.getRtnViewData();
        mv.setViewName("appointmentGroupTableShow");
        mv.addObject("data",data);
        return mv;
    }

    /**
     * 用户已经加入的成团页面的初始化
     * @return
     */
    @RequestMapping(value="/group_getAppointmentGroupInfo",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object getGroupJoinInfo(){
        String rtn = "Y";
        JSONObject rtnJSONObject = this.getRtnJSONObject();
        try{
            ViewData viewData = this.getViewData();
            JSONObject viewObject= viewData.getJSONObject("DATA");
            long userId = getLongParamFromSession("userId");
            if(userId < 0){
                rtnJSONObject.put("rtnMessage","用户请先登录");
            }
            String begin = APPUtil.getSafeStringFromJSONObject(viewObject,"begin");
            String end = APPUtil.getSafeStringFromJSONObject(viewObject,"end");
            HashMap rtnMap = groupsSV.queryAppointmentGroup(userId,Integer.parseInt(begin),Integer.parseInt(end));
            rtnJSONObject.putAll(rtnMap);
        }catch (Exception e){
            rtnJSONObject.put("rtnMessage",e.getMessage());
        }
        rtnJSONObject.put("result",rtn);
        return rtnJSONObject;
    }

    @RequestMapping(value="/group_confirmAppointment",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object confirmAppointment(){
        String rtn = "Y";
        JSONObject rtnJSONObject = this.getRtnJSONObject();
        try{
            ViewData viewData = this.getViewData();
            JSONObject viewObject= viewData.getJSONObject("DATA");
            long userId = getLongParamFromSession("userId");
            long houseId = APPUtil.getSafeLongParamFromJSONObject(viewObject,"houseId");
            if(userId < 0){
                rtnJSONObject.put("rtnMessage","用户请先登录");
            }
            appointmentSV.saveAppointmentInfo(userId,houseId,2L);
        }catch (Exception e){
            rtnJSONObject.put("rtnMessage",e.getMessage());
            rtn = "N";
        }
        rtnJSONObject.put("result",rtn);
        return rtnJSONObject;
    }
}
