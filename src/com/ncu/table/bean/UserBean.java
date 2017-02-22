package com.ncu.table.bean;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.persistence.*;

import org.hibernate.metadata.ClassMetadata;

import com.ncu.table.ivalue.IUserValue;
import com.ncu.util.BeanUtil;

/**
 * 用户表
 * @author oulc
 *
 */
@Entity
@Table(name = "user") 
public class UserBean implements IUserValue{  
	
	@Id
	@Column(name = IUserValue.S_UserId)
    private int userId;  
	
	@Column(name = IUserValue.S_Code)
    private String code;  
	
	@Column(name = IUserValue.S_Password)
    private String password;
	
    @Column(name = IUserValue.S_DelFlag)
    private int delFlag;
    
    @Column(name = IUserValue.S_CreateDate)
    private Date createDate;
    
    @Column(name = IUserValue.S_ModifyDate)
    private Date modifyDate;
    
    @Column(name = IUserValue.S_UserType)
    private String userType;
    
    @Column(name = IUserValue.S_Phone)
    private String phone;
    
    @Column(name = IUserValue.S_Name)
    private String name;
    
    @Column(name = IUserValue.S_Sex)
    private String sex;
    
    @Transient
	public static Class beanClass = UserBean.class;
    
    @Transient
	public static ArrayList<String> primaryKey = BeanUtil.initPK(beanClass);
    
    @Override
    public void setUserId(int value){
    	this.userId = value;
    }
    
    @Override
    public void setCode(String value){
    	this.code = value;
    }
    
    @Override
    public void setDelFlag(int value){
    	this.delFlag = value;
    }
    
    @Override
    public void setPassword(String value){
    	this.password = value;
    }
    
    @Override
    public void setCreateDate(Date value){
    	this.createDate = value;
    }
    
    @Override
	public void setModifyDate(Date value) {
		this.modifyDate = value;
	}
    
	@Override
	public void setUserType(String value) {
		this.userType = value;
	}
    
	@Override
    public int getUserId(){
    	return userId;
    }
    
	@Override
    public String getCode(){
    	return code;
    }
    
	@Override
    public String getPassword(){
    	return password;
    }
    
	@Override
    public int getDelFlag(){
    	return delFlag;
    }
    
	@Override
    public Date getCreateDate(){
    	return createDate;
    }
	
	@Override
	public Date getModifyDate() {
		return this.modifyDate;
	}
	
	@Override
	public String getUserType() {
		return this.userType;
	}

	@Override
	public void setPhone(String value) {
		this.phone = value;
	}

	@Override
	public void setName(String value) {
		this.name = value;
	}

	@Override
	public void setSex(String value) {
		this.sex = value;
	}

	@Override
	public String getPhone() {
		return phone;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getSex() {
		return sex;
	}
}  