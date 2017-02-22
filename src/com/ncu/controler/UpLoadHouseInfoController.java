package com.ncu.controler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ncu.cache.ImagePath;
import com.ncu.data.ViewData;
@Controller
public class UpLoadHouseInfoController extends BaseController{
	
	public static Logger log = Logger.getLogger(UpLoadHouseInfoController.class);
	/**
	 * 用户请求网页
	 * @return
	 */
	@RequestMapping(value="/upLoadHouseInfo")
	public ModelAndView choiceRegist()throws Exception{
		ModelAndView mv = this.getModelAndView();
		ViewData data = new ViewData();
		mv.setViewName("upLoadHouseInfo");
		mv.addObject("data",data);
		return mv;
	} 
	/**
     * 用户上传文件
     * @param pojo
     * @return
     * @throws Exception
     */
	
	@RequestMapping(value="/uploadImage")
	@ResponseBody
	public void fileUpload(@RequestParam ("file") MultipartFile fileUpload,HttpServletRequest request,HttpServletResponse response){
		String path = imagePathObejct.getPhysicalPath();
	}

	     
	
	

}
