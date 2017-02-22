package com.ncu.data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONTokener;

public class ViewData extends HashMap implements Map{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HashMap<Object,Object> map;
	
	public ViewData(){
		map = new HashMap<Object, Object>();
	}
	
	
	/**
	 *根据request
	 * @param request
	 */
	public ViewData(HttpServletRequest request){
		this.request = request;
		Map properties = request.getParameterMap();
		HashMap returnMap = new HashMap();
		Iterator iterator = properties.entrySet().iterator();
		
		while(iterator.hasNext()){
			Object allValue = "";
			Map.Entry entry = (Map.Entry) iterator.next();
			String key = (String) entry.getKey();
			Object value = entry.getValue();
			if(null == value){
				allValue = "";
			}else if(value instanceof String[]){
				allValue = new JSONTokener(((String[])value)[0]).nextValue();
			}
			returnMap.put(key, allValue);
		}
		map = returnMap;
	}
	
	/**
	 * 根据键值获取值
	 * @param key
	 * @return
	 */
	public Object get(Object key){
		return map.get(key);
	}
	
	public void clear(){
		map.clear();
	}
	
	public boolean containKey(String key){
		return map.containsKey(key);
	}
	
	public boolean containValue(String value){
		return map.containsValue(value);
	}
	
	public boolean isEmpty() {
		return map.isEmpty();
	}
	
	public JSONObject getJSONObject(String key){
		return (JSONObject)map.get(key);
	}
	
	public JSONArray getJSONArray(String key){
		return (JSONArray)map.get(key);
	}
	
	public String getString(Object key){
		return (String)map.get(key);
	}
	
	public long getLong(Object key){
		return Long.parseLong(map.get(key).toString());
	}
	/**
	 * 待优化  重写一下这个方法
	 */
	@Override
	public String toString(){
		Iterator iterator = map.entrySet().iterator();
		StringBuilder content = new StringBuilder();
		while(iterator.hasNext()){
			Object allValue = "";
			Map.Entry entry = (Map.Entry) iterator.next();
			String key = (String) entry.getKey();
			Object value = entry.getValue();
			content.append(key+":"+value+"\r\n");
		}
		return content.toString();
	}

}
