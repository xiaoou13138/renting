package com.ncu.controler;

import com.ncu.data.ViewData;
import com.ncu.service.interfaces.IHousePictureRelSV;
import com.ncu.service.interfaces.IPictureSV;
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
 * Created by zuowy on 2017/5/4.
 */
@Controller
@Scope("prototype")
public class HousePictureManageController extends BaseController {
    @Resource(name ="PictureSVImpl")
    private IPictureSV pictureSV;

    @Resource(name="HousePictureRelSVImpl")
    private IHousePictureRelSV housePictureRelSV;

    @RequestMapping(value="/HousePictureManage")
    public ModelAndView getView()throws Exception{
        long userId = getLongParamFromSession("userId");
        if(userId <= 0 ){
            ModelAndView mv = this.getModelAndView();
            mv.setViewName("login");
            return mv;
        }
        ModelAndView mv = this.getModelAndView();//页面的容器
        mv.setViewName("HousePictureManage");
        return mv;
    }

    @RequestMapping(value="/housePictureManage_action" ,produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object getPictureList(){
        JSONObject rtnObject = this.getRtnJSONObject();
        ViewData viewData = this.getViewData();
        String rtn = "Y";
        try{
            JSONObject viewObject = viewData.getJSONObject("DATA");
            int actionType = viewObject.getInt("actionType");
            long houseId = viewObject.getLong("houseId");
            if(actionType == 1){
                int begin  =viewObject.getInt("begin");
                int end  =viewObject.getInt("end");
                HashMap map = pictureSV.queryPictureListByHouseId(houseId,null,begin,end);
                rtnObject.putAll(map);
            }else if(actionType == 2){
                JSONArray pictureList = viewObject.getJSONArray("pictureList");
                housePictureRelSV.deleteHousePictureRel(houseId,pictureList);
            }
        }catch (Exception e){
            rtnObject.put("errMessage",e.getMessage());
            rtn = "N";
            e.printStackTrace();
        }
        rtnObject.put("result",rtn);
        return rtnObject;
    }
}
