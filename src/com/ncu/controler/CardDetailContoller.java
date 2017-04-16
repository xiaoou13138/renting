package com.ncu.controler;

import com.ncu.data.ViewData;
import com.ncu.service.interfaces.IMessageSV;
import com.ncu.service.interfaces.IPostSV;
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
 * Created by xiaoou on 2017/4/13.
 */
@Controller
@Scope("prototype")
public class CardDetailContoller extends BaseController {
    @Resource(name="PostSVImpl")
    private IPostSV postSV;

    @Resource(name="MessageSVImpl")
    private IMessageSV messageSV;

    @RequestMapping(value="/cardDetail")
    public ModelAndView getView()throws Exception{
        ModelAndView mv = this.getModelAndView();
        ViewData data = this.getRtnViewData();
        mv.setViewName("cardDetail");
        mv.addObject("data",data);
        return mv;
    }

    /**
     * 获取帖子和评论的信息的信息
     * @return
     */
    @RequestMapping(value="/cardDetail_getCardDetailInfo",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object getPostCardInfo(){
        JSONObject rtnJSONObject = this.getRtnJSONObject();
        String rtn = "Y";
        try{
            ViewData viewData = this.getViewData();
            JSONObject viewObject= viewData.getJSONObject("DATA");
            long postId = APPUtil.getSafeLongParamFromJSONObject(viewObject,"postId");
            String begin = APPUtil.getSafeStringFromJSONObject(viewObject,"begin");
            String end = APPUtil.getSafeStringFromJSONObject(viewObject,"end");
            HashMap map = postSV.queryPostBarDetailForController(postId,Integer.parseInt(begin),Integer.parseInt(end));
            rtnJSONObject.putAll(map);
        }catch (Exception e){
            e.printStackTrace();
            rtn="N";
        }
        rtnJSONObject.put("result",rtn);
        return rtnJSONObject;
    }

    /**
     * 回复帖子
     * @return
     */
    @RequestMapping(value="/cardDetail_saveReplyInfo",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object saveReplyInfo(){
        JSONObject rtnJSONObject = this.getRtnJSONObject();
        String rtn = "Y";
        try{
            ViewData viewData = this.getViewData();
            JSONObject viewObject= viewData.getJSONObject("DATA");
            long userId = getLongParamFromSession("userId");
            long postId = APPUtil.getSafeLongParamFromJSONObject(viewObject,"postId");
            String content = APPUtil.getSafeStringFromJSONObject(viewObject,"content");
            messageSV.saveMessageByUserIdAndContent(userId,postId,content);
        }catch (Exception e){
            rtn="N";
        }
        rtnJSONObject.put("result",rtn);
        return rtnJSONObject;
    }
}