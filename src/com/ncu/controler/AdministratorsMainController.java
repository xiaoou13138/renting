package com.ncu.controler;

import com.ncu.data.ViewData;
import com.ncu.service.interfaces.IMessageSV;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.apache.commons.lang.StringUtils;

import javax.annotation.Resource;

/**
 * Created by zuowy on 2017/4/21.
 */
@Controller
@Scope("prototype")
public class AdministratorsMainController extends BaseController {
    @Resource(name="MessageSVImpl")
    private IMessageSV messageSV;

    /**
     * 系统管理员的页面请求
     * @return
     */
    @RequestMapping(value="/administratorsMain")
    public ModelAndView getView()throws Exception{
        ModelAndView mv = this.getModelAndView();
        ViewData data = this.getRtnViewData();
        //先查询是不是系统管理员
        String userType = getStringParamFromSession("userType");
        if(!"admin".equals(userType)){
            return null;
        }
        mv.setViewName("administratorsMain");
        mv.addObject("data",data);
        return mv;
    }


    @RequestMapping(value="/administratorsMain_dealAction",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object dealAction(){
        JSONObject rtnJSONObject = this.getRtnJSONObject();
        String rtn = "Y";
        try{
            String userType = getStringParamFromSession("userType");
            long userId = getLongParamFromSession("userId");
            if(!"admin".equals(userType) && userId<=0){
                throw new Exception("用户不是管理员");
            }

            ViewData viewData = this.getViewData();
            JSONObject viewObject= viewData.getJSONObject("DATA");
            int actionType = viewObject.getInt("actionType");

            if(actionType ==  1 ){//发布公告
                String content = viewObject.getString("content");
                if(StringUtils.isNotBlank(content)){
                    messageSV.saveMessageByUserIdAndContent(userId,0,content,1,-1);
                }
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
