package com.ncu.table.bean;

import java.util.ArrayList;

import com.ncu.util.beanUtil.BeanUtil;
import javax.persistence.*
;import java.util.Date;
import java.io.Serializable;
import com.ncu.table.ivalue.IHouseFacilityRelValue;

@Entity
@Table(name ="house_facility_rel")
public class HouseFacilityRelBean implements IHouseFacilityRelValue,Serializable{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = IHouseFacilityRelValue.S_HouseFacilityRelId)
  private Long houseFacilityRelId;

  @Column(name = IHouseFacilityRelValue.S_HouseId)
  private Long houseId;

  @Column(name = IHouseFacilityRelValue.S_CodeType)
  private String codeType;

  @Transient
  public static Class beanClass = HouseFacilityRelBean.class;

  @Transient
  public static ArrayList<String> primaryKey = BeanUtil.initPK(beanClass);

  public void setHouseFacilityRelId(Long value){
    this.houseFacilityRelId = value;
  }

  public Long getHouseFacilityRelId(){
    return houseFacilityRelId;
  }

  public void setHouseId(Long value){
    this.houseId = value;
  }

  public Long getHouseId(){
    return houseId;
  }

  public void setCodeType(String value){
    this.codeType = value;
  }

  public String getCodeType(){
    return codeType;
  }

}