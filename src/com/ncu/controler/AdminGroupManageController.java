package com.ncu.controler;

import com.ncu.data.ViewData;
import com.ncu.service.interfaces.IGroupsSV;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by xiaoou on 2017/5/5.
 */
@Controller
@Scope("prototype")
public class AdminGroupManageController extends BaseController {
    @Resource(name="GroupsSVImpl")
    private IGroupsSV groupsSV;

    @RequestMapping(value="/adminGroupManage_dealAction",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object dealAction(){
        JSONObject rtnJSONObject = this.getRtnJSONObject();
        String rtn = "Y";
        try{
            String userType = getStringParamFromSession("userType");
            if(!"admin".equals(userType)){
                throw new Exception("用户不是管理员");
            }

            ViewData viewData = this.getViewData();
            JSONObject viewObject= viewData.getJSONObject("DATA");
            int actionType = viewObject.getInt("actionType");
            if(actionType ==  1 ){//获取用户表格信息
                long groupId = viewObject.getLong("groupId");
                if(groupId <= 0){
                    throw new Exception("房子主键不合法");
                }
                groupsSV.deleteGroup(groupId);
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
