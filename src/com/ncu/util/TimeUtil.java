package com.ncu.util;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 获取当前系统时间
 * @author oulc
 *
 */
public class TimeUtil {
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
}
