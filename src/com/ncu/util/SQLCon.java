package com.ncu.util;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class SQLCon {
	
	/**
	 * 拼接SQL
	 * @param colName 列名
	 * @param colValue 列值
	 * @param condition sql条件
	 */
	public static void connectSQL(String colName,Object colValue,StringBuilder condition,Map params,boolean isLike){
		String realColName = StringUtil.toHumpWord(colName,false);
		if(colValue != null){
			if(isLike){
				condition.append(" AND "+realColName+" LIKE :"+realColName+"");
				params.put(realColName, ""+'%'+colValue+'%');
			}else{
				condition.append(" AND "+realColName+" = :"+realColName);
				params.put(realColName, colValue);
			}
		}
	}
}
