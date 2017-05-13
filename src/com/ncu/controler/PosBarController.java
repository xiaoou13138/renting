package com.ncu.controler;

import com.ncu.data.ViewData;
import com.ncu.service.interfaces.IPostSV;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * Created by zuowy on 2017/4/11.
 */
@Controller
@Scope("prototype")
public class PosBarController extends BaseController {
    @Resource(name="PostSVImpl")
    private IPostSV postSV;
    /**
     * 新增组的页面请求
     * @return
     */
    @RequestMapping(value="/postBar")
    public ModelAndView getView()throws Exception{
        ModelAndView mv = this.getModelAndView();
        ViewData data = this.getRtnViewData();
        mv.setViewName("postBar");
        mv.addObject("data",data);
        return mv;
    }

    /**
     * 获取帖子的信息
     * @return
     */
    @RequestMapping(value="/postBar_getPostCardInfo",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object getPostCardInfo(){
        JSONObject rtnJSONObject = this.getRtnJSONObject();
        String rtn = "Y";
        try{
            ViewData viewData = this.getViewData();
            JSONObject viewObject= viewData.getJSONObject("DATA");
            HashMap map = postSV.queryPostInfoForController(viewObject);
            rtnJSONObject.putAll(map);
        }catch (Exception e){
            e.printStackTrace();
            rtn="N";
        }
        rtnJSONObject.put("result",rtn);
        return rtnJSONObject;
    }
}
