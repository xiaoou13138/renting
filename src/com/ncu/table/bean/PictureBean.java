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
import com.ncu.table.ivalue.IPictureValue;
import com.ncu.util.BeanUtil;
@Entity
@Table(name = "picture") 
public class PictureBean implements IPictureValue{
	
	@Id
	@Column(name = IPictureValue.S_PictureId)
    private long pictureId; 
	
	@Column(name = IPictureValue.S_PicturePath)
    private String picturePath;
	
	@Column(name = IPictureValue.S_DelFlag)
    private int delFlag;
    
    @Column(name = IPictureValue.S_CreateDate)
    private Date createDate;
    
    @Column(name = IPictureValue.S_ModifyDate)
    private Date modifyDate;
    
    @Column(name = IPictureValue.S_OperId)
    private long operId;

    @Transient
	public static Class beanClass = PictureBean.class;
    
    @Transient
	public static ArrayList<String> primaryKey = BeanUtil.initPK(beanClass);
    
	@Override
	public void setPictureId(long value) {
		this.pictureId = value;
	}

	@Override
	public void setPicturePath(String value) {
		this.picturePath = value;
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
	public long getPictureId() {
		return this.pictureId;
	}

	@Override
	public String getPicturePath() {
		return this.picturePath;
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
