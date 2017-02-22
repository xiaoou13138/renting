package com.ncu.table.bean;

import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.ncu.table.ivalue.IHouseValue;
import com.ncu.table.ivalue.IUserValue;
import com.ncu.util.BeanUtil;
@Entity
@Table(name = "house") 
public class HouseBean implements IHouseValue{
	@Id
	@Column(name = IHouseValue.S_HouseId)
    private long houseId; 
	
	@Column(name = IHouseValue.S_HouseName)
    private String houseName;
	
	@Column(name = IHouseValue.S_HouseDsc)
	private String houseDsc;
	
	@Column(name = IHouseValue.S_HouseMoney)
	private float houseMoney;
	
	@Column(name = IHouseValue.S_DelFlag)
    private int delFlag;
    
    @Column(name = IHouseValue.S_CreateDate)
    private Date createDate;
    
    @Column(name = IHouseValue.S_ModifyDate)
    private Date modifyDate;
    
    @Column(name = IHouseValue.S_OperId)
    private long operId;
    
    @Transient
	public static Class beanClass = HouseBean.class;
    
    @Transient
	public static ArrayList<String> primaryKey = BeanUtil.initPK(beanClass);

	@Override
	public void setHouseId(long value) {
		this.houseId = value;
	}

	@Override
	public void setHouseName(String value) {
		this.houseName = value;
	}

	@Override
	public void setHouseDsc(String value) {
		this.houseDsc = value;
	}

	@Override
	public void setHouseMoney(float value) {
		this.houseMoney = value;
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
	public void setOperId(long value) {
		this.operId = value;
	}

	@Override
	public long getHouseId() {
		return this.houseId;
	}

	@Override
	public String getHouseName() {
		return this.houseName;
	}

	@Override
	public String getHouseDsc() {
		return this.houseDsc;
	}

	@Override
	public float getHouseMoney() {
		return this.houseMoney;
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

	@Override
	public long getOperId() {
		return this.operId;
	}
}
