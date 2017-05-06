package com.ncu.controler;

import com.ncu.cache.StaticDataCache;
import com.ncu.data.ViewData;
import com.ncu.service.interfaces.IPictureSV;
import com.ncu.service.interfaces.IUploadPictureSV;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiaoou on 2017/4/12.
 */
@Controller
@Scope("prototype")
public class UpLoadPictureController extends BaseController {
    @Autowired
    private StaticDataCache cache;
    @Resource(name="UploadPictureSVImpl")
    private IUploadPictureSV uploadPictureSV;
    @Resource(name ="PictureSVImpl")
    private IPictureSV pictureSV;


    /**
     * 用户创建帖子的时候上传的图片
     * @param fileUpload
     * @param request
     * @param response
     */
    @RequestMapping(value="/uploadImage")
    @ResponseBody
    public void  imageFileUpload(@RequestParam("file") MultipartFile fileUpload, DefaultMultipartHttpServletRequest request, HttpServletResponse response){
        String path = cache.getStaticDataByCode("physicalPath").get(0).getCodeValue();//获取图片的物理路径
        try{
            HashMap params = (HashMap)request.getParameterMap();//获取时间片
            String time[] = (String[])params.get("time");
            String pictureType[] = (String[])params.get("pictureType");
            //保存图片和用户的信息  保存图片路径的信息
            String fileName = uploadPictureSV.saveImageByUploadPicture(fileUpload);
            long pictureId = pictureSV.savePictureInfoByPictureName(fileName);
            HttpSession session = this.getSession();
            //获取对象

            HashMap pictureMap = null;
            ArrayList PictureList = null;
            if(session.getAttribute("pictureMap_"+time[0]+"_"+pictureType[0]) == null){
                pictureMap =  new HashMap();
                PictureList = new ArrayList();
                pictureMap.put("pictureList",PictureList);
                session.setAttribute("pictureMap_"+time[0]+"_"+pictureType[0],pictureMap);
            }else{
                pictureMap = (HashMap) session.getAttribute("pictureMap_"+time[0]+"_"+pictureType[0]);
                PictureList = (ArrayList)pictureMap.get("pictureList");
            }
            PictureList.add(pictureId);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

