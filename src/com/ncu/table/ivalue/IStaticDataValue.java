package com.ncu.table.ivalue;

import java.util.Date;

public interface IStaticDataValue{
  public final static String S_CodeType = "CODE_TYPE";
  public final static String S_CodeValue = "CODE_VALUE";
  public final static String S_CodeName = "CODE_NAME";
  public final static String S_CodeDesc = "CODE_DESC";
  public final static String S_CodeTypeAlias = "CODE_TYPE_ALIAS";
  public final static String S_SordId = "SORD_ID";
  public final static String S_DelFlag = "DEL_FLAG";
  public final static String S_ExtendCodeType = "EXTEND_CODE_TYPE";
  public void setCodeType(String value);
  public void setCodeValue(String value);
  public void setCodeName(String value);
  public void setCodeDesc(String value);
  public void setCodeTypeAlias(String value);
  public void setSordId(Long value);
  public void setDelFlag(String value);
  public void setExtendCodeType(String value);
  public String getCodeType();
  public String getCodeValue();
  public String getCodeName();
  public String getCodeDesc();
  public String getCodeTypeAlias();
  public Long getSordId();
  public String getDelFlag();
  public String getExtendCodeType();
}