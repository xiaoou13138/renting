package com.ncu.controler;

import com.ncu.data.ViewData;
import com.ncu.service.interfaces.IGroupsSV;
import com.ncu.util.APPUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
import javax.annotation.Resource;

/**
 * Created by xiaoou on 2017/4/1.
 */
@Controller
@Scope("prototype")
public class MemberController extends BaseController {
    @Resource(name="GroupsSVImpl")
    private IGroupsSV groupsSV;

    /**
     * 用户请求网页
     * @return
     */
    @RequestMapping(value="/member")
    public ModelAndView getView()throws Exception{
        ModelAndView mv = this.getModelAndView();
        ViewData data = this.getViewData();
        mv.setViewName("member");
        mv.addObject("data",data);
        return mv;
    }
    @RequestMapping(value="/member_getInfo",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object getInfo(){
        JSONObject rtnObject = this.getRtnJSONObject();
        ViewData viewData = this.getViewData();
        JSONObject viewObject = viewData.getJSONObject("DATA");
        try{
            String groupId = APPUtil.getSafeStringFromJSONObject(viewObject,"groupId");
            int begin = -1;
            int end = -1;
            if(StringUtils.isNotBlank(groupId)){
                List list = groupsSV.queryUserInfoByGroupId(Long.parseLong(groupId),begin,end);
                rtnObject.put("userList",list);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return rtnObject;
    }

}
