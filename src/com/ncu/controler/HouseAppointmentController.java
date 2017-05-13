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
import java.util.HashMap;

/**
 * Created by zuowy on 2017/4/21.
 */
@Controller
@Scope("prototype")
public class HouseAppointmentController extends BaseController{
    @Resource(name="AppointmentSVImpl")
    private IAppointmentSV appointmentSV;

    @RequestMapping(value="/houseAppointment")
    public ModelAndView getView()throws Exception{
        ModelAndView mv = this.getModelAndView();
        ViewData data = this.getRtnViewData();
        mv.setViewName("houseAppointment");
        mv.addObject("data",data);
        return mv;
    }

    @RequestMapping(value="/showMessage_action",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object getGroupJoinInfo()throws Exception{
        JSONObject rtnJSONObject = this.getRtnJSONObject() ;
        String rtn = "Y";
        try{
            ViewData viewData = this.getViewData();
            JSONObject viewObject= viewData.getJSONObject("DATA");
            long userId = getLongParamFromSession("userId");
            int actionType = viewObject.getInt("actionType");
            if(actionType == 1){//查询预约信息
                long houseId = viewObject.getLong("houseId");
                int begin = viewObject.getInt("begin");
                int end = viewObject.getInt("end");
                HashMap map = appointmentSV.queryAppointmentByHouseIdForController(houseId,begin,end);
                rtnJSONObject.putAll(map);
            }else if(actionType ==2){//删除预约信息
                String content = viewObject.getString("content");
                long appointmentId = viewObject.getLong("appointmentId");
                appointmentSV.deleteAppointmentForController(userId,appointmentId,content,1);
            }
        }catch (Exception e){
            e.printStackTrace();
            rtn="N";
            throw e;
        }
        rtnJSONObject.put("result",rtn);
        return rtnJSONObject;
    }
}
