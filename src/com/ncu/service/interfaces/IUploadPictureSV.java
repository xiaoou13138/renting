package com.ncu.service.interfaces;

import org.springframework.web.multipart.MultipartFile;

/**
 * 上传图片的功能类
 * @author oulc
 *
 */
public interface IUploadPictureSV {
	/**
	 * 根据上传上来的数据保存成图片
	 * @param file 图片
	 * @return
	 * @throws Exception 
	 */
	public boolean saveImageByUploadPicture(MultipartFile file) throws Exception;
	/**
	 * 获取文件的名称
	 * @param file 图片
	 * @return
	 * @throws Exception 
	 */
	public String getFileName(MultipartFile file) throws Exception;

}
