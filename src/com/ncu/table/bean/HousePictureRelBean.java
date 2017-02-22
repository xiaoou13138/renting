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

import com.ncu.table.ivalue.IHousePictureRelValue;
import com.ncu.table.ivalue.IPictureValue;
import com.ncu.util.BeanUtil;
@Entity
@Table(name = "house_picture_rel") 
public class HousePictureRelBean implements IHousePictureRelValue{
	
	@Id
	@Column(name = IHousePictureRelValue.S_RelId)
    private long relId; 
	
	@Column(name = IHousePictureRelValue.S_RelType)
    private String relType;
	
	@Column(name = IHousePictureRelValue.S_HouseId)
	private long houseId;
	
	@Column(name = IHousePictureRelValue.S_RelPictureId)
	private long relPictureId;
	
	@Column(name = IHousePictureRelValue.S_DelFlag)
    private int delFlag;
    
    @Column(name = IHousePictureRelValue.S_CreateDate)
    private Date createDate;
    
    @Column(name = IHousePictureRelValue.S_ModifyDate)
    private Date modifyDate;
    
    @Column(name = IHousePictureRelValue.S_OperId)
    private long operId;

    @Transient
	public static Class beanClass = HousePictureRelBean.class;
    
    @Transient
	public static ArrayList<String> primaryKey = BeanUtil.initPK(beanClass);
	
	@Override
	public void setRelId(long value) {
		this.relId = value;
	}

	@Override
	public void setRelType(String value) {
		this.relType = value;		
	}

	@Override
	public void setHouseId(long value) {
		this.houseId = value;		
	}

	@Override
	public void setRelPictureId(long value) {
		this.relPictureId = value;		
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
	public long getRelId() {
		return this.relId;
	}

	@Override
	public String getRelType() {
		return this.relType;
	}

	@Override
	public long getHouseId() {
		return this.houseId;
	}

	@Override
	public long getRelPictureId() {
		return this.relPictureId;
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
