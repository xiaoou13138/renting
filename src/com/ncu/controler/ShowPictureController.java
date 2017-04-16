package com.ncu.controler;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ncu.cache.StaticDataCache;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ncu.data.ViewData;

/**
 * 读取图片的工具类
 * @author oulc
 *
 */
@Controller
@Scope("prototype")
public class ShowPictureController extends BaseController{
	@Autowired
	private StaticDataCache cache;
	public static Logger log = Logger.getLogger(ShowPictureController.class);

	@RequestMapping(value="/showImage")
	public void showImage(HttpServletRequest request,HttpServletResponse response) throws Exception{
		ViewData data = this.getViewData();
		String imageFile = data.getString("imageFile");
		//暂时先用虚拟的路径
		String imagePath = cache.getStaticDataByCode("physicalPath").get(0).getCodeValue()+imageFile;

		FileInputStream fileIs = null;
		OutputStream outStream = null;
		try {
			fileIs = new FileInputStream(imagePath);
			int i=fileIs.available(); //得到文件大小
			byte fileData[]=new byte[i];
			fileIs.read(fileData);  //读数据
			response.setContentType("image/*"); //设置返回的文件类型
			outStream=response.getOutputStream(); //得到向客户端输出二进制数据的对象
			outStream.write(fileData);  //输出数据
			outStream.flush();

		} catch (Exception e) {
			log.error("系统找不到图像文件："+imagePath);
			return;
		}finally{
			try {
				outStream.close();
				fileIs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

}
