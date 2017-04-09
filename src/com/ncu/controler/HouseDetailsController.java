package com.ncu.controler;

import com.ncu.data.ViewData;
import com.ncu.service.interfaces.IHouseSV;
import com.ncu.util.APPUtil;
import net.sf.json.JSONObject;
import org.infinispan.commons.hash.Hash;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * Created by xiaoou on 2017/4/2.
 */
@Controller
@Scope("prototype")
public class HouseDetailsController extends BaseController {
    @Resource(name="HouseSVImpl")
    private IHouseSV houseSV;

    @RequestMapping(value="/houseDetails")
    public ModelAndView getView()throws Exception{
        ModelAndView mv = this.getModelAndView();
        ViewData data = this.getViewData();
        mv.setViewName("houseDetails");
        mv.addObject("data",data);
        return mv;
    }

    @RequestMapping(value="/houseDetails_getBaseInfo",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object getBaseInfo(){
        JSONObject rtnObject = this.getRtnJSONObject();
        try{
            ViewData viewData = this.getViewData();
            JSONObject viewObject = viewData.getJSONObject("DATA");
            String  houseId = APPUtil.getSafeStringFromJSONObject(viewObject,"houseId");
            HashMap map = houseSV.queryDetailsByHouseId(houseId);
            rtnObject.putAll(map);
        }catch (Exception e){
            e.printStackTrace();
        }
        return rtnObject;
    }

}
