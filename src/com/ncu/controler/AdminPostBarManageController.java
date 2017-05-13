package com.ncu.controler;

import com.ncu.data.ViewData;
import com.ncu.service.interfaces.IMessageSV;
import com.ncu.service.interfaces.IPostSV;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by zuowy on 2017/5/6.
 */
@Controller
@Scope("prototype")
public class AdminPostBarManageController extends BaseController{
    @Resource(name="PostSVImpl")
    private IPostSV postSV;

    @Resource(name="MessageSVImpl")
    private IMessageSV messageSV;

    @RequestMapping(value="/adminPostBarManage_dealAction",produces="application/json;charset=UTF-8")
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
            if(actionType ==  1 ){
                long postId = viewObject.getLong("postId");
                if(postId <= 0){
                    throw new Exception("帖子主键不合法");
                }
                postSV.deletePostInfoByPostId(postId);
            }else if(actionType == 2){
                long messageId = viewObject.getLong("messageId");
                messageSV.deleteMessage(messageId);
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
