package com.ncu.controler;

import com.ncu.cache.StaticDataCache;
import com.ncu.data.ViewData;
import com.ncu.service.interfaces.IHouseSV;
import com.ncu.service.interfaces.IPictureSV;
import com.ncu.service.interfaces.IUploadPictureSV;
import com.ncu.util.APPUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zuowy on 2017/4/1.
 */
@Controller
@Scope("prototype")
public class UpLoadHouseInfoController extends BaseController {
    @Resource(name="HouseSVImpl")
    private IHouseSV houseSV;
    @Autowired
    private StaticDataCache cache;
    @Resource(name="UploadPictureSVImpl")
    private IUploadPictureSV uploadPictureSV;

    @Resource(name ="PictureSVImpl")
    private IPictureSV pictureSV;

    @RequestMapping(value="/upLoadHouseInfo")
    public ModelAndView getView()throws Exception{
        long userId = getLongParamFromSession("userId");
        if(userId <= 0 ){
            ModelAndView mv = this.getModelAndView();
            mv.setViewName("login");
            return mv;
        }

        ModelAndView mv = this.getModelAndView();//页面的容器
        mv.setViewName("upLoadHouseInfo");
        JSONObject data = this.getRtnJSONObject();
        ViewData viewData = this.getViewData();//这个是页面url拼接的参数
        long viewType = viewData.getLong("viewType");//获得页面的类型
        if(viewType == 1 ){

        }else if(viewType == 2){
            long houseId = viewData.getLong("houseId");
            if(houseId > 0){
                HashMap map = houseSV.queryAllHouseInfoByHouseId(houseId);
                data.putAll(map);
            }
            mv.addObject("data",data);
        }
        return mv;
    }

    @RequestMapping(value="/upLoadHouseInfo_getInfo" ,produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object getInfo(){
        JSONObject rtnObject = this.getRtnJSONObject();
        ViewData viewData = this.getViewData();
        JSONObject viewObject = viewData.getJSONObject("DATA");
        try{
            String userId = APPUtil.getSafeStringFromJSONObject(viewObject,"houseId");
        }catch (Exception e){
            e.printStackTrace();
        }
        return rtnObject;
    }

    @RequestMapping(value="/upLoadHouseInfo_upLoad" ,produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object saveInfo(){
        JSONObject rtnObject = this.getRtnJSONObject();
        ViewData viewData = this.getViewData();
        String rtn = "N";
        try{
            JSONObject viewObject = viewData.getJSONObject("DATA");
            long userId = getLongParamFromSession("userId");
            if(userId <=0){
                throw new Exception("用户请先登录");
            }
            String time = viewObject.getString("time");
            int viewType = viewObject.getInt("viewType");
            ArrayList mainList = new ArrayList();
            ArrayList normalList = new ArrayList();
            if(StringUtils.isNotBlank(time)){
                if(this.getSession().getAttribute("pictureMap_"+time+"_1") != null){
                    HashMap postBarPictureMap = (HashMap)this.getSession().getAttribute("pictureMap_"+time+"_1");
                    mainList = (ArrayList) postBarPictureMap.get("pictureList");
                }
                if(this.getSession().getAttribute("pictureMap_"+time+"_2") != null){
                    HashMap postBarPictureMap = (HashMap)this.getSession().getAttribute("pictureMap_"+time+"_2");
                    normalList = (ArrayList) postBarPictureMap.get("pictureList");
                }
            }
            houseSV.saveUpLoadHouseInfo(viewObject,userId,mainList,normalList,viewType);
            this.getSession().removeAttribute("pictureMap_"+time+"_1");
            this.getSession().removeAttribute("pictureMap_"+time+"_2");
            rtn = "Y";
        }catch (Exception e){
            rtnObject.put("errMessage",e.getMessage());
            e.printStackTrace();
        }

        rtnObject.put("result",rtn);
        return rtnObject;
    }


}
