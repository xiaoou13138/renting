package com.ncu.controler;

import com.ncu.data.ViewData;
import com.ncu.service.interfaces.IUserSV;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * Created by xiaoou on 2017/5/5.
 */
@Controller
@Scope("prototype")
public class AdminUserManageController extends BaseController {
    @Resource(name="UserSVImpl")
    private IUserSV userSV;


    /**
     * 系统管理员的页面请求
     * @return
     */
    @RequestMapping(value="/adminUserManage")
    public ModelAndView getView()throws Exception{
        ModelAndView mv = this.getModelAndView();
        ViewData data = this.getRtnViewData();
        //先查询是不是系统管理员
        String userType = getStringParamFromSession("userType");
        if(!"admin".equals(userType)){
            return null;
        }
        mv.setViewName("adminUserManage");
        mv.addObject("data",data);
        return mv;
    }

    @RequestMapping(value="/adminUserManage_dealAction",produces="application/json;charset=UTF-8")
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
                String searchContent = viewObject.getString("searchContent");
                int searchType = viewObject.getInt("searchType");
                int begin = viewObject.getInt("begin");
                int end = viewObject.getInt("end");
                HashMap userMap = userSV.queryUserForControllerByCondition(searchContent,searchType,begin,end);
                rtnJSONObject.putAll(userMap);
            }else if(actionType == 2){
                //删除用户信息
                JSONArray userArray = viewObject.getJSONArray("userList");
                userSV.deleteUser(userArray);
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
