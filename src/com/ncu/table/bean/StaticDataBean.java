package com.ncu.table.bean;

import java.util.ArrayList;

import com.ncu.util.beanUtil.BeanUtil;
import javax.persistence.*
;import java.util.Date;
import java.io.Serializable;
import com.ncu.table.ivalue.IStaticDataValue;

@Entity
@Table(name ="static_data")
public class StaticDataBean implements IStaticDataValue,Serializable{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = IStaticDataValue.S_CodeType)
  private String codeType;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = IStaticDataValue.S_CodeValue)
  private String codeValue;

  @Column(name = IStaticDataValue.S_CodeName)
  private String codeName;

  @Column(name = IStaticDataValue.S_CodeDesc)
  private String codeDesc;

  @Column(name = IStaticDataValue.S_CodeTypeAlias)
  private String codeTypeAlias;

  @Column(name = IStaticDataValue.S_SordId)
  private Long sordId;

  @Column(name = IStaticDataValue.S_DelFlag)
  private String delFlag;

  @Column(name = IStaticDataValue.S_ExtendCodeType)
  private String extendCodeType;

  @Transient
  public static Class beanClass = StaticDataBean.class;

  @Transient
  public static ArrayList<String> primaryKey = BeanUtil.initPK(beanClass);

  public void setCodeType(String value){
    this.codeType = value;
  }

  public String getCodeType(){
    return codeType;
  }

  public void setCodeValue(String value){
    this.codeValue = value;
  }

  public String getCodeValue(){
    return codeValue;
  }

  public void setCodeName(String value){
    this.codeName = value;
  }

  public String getCodeName(){
    return codeName;
  }

  public void setCodeDesc(String value){
    this.codeDesc = value;
  }

  public String getCodeDesc(){
    return codeDesc;
  }

  public void setCodeTypeAlias(String value){
    this.codeTypeAlias = value;
  }

  public String getCodeTypeAlias(){
    return codeTypeAlias;
  }

  public void setSordId(Long value){
    this.sordId = value;
  }

  public Long getSordId(){
    return sordId;
  }

  public void setDelFlag(String value){
    this.delFlag = value;
  }

  public String getDelFlag(){
    return delFlag;
  }

  public void setExtendCodeType(String value){
    this.extendCodeType = value;
  }

  public String getExtendCodeType(){
    return extendCodeType;
  }

}