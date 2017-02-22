package com.ncu.dao.interfaces;

import java.util.HashMap;
import java.util.List;

import com.ncu.table.ivalue.IHouseValue;

public interface IHouseDAO {
	/**
	 * 根据条件来查询房子的信息
	 * @param condition
	 * @param params
	 * @param beginPage 分页开始
	 * @param endPage 分页结束
	 * @return
	 * @throws Exception
	 */
	public List<IHouseValue> getHouseInfoByCondition(String condition , HashMap<String,String> params,int beginPage,int endPage) throws Exception;

}
