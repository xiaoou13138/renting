package com.ncu.table.bean;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ncu.table.ivalue.IUserTypeDefineValue;
import com.ncu.util.beanUtil.BeanUtil;

@Entity
@Table(name = "user_type_define") 
public class UserTypeDefineBean implements IUserTypeDefineValue{

	@Id
	@Column(name = IUserTypeDefineValue.S_UserType)
    private String userType;
	
	@Column(name = IUserTypeDefineValue.S_TypeDsc)
	private String typeDsc;
	
	@Column(name = IUserTypeDefineValue.S_DelFlag)
	private int delFlag;
	
	@Column(name = IUserTypeDefineValue.S_CreateDate)
	private Date createDate;
	
	@Column(name = IUserTypeDefineValue.S_ModifyDate)
	private Date modifyDate;
	
	@Transient
	public static Class beanClass = UserTypeDefineBean.class;
    
    @Transient
	public static ArrayList<String> primaryKey = BeanUtil.initPK(beanClass);
	
	@Override
	public void setUserType(String value) {
		this.userType = value;
		
	}

	@Override
	public void setTypeDsc(String value) {
		this.typeDsc = value;
	}

	@Override
	public void setDelFlag(int value) {
		this.delFlag = value;
	}

	@Override
	public void setCreateDate(Date value) {
		this.createDate = value;
	}

	@Override
	public void setModifyDate(Date value) {
		this.modifyDate = value;
		
	}

	@Override
	public String getUserType() {
		return this.userType;
	}

	@Override
	public String getTypeDsc() {
		return this.typeDsc;
	}

	@Override
	public int getDelFlag() {
		return this.delFlag;
	}

	@Override
	public Date getCreateDate() {
		return this.createDate;
	}

	@Override
	public Date getModifyDate() {
		return this.modifyDate;
	}

}
