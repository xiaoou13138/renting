package com.ncu.controler;

import com.ncu.data.ViewData;
import com.ncu.service.interfaces.IMessageNoticeQueueSV;
import com.ncu.service.interfaces.IMessageSV;
import net.sf.json.JSONObject;
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
public class MessageController extends BaseController {
    @Resource(name="MessageSVImpl")
    private IMessageSV messageSV;

    @Resource(name="MessageNoticeQueueSVImpl")
    private IMessageNoticeQueueSV messageNoticeQueueSV;

    /**
     * 用户请求网页
     * @return
     */
    @RequestMapping(value="/message")
    public ModelAndView getView()throws Exception{
        ModelAndView mv = this.getModelAndView();
        ViewData data = this.getViewData();
        mv.setViewName("message");
        mv.addObject("data",data);
        long userId = getLongParamFromSession("userId");
        if(userId <= 0){
            mv.setViewName("gotoLogin");
        }else{
            messageNoticeQueueSV.saveMessageNoticeQueue(userId,0L,false);
        }

        return mv;
    }

    /**
     * 获取用户私信信息
     * @return
     */
    @RequestMapping(value="/message_getMessage",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object getMessage(){
        JSONObject rtnJSONObject = this.getRtnJSONObject();
        String rtn = "Y";
        try{
            ViewData viewData = this.getViewData();
            JSONObject viewObject= viewData.getJSONObject("DATA");
            long userId = getLongParamFromSession("userId");
            if(userId <=0){
                throw new Exception("用户请先登录");
            }
            int begin = viewObject.getInt("begin");
            int end = viewObject.getInt("end");
            HashMap rtnMap = messageSV.queryMessageByUserIdForController(userId,begin,end);
            rtnJSONObject.putAll(rtnMap);
        }catch (Exception e){
            e.printStackTrace();
            rtn="N";
        }
        rtnJSONObject.put("result",rtn);
        return rtnJSONObject;
    }

    /**
     * 保存私信内容
     * @return
     */
    @RequestMapping(value="/cardDetail_savePrivateReplyInfo",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object savePrivateReplyInfo(){
        JSONObject rtnJSONObject = this.getRtnJSONObject();
        String rtn = "Y";
        try{
            ViewData viewData = this.getViewData();
            JSONObject viewObject= viewData.getJSONObject("DATA");
            long userId = getLongParamFromSession("userId");
            String content = viewObject.getString("content");
            long receiverId = viewObject.getLong("receiverId");
            messageSV.saveMessageByUserIdAndContent(userId,0L,content,1L,receiverId);
        }catch (Exception e){
            e.printStackTrace();
            rtn="N";
        }
        rtnJSONObject.put("result",rtn);
        return rtnJSONObject;
    }

}
