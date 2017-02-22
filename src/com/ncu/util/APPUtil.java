package com.ncu.util;

import java.util.Map;


import net.sf.json.JSONObject;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.ncu.data.ViewData;

public class APPUtil {
	public static Object returnObject(ViewData data,Map map){
		if(data.containKey("callback")){
			String callback = data.getString("callback");
			return new JSONPObject(callback,map);
			}else{
				return map;
		}
	}
}
