package com.ncu.service.interfaces;

public interface IHouseSV {
	/**
	 * 根据房子的信息来生成界面
	 * @return
	 * @throws Exception
	 */
	public String createHtml(long houseId) throws Exception;
	
	/**
	 * 根据房子的信息来生成界面(多个房子)
	 * @param begin 分页的开始
	 * @param end 分页的结束
	 * @return
	 * @throws Exception
	 */
	public String createHtml(int begin,int end) throws Exception;

}
