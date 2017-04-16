package com.ncu.controler;

import com.ncu.data.ViewData;
import com.ncu.service.interfaces.IApplyGroupMessageSV;
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
 * Created by xiaoou on 2017/4/10.
 */
@Controller
@Scope("prototype")
public class ApplyGroupMessageCotroller extends BaseController {
    @Resource(name="ApplyGroupMessageSVImpl")
    IApplyGroupMessageSV applyGroupMessageSV;

    /**
     * 新增组的页面请求
     * @return
     */
    @RequestMapping(value="/showApplyGroupMessage")
    public ModelAndView getView()throws Exception{
        ModelAndView mv = this.getModelAndView();
        ViewData data = this.getRtnViewData();
        mv.setViewName("showApplyGroupMessage");
        mv.addObject("data",data);
        return mv;
    }

    /**
     * 用户已经加入的成团页面的初始化
     * @return
     */
    @RequestMapping(value="/showMessage_getMessage",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object getGroupJoinInfo(){
        JSONObject rtnJSONObject = this.getRtnJSONObject();
        String rtn = "Y";
        try{
            ViewData viewData = this.getViewData();
            JSONObject viewObject= viewData.getJSONObject("DATA");
            long userId = getLongParamFromSession("userId");
            HashMap rtnMap = applyGroupMessageSV.queryApplyGroupMessageForController(userId);
            rtnJSONObject.putAll(rtnMap);
        }catch (Exception e){
            e.printStackTrace();
            rtn="N";
        }
        rtnJSONObject.put("result",rtn);
        return rtnJSONObject;
    }

    /**
     * 处理待审核信息  同意或者是拒绝
     * @return
     */
    @RequestMapping(value="/showMessage_dealMessage",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object dealMessage(){
        JSONObject rtnJSONObject = this.getRtnJSONObject();
        String rtn = "Y";
        try{
            ViewData viewData = this.getViewData();
            JSONObject viewObject= viewData.getJSONObject("DATA");
            String applyId = APPUtil.getSafeStringFromJSONObject(viewObject,"applyId");
            String  dealType = APPUtil.getSafeStringFromJSONObject(viewObject,"dealType");
            applyGroupMessageSV.dealMessage(Long.parseLong(applyId),dealType);
        }catch (Exception e){
            e.printStackTrace();
            rtn="N";
        }
        rtnJSONObject.put("result",rtn);
        return rtnJSONObject;
    }

    /**
     * 申请加入一个团
     * @return
     */
    @RequestMapping(value="/group_addGroup",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object addGroup(){
        JSONObject rtnJSONObject = this.getRtnJSONObject();
        String rtn = "Y";
        String rtnMessage = "";
        try{
            long userId = getLongParamFromSession("userId");
            if(userId == 0){
                rtnMessage = "用户没有登录，请登录。";
            }
            ViewData viewData = this.getViewData();
            JSONObject viewObject= viewData.getJSONObject("DATA");
            String groupId = APPUtil.getSafeStringFromJSONObject(viewObject,"groupId");
            applyGroupMessageSV.saveApplyGroupMessageForController(userId,Long.parseLong(groupId));
        }catch (Exception e){
            rtn = "N";
            rtnMessage = e.getMessage();
            e.printStackTrace();
        }
        rtnJSONObject.put("result",rtn);
        rtnJSONObject.put("rtnMessage",rtnMessage);
        return rtnJSONObject;
    }

}
