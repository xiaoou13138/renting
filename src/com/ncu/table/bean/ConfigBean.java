package com.ncu.table.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.ncu.table.ivalue.IConfigValue;
import com.ncu.util.BeanUtil;

/**
 * 配置信息表
 * @author oulc
 *
 */
@Entity
@Table(name = "config") 
public class ConfigBean implements IConfigValue{
	@Id
	@Column(name = IConfigValue.S_CodeType)
	private String codeType;
	
	@Column(name = IConfigValue.S_CodeValue)
	private String codeValue;
	
	@Column(name = IConfigValue.S_DelFlag)
	private int delFlag;
	
	@Transient
	public static Class beanClass = ConfigBean.class;
	
	@Transient
	public static ArrayList<String> primaryKey = BeanUtil.initPK(beanClass);
	
	public void setCodeType(String value){
		this.codeType = value;
	}
	
	public String getCodeType(){
		return codeType;
	}
	
	public void setCodeValue(String value){
		this.codeValue =value;
	}
	public String getCodeValue(){
		return codeValue;
	}
	
	public void setDelFlag(int value){
		this.delFlag =value;
	}
	public int getDelFlag(){
		return delFlag;
	}
}
