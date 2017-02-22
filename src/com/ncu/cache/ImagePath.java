package com.ncu.cache;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ncu.service.impl.ConfigSVImpl;
/**
 * 图片相关的缓存类
 * @author oulc
 *
 */
@Service
@Scope("singleton")
@DependsOn("BeanUtil")
public class ImagePath{
	private String physicalPath = null;
	private String allowSuffix = null;
	@Autowired
	private ConfigSVImpl sv;
	
	@PostConstruct
	public void init() throws Exception{
		physicalPath = sv.queryConfigByCodeType("physicalPath").get(0).getCodeValue();
		allowSuffix = sv.queryConfigByCodeType("allowSuffix").get(0).getCodeValue();
	}
	
	public String getPhysicalPath(){
		return physicalPath;
	}
	
	public String getAllowSuffix(){
		return allowSuffix;
	}
}
