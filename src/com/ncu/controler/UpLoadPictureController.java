package com.ncu.controler;

import com.ncu.cache.StaticDataCache;
import com.ncu.service.interfaces.IPictureSV;
import com.ncu.service.interfaces.IUploadPictureSV;
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
     * 用户上传房源的是否上传的图片
     * @param fileUpload
     * @param request
     * @param response
     */
    @RequestMapping(value="/uploadImage")
    @ResponseBody
    public void  fileUpload(@RequestParam("file") MultipartFile fileUpload, DefaultMultipartHttpServletRequest request, HttpServletResponse response){
        String path = cache.getStaticDataByCode("physicalPath").get(0).getCodeValue();//获取图片的物理路径
        try{
            Map params =request.getParameterMap();
            Object a = params.get("pictureType");
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


    /**
     * 用户创建帖子的时候上传的图片
     * @param fileUpload
     * @param request
     * @param response
     */
    @RequestMapping(value="/postBarUploadImage")
    @ResponseBody
    public void  postBarfileUpload(@RequestParam("file") MultipartFile fileUpload, DefaultMultipartHttpServletRequest request, HttpServletResponse response){
        String path = cache.getStaticDataByCode("physicalPath").get(0).getCodeValue();//获取图片的物理路径
        try{
            HashMap params = (HashMap)request.getParameterMap();//获取时间片
            String time[] = (String[])params.get("time");
            long userId = getLongParamFromSession("userId");
            //保存图片和用户的信息  保存图片路径的信息
            String fileName = uploadPictureSV.saveImageByUploadPicture(fileUpload);
            long pictureId = pictureSV.savePictureInfoByPictureName(fileName);
            HttpSession session = this.getSession();
            //获取对象
            HashMap postBarPictureMap = null;
            ArrayList postBarPictureList = null;
            if(session.getAttribute("postBarPictureMap"+time[0]) == null){
                postBarPictureMap =  new HashMap();
                postBarPictureList = new ArrayList();
                postBarPictureMap.put("postBarPictureList",postBarPictureList);
                session.setAttribute("postBarPictureMap"+time[0],postBarPictureMap);
            }else{
                postBarPictureMap = (HashMap) session.getAttribute("postBarPictureMap"+time[0]);
                postBarPictureList = (ArrayList)postBarPictureMap.get("postBarPictureList");
            }
            postBarPictureList.add(pictureId);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

