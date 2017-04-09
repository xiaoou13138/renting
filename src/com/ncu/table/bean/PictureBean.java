package com.ncu.table.bean;

import java.util.ArrayList;

import com.ncu.util.beanUtil.BeanUtil;
import javax.persistence.*
;import java.util.Date;
import java.io.Serializable;
import com.ncu.table.ivalue.IPictureValue;

@Entity
@Table(name ="picture")
public class PictureBean implements IPictureValue,Serializable{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = IPictureValue.S_PictureId)
  private Long pictureId;

  @Column(name = IPictureValue.S_PicturePath)
  private String picturePath;

  @Column(name = IPictureValue.S_CreateDate)
  private Date createDate;

  @Column(name = IPictureValue.S_DelFlag)
  private Long delFlag;

  @Transient
  public static Class beanClass = PictureBean.class;

  @Transient
  public static ArrayList<String> primaryKey = BeanUtil.initPK(beanClass);

  public void setPictureId(Long value){
    this.pictureId = value;
  }

  public Long getPictureId(){
    return pictureId;
  }

  public void setPicturePath(String value){
    this.picturePath = value;
  }

  public String getPicturePath(){
    return picturePath;
  }

  public void setCreateDate(Date value){
    this.createDate = value;
  }

  public Date getCreateDate(){
    return createDate;
  }

  public void setDelFlag(Long value){
    this.delFlag = value;
  }

  public Long getDelFlag(){
    return delFlag;
  }

}