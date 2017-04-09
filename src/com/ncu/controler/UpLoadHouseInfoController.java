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
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by xiaoou on 2017/4/1.
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
        ModelAndView mv = this.getModelAndView();
        ViewData data = this.getRtnViewData();
        mv.setViewName("upLoadHouseInfo");
        mv.addObject("data",data);
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
            Object object = this.getSession().getAttribute("pictureList");
            ArrayList pictureList = null;
            if(object !=null){
                pictureList = (ArrayList)object;
            }
            try{
                houseSV.saveUpLoadHouseInfo(viewObject,userId,pictureList);
                this.getSession().removeAttribute("pictureList");
                rtn = "Y";
            }catch (Exception e){
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        rtnObject.put("result",rtn);
        return rtnObject;
    }

    /**
     * 用户上传文件
     * @return
     * @throws Exception
     */

    @RequestMapping(value="/uploadImage")
    @ResponseBody
    public void  fileUpload(@RequestParam("file") MultipartFile fileUpload, HttpServletRequest request, HttpServletResponse response){
        String path = cache.getStaticDataByCode("physicalPath").get(0).getCodeValue();
        try{
            long userId = getLongParamFromSession("userId");
            //保存图片和用户的信息  保存图片路径的信息
            String fileName = uploadPictureSV.saveImageByUploadPicture(fileUpload);
            long pictureId = pictureSV.savePictureInfoByPictureName(fileName);
            HttpSession session = this.getSession();
            String type="NORMAL";
            Object object = session.getAttribute("pictureList");
            ArrayList pictureList = null;
            if(object == null){
                pictureList = new ArrayList();
                session.setAttribute("pictureList",pictureList);
                type = "MAIN";
            }else{
                pictureList = (ArrayList)object;
            }
            HashMap map = new HashMap();
            map.put("TYPE",type);
            map.put("PICTURE",pictureId);
            pictureList.add(map);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
