package com.ncu.util.beanUtil;

import com.ncu.util.StringUtil;

public class TableColDsc {
	private String colName;
	private String colType;
	private boolean isUnique;
	private String S_ColName;
	private String humpColNameFU;
	private String humpColNameFL;
	public void createTableColDsc(String colNameIn,String typeIn,String isKeyIn){
		String type = "";
		if(typeIn.indexOf("(") !=-1){
			type = typeIn.substring(0,typeIn.indexOf("("));
		}else{
			type = typeIn;
		}
		
		if("datetime".equals(type) || "date".equals(type)){
			colType = "Date";
		}
		if("varchar".equals(type)){
			colType = "String";
		}
		if("char".equals(type)){
			colType = "String";
		}
		if("int".equals(type)){
			colType = "Long";
		}
		if("float".equals(type)){
			colType = "Float";
		}
		if("bigint".equals(type)){
			colType = "Long";
		}
		if("PRI".equals(isKeyIn)){
			isUnique = true;
		}else{
			isUnique = false;
		}
		
		colName = colNameIn.toUpperCase();
		S_ColName = "S_"+StringUtil.toHumpWord(colName, true);
		humpColNameFU = StringUtil.toHumpWord(colName, true);
		humpColNameFL = StringUtil.toHumpWord(colName, false);
	}
	public String getS_Code(){
		return S_ColName;
	}
	public String getColName(){
		return colName;
	}
	public String getColType(){
		return colType;
	}
	public boolean isUnique(){
		return isUnique;
	}
	public String getHumpColNameFU(){
		return humpColNameFU;
	}
	public String gethumpColNameFL(){
		return humpColNameFL; 
	}

}
