package com.ncu.table.ivalue;

public interface IConfigValue{
	public final static String S_CodeType =  "CODE_TYPE";
	public final static String S_CodeValue  = "CODE_VALUE";
	public final static String S_DelFlag = "DEL_FLAG";
	
	public void setCodeType(String value);
	public void setCodeValue(String value);
	public void setDelFlag(int value);
	
	public String getCodeType();
	public String getCodeValue();
	public int getDelFlag();
}
