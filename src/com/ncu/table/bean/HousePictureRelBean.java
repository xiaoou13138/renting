package com.ncu.table.bean;

import java.util.ArrayList;

import com.ncu.util.beanUtil.BeanUtil;
import javax.persistence.*
;import java.util.Date;
import java.io.Serializable;
import com.ncu.table.ivalue.IHousePictureRelValue;

@Entity
@Table(name ="house_picture_rel")
public class HousePictureRelBean implements IHousePictureRelValue,Serializable{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = IHousePictureRelValue.S_HousePictureRelId)
  private Long housePictureRelId;

  @Column(name = IHousePictureRelValue.S_HouseId)
  private Long houseId;

  @Column(name = IHousePictureRelValue.S_HousePictureId)
  private Long housePictureId;

  @Column(name = IHousePictureRelValue.S_PictureType)
  private String pictureType;

  @Transient
  public static Class beanClass = HousePictureRelBean.class;

  @Transient
  public static ArrayList<String> primaryKey = BeanUtil.initPK(beanClass);

  public void setHousePictureRelId(Long value){
    this.housePictureRelId = value;
  }

  public Long getHousePictureRelId(){
    return housePictureRelId;
  }

  public void setHouseId(Long value){
    this.houseId = value;
  }

  public Long getHouseId(){
    return houseId;
  }

  public void setHousePictureId(Long value){
    this.housePictureId = value;
  }

  public Long getHousePictureId(){
    return housePictureId;
  }

  public void setPictureType(String value){
    this.pictureType = value;
  }

  public String getPictureType(){
    return pictureType;
  }

}