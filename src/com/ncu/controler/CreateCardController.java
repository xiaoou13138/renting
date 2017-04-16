package com.ncu.controler;

import com.ncu.data.ViewData;
import com.ncu.service.interfaces.IPostPictureRelSV;
import com.ncu.service.interfaces.IPostSV;
import com.ncu.util.APPUtil;
import com.ncu.util.StringUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by xiaoou on 2017/4/11.
 */
@Controller
@Scope("prototype")
public class CreateCardController extends BaseController {
    @Resource(name="PostSVImpl")
    private IPostSV postSV;

    @Resource(name="PostPictureRelSVImpl")
    private IPostPictureRelSV postPictureRelSV;
    /**
     * 新增组的页面请求
     * @return
     */
    @RequestMapping(value="/createCard")
    public ModelAndView getView()throws Exception{
        ModelAndView mv = this.getModelAndView();
        ViewData data = this.getRtnViewData();
        mv.setViewName("createCard");
        mv.addObject("data",data);
        return mv;
    }


    /**
     * 用户创建一个新的帖子
     * @return
     */
    @RequestMapping(value="/createCard_saveCard",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object saveCardInfo(){
        JSONObject rtnJSONObject = this.getRtnJSONObject();
        String rtn = "Y";
        try{
            ViewData viewData = this.getViewData();
            JSONObject viewObject= viewData.getJSONObject("DATA");
            long userId = getLongParamFromSession("userId");
            long postId = postSV.savePostInfoForController(viewObject,userId);

            //保存图片关联
            String time = APPUtil.getSafeStringFromJSONObject(viewObject,"time");
            if(StringUtils.isNotBlank(time)){
                if(this.getSession().getAttribute("postBarPictureMap"+time) != null){
                    HashMap postBarPictureMap = (HashMap)this.getSession().getAttribute("postBarPictureMap"+time);
                    ArrayList pictureList = (ArrayList)postBarPictureMap.get("postBarPictureList");
                    //需要保存图片个帖子的关系
                    postPictureRelSV.savePostPictureRelForController(postId,pictureList);
                    this.getSession().removeAttribute("postBarPictureMap"+time);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            rtn="N";
        }
        rtnJSONObject.put("result",rtn);
        return rtnJSONObject;
    }
}
