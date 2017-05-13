package com.ncu.controler;

import com.ncu.data.ViewData;
import com.ncu.service.interfaces.IUserSV;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * Created by zuowy on 2017/4/15.
 */
@Controller
@Scope("prototype")
public class EditUserInfoController extends BaseController {
    @Resource(name="UserSVImpl")
    private IUserSV userSV;

    @RequestMapping(value="/editUserInfo")
    public ModelAndView getView()throws Exception{
        ModelAndView mv = this.getModelAndView();
        JSONObject data = this.getRtnJSONObject();
        mv.setViewName("editUserInfo");
        //查询用户信息
        long userId = getLongParamFromSession("userId");
        JSONObject object = userSV.queryUserInfoByForEditView(userId);
        data.putAll(object);
        mv.addObject("data",data);
        return mv;
    }
    @RequestMapping(value="/editUserInfo_viewAction",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object getGroupJoinInfo(){
        JSONObject rtnJSONObject = this.getRtnJSONObject();
        String rtn = "Y";
        try{
            long userId = getLongParamFromSession("userId");
            if(userId <=0){
                throw new Exception("请重新登录");
            }
            ViewData viewData = this.getViewData();
            JSONObject viewObject= viewData.getJSONObject("DATA");
            int actionType = viewObject.getInt("actionType");
            if(actionType == 1){
                //更新信息
                userSV.updateUserInfo(userId,viewObject);
            }
        }catch (Exception e){
            e.printStackTrace();
            rtn="N";
        }
        rtnJSONObject.put("result",rtn);
        return rtnJSONObject;
    }

}
