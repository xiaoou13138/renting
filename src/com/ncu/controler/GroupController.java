package com.ncu.controler;

import com.ncu.data.ViewData;
import com.ncu.service.interfaces.IApplyGroupMessageSV;
import com.ncu.service.interfaces.IGroupRenterRelSV;
import com.ncu.service.interfaces.IGroupsSV;
import com.ncu.util.APPUtil;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;

/**
 * Created by xiaoou on 2017/4/1.
 */
@Controller
@Scope("prototype")
public class GroupController extends BaseController {
    @Resource(name="GroupsSVImpl")
    private IGroupsSV groupsSV;

    @Resource(name= "ApplyGroupMessageSVImpl")
    private IApplyGroupMessageSV applyGroupMessageSV;


    @Resource(name="GroupRenterRelSVImpl")
    private IGroupRenterRelSV groupRenterRelSV;

    /**
     * 新增组的页面请求
     * @return
     */
    @RequestMapping(value="/group")
    public ModelAndView getView()throws Exception{
        ModelAndView mv = this.getModelAndView();
        ViewData data = this.getRtnViewData();
        mv.setViewName("group");
        mv.addObject("data",data);
        return mv;
    }

    /**
     * 新增组的页面请求
     * @return
     */
    @RequestMapping(value="/addGroup")
    public ModelAndView getAddGroupView()throws Exception{
        ModelAndView mv = this.getModelAndView();
        ViewData data = this.getRtnViewData();
        mv.setViewName("addGroup");
        mv.addObject("data",data);
        return mv;
    }
    /**
     * 用户已经加入的成团页面的初始化
     * @return
     */
    @RequestMapping(value="/group_getJoinGroupInfo",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object getGroupJoinInfo(){
        JSONObject rtnJSONObject = this.getRtnJSONObject();
        try{
            ViewData viewData = this.getViewData();
            JSONObject viewObject= viewData.getJSONObject("DATA");
            String begin = APPUtil.getSafeStringFromJSONObject(viewObject,"begin");
            String end = APPUtil.getSafeStringFromJSONObject(viewObject,"end");
            String searchContent = APPUtil.getSafeStringFromJSONObject(viewObject,"searchContent");
            HashMap rtnMap = groupsSV.queryGroupInfoForController(searchContent,Integer.parseInt(begin),Integer.parseInt(end));
            rtnJSONObject.putAll(rtnMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        return rtnJSONObject;
    }

    /**
     *
     * @return
     */
    @RequestMapping(value="/group_getGroupInfo",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object getGroupInfo(){
        JSONObject rtnJSONObject = this.getRtnJSONObject();
        try{
            long userId = getLongParamFromSession("userId");
            ViewData viewData = this.getViewData();
            JSONObject viewObject= viewData.getJSONObject("DATA");
            String begin = APPUtil.getSafeStringFromJSONObject(viewObject,"begin");
            String end = APPUtil.getSafeStringFromJSONObject(viewObject,"end");
            HashMap rtnMap = groupsSV.queryGroupInfoByUserId(userId,Integer.parseInt(begin),Integer.parseInt(end));
            rtnJSONObject.putAll(rtnMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        return rtnJSONObject;
    }
    /**
     * 退出团
     * @return
     */

    @RequestMapping(value="/group_exitGroup",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object exitGroup(){
        JSONObject rtnJSONObject = this.getRtnJSONObject();
        String rtn = "Y";
        try{
            long userId = getLongParamFromSession("userId");
            ViewData viewData = this.getViewData();
            JSONObject viewObject= viewData.getJSONObject("DATA");
            long groupId = APPUtil.getSafeLongParamFromJSONObject(viewObject,"groupId");
            groupRenterRelSV.exitGroup(userId,groupId);
        }catch (Exception e){
            rtn = "N";
            e.printStackTrace();
        }
        rtnJSONObject.put("result",rtn);
        return rtnJSONObject;
    }




    /**
     * 新增一个团
     * @return
     */
    @RequestMapping(value="/group_createGroup",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object createGroup(){
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
            groupsSV.saveGroup(userId,viewObject);
        }catch (Exception e){
            rtn = "N";
            rtnMessage = e.getMessage();
            e.printStackTrace();
        }
        rtnJSONObject.put("result",rtn);
        rtnJSONObject.put("rtnMessage",rtnMessage);
        return rtnJSONObject;
    }
    /**
     * 新增一个团
     * @return
     */
    @RequestMapping(value="/group_queryGroupMessage",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object queryGroupMessage(){
        JSONObject rtnJSONObject = this.getRtnJSONObject();
        String rtn = "Y";
        String rtnMessage = "";
        try{
            long userId = getLongParamFromSession("userId");
            if(userId == 0){
                throw new Exception("用户没有登录，请登录。");
            }
            ViewData viewData = this.getViewData();
            JSONObject viewObject= viewData.getJSONObject("DATA");
            //查询用户是否有申请的消息
            long messageNum = applyGroupMessageSV.queryApplyMessageCountByUserId(userId);
            rtnJSONObject.put("messageNum",messageNum);
        }catch (Exception e){
            rtn = "N";
            rtnJSONObject.put("rtnMessage",e.getMessage());
            e.printStackTrace();
        }
        rtnJSONObject.put("result",rtn);
        return rtnJSONObject;
    }


}
