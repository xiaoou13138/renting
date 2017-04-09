package com.ncu.controler;

import com.ncu.data.ViewData;
import com.ncu.service.interfaces.IGroupsSV;
import com.ncu.util.APPUtil;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
