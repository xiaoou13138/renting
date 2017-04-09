package com.ncu.util;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 获取当前系统时间
 * @author oulc
 *
 */
public class TimeUtil {
	/**
	 * 获取yyyy-MM-dd hh:mm:ss格式的当前时间
	 * @return
	 */
	public static Date getCurrentTimeyyyyMMddhhmmss(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = null;
		try{
			date = sdf.parse(sdf.format(new Date()));
		}catch(Exception e){
			e.printStackTrace();
		}
		return date;
	}

	public static String formatTimeyyyyMMddhhmmss(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try{
			return sdf.format(new Date());
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	public static String getCurrentTimeyyyyMMddhhmmssStr(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		try{
			return sdf.format(new Date());
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
}
