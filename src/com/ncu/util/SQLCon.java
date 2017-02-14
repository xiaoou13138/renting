package com.ncu.util;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class SQLCon {
	
	/**
	 * 拼接SQL
	 * @param colName 列名
	 * @param colValue 列值
	 * @param condition sql条件
	 * @param map 值
	 * @param connectType 拼接的类型
	 */
	public static void connectSQL(String colName,String colValue,StringBuilder condition,Map params,boolean isLike){
		if(StringUtils.isNotBlank(colValue)){
			if(isLike){
				condition.append(" AND "+colName+" LIKE %:"+colName+"%");
				params.put(colName, colValue);
			}else{
				condition.append(" AND "+colName+" = :"+colName);
				params.put(colName, colValue);
			}
		}
	}
}
