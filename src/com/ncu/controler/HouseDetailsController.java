package com.ncu.controler;

import com.ncu.data.ViewData;
import com.ncu.service.interfaces.IAppointmentSV;
import com.ncu.service.interfaces.IHouseCollectSV;
import com.ncu.service.interfaces.IHouseSV;
import com.ncu.service.interfaces.IMessageSV;
import com.ncu.util.APPUtil;
import com.ncu.util.StringUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.infinispan.commons.hash.Hash;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * Created by zuowy on 2017/4/2.
 */
@Controller
@Scope("prototype")
public class HouseDetailsController extends BaseController {
    @Resource(name="HouseSVImpl")
    private IHouseSV houseSV;

    @Resource(name="HouseCollectSVImpl")
    private IHouseCollectSV houseCollectSV;

    @Resource(name="AppointmentSVImpl")
    private IAppointmentSV appointmentSV;

    @Resource(name="MessageSVImpl")
    private IMessageSV messageSV;

    @RequestMapping(value="/houseDetails")
    public ModelAndView getView()throws Exception{
        ModelAndView mv = this.getModelAndView();
        ViewData data = this.getViewData();
        mv.setViewName("houseDetails");
        mv.addObject("data",data);
        return mv;
    }

    /**
     * 获取页面信息
     * @return
     */
    @RequestMapping(value="/houseDetails_getBaseInfo",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object getBaseInfo(){
        JSONObject rtnObject = this.getRtnJSONObject();
        try{
            ViewData viewData = this.getViewData();
            JSONObject viewObject = viewData.getJSONObject("DATA");
            long  houseId = APPUtil.getSafeLongParamFromJSONObject(viewObject,"houseId");
            long userId = getLongParamFromSession("userId");
            HashMap map = houseSV.queryDetailsByHouseId(userId,houseId);
            rtnObject.putAll(map);
        }catch (Exception e){
            e.printStackTrace();
        }
        return rtnObject;
    }

    /**
     * 收藏
     * @return
     */
    @RequestMapping(value="/houseDetail_collect",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object collect(){
        JSONObject rtnObject = this.getRtnJSONObject();
        String rtn = "Y";
        try{
            ViewData viewData = this.getViewData();
            JSONObject viewObject = viewData.getJSONObject("DATA");
            long houseId = APPUtil.getSafeLongParamFromJSONObject(viewObject,"houseId");
            long userId = getLongParamFromSession("userId");
            boolean collectState  = viewObject.getBoolean("collectState");//true 取消收藏
            if(userId >0){
                if(!collectState){
                    houseCollectSV.deleteHouseCollectByUserIdAndHouseId(userId,houseId);
                }else{
                    houseCollectSV.saveCollectInfoByUserIdAndHouseId(userId,houseId);
                }

            }else{
                rtnObject.put("rtnMessage","用户请重新登录");
            }
        }catch (Exception e){
            rtn = "N";
            rtnObject.put("rtnMessage",e.getMessage());
        }
        rtnObject.put("result",rtn);
        return rtnObject;
    }

    /**
     * 预约（个人、团体）
     */
    @RequestMapping(value="/houseDetail_appointment",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object appointment(){
        JSONObject rtnObject = this.getRtnJSONObject();
        String rtn = "Y";
        try{
            ViewData viewData = this.getViewData();
            JSONObject viewObject = viewData.getJSONObject("DATA");
            long houseId = APPUtil.getSafeLongParamFromJSONObject(viewObject,"houseId");
            long userId = getLongParamFromSession("userId");
            long appointmentType = APPUtil.getSafeLongParamFromJSONObject(viewObject,"appointmentType");
            if(userId >0){
                appointmentSV.saveAppointmentInfo(userId,0,houseId,appointmentType);
            }else{
                rtn = "N";
                rtnObject.put("rtnMessage","用户请重新登录");
            }
        }catch (Exception e){
            rtn = "N";
            rtnObject.put("rtnMessage",e.getMessage());
        }
        rtnObject.put("result",rtn);
        return rtnObject;
    }

    /**
     * 预约（个人、团体）
     */
    @RequestMapping(value="/houseDetail_sendPrivateMessage",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object sendPrivateMessage(){
        JSONObject rtnObject = this.getRtnJSONObject();
        String rtn = "Y";
        try{
            ViewData viewData = this.getViewData();
            JSONObject viewObject = viewData.getJSONObject("DATA");
            long userId = getLongParamFromSession("userId");
            if(userId <=0){
                throw new Exception("用户请先登录");
            }
            long houseId = viewObject.getLong("houseId");
            String content = viewObject.getString("content");
            if(houseId > 0 && StringUtils.isNotBlank(content)){
                messageSV.saveMessageByHouseId(houseId,userId,content);
            }

        }catch (Exception e){
            rtn = "N";
            rtnObject.put("rtnMessage",e.getMessage());
        }
        rtnObject.put("result",rtn);
        return rtnObject;
    }

}
