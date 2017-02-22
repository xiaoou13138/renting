package com.ncu.service.interfaces;

import java.util.List;

import com.ncu.table.bean.ConfigBean;

public interface IConfigSV {
	/**
	 * 根据codeType区查询配置表的数据
	 * @param codeType
	 * @return
	 * @throws Exception
	 */
	public List<ConfigBean> queryConfigByCodeType(String codeType) throws Exception;
}
