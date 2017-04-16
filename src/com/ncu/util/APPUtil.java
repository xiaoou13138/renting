package com.ncu.util;

import java.util.Map;


import net.sf.json.JSONObject;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.ncu.data.ViewData;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpSession;

public class APPUtil {
	public static Object returnObject(ViewData data,Map map){
		if(data.containKey("callback")){
			String callback = data.getString("callback");
			return new JSONPObject(callback,map);
			}else{
				return map;
		}
	}

	/**
	 * 从json串里面安全获取值 如果没有key就返回一个空字符串
	 * @param object
	 * @param key
	 * @return
	 */
	public static String getSafeStringFromJSONObject(JSONObject object,String key){
		String rtnValue = "";
		if(object != null && StringUtils.isNotBlank(key)){
			if(object.containsKey(key)){
				rtnValue = object.getString(key);
			}
		}
		return rtnValue;
	}

	/**
	 *  从json串中安全过去long类型的值
	 * @param object
	 * @param key
	 * @return
	 */
	public static long getSafeLongParamFromJSONObject(JSONObject object,String key){
		if(object.containsKey(key)){
			return object.getLong(key);
		}
		return 0L;
	}

}
