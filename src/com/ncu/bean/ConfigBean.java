package com.ncu.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 配置信息表
 * @author oulc
 *
 */
@Entity
@Table(name = "config", uniqueConstraints = {   
        @UniqueConstraint(columnNames = {"CODE_TYPE"}),  
}) 
public class ConfigBean {
	@Id
	@Column(name = "CODE_TYPE")
	private String codeType;
	
	@Column(name = "CODE_VALUE")
	private String codeValue;
	
	@Column(name = "DEL_FLAG")
	private int delFlag;
	
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
