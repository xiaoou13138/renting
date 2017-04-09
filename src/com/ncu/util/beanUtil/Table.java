package com.ncu.util.beanUtil;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.ncu.util.StringUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class Table {
	
	private String tableName;
	private String beanName;
	private String engineName;
	private String ivalueName;
	private ArrayList<TableColDsc> colDsc = new ArrayList<TableColDsc>();
	private Connection con = null;
	
	public Table(String tableName,Connection con) throws SQLException{
		this.tableName = tableName;
		this.con = con;
		createTableInfo();
	}
	private void createBeanName(){
		this.beanName = StringUtil.toHumpWord(tableName,true)+"Bean";
	}
	private void createEngineName(){
		this.engineName = StringUtil.toHumpWord(tableName,true)+"Engine";
	}
	private void createIvalueName(){
		this.ivalueName = "I"+StringUtil.toHumpWord(tableName,true)+"Value";
	}
	
	public String getBeanName(){
		return beanName;
	}
	public String getIvalueName(){
		return this.ivalueName;
	}
	public String getEngineName(){
		return this.engineName;
	}
	public String getTableName(){
		return tableName;
	}
	public void createTableInfo() throws SQLException{
		getTableInfoByQuery();
		createBeanName();
		createEngineName();
		createIvalueName();
	}
	/**
	 * 从数据库中获取表的信息
	 * @throws SQLException
	 */
	public void getTableInfoByQuery() throws SQLException{
		PreparedStatement pre = (PreparedStatement) con.prepareStatement("desc "+tableName);
		ResultSet rst = (ResultSet) pre.executeQuery();
		while(rst.next()){
			String colName = rst.getString("Field");
			String type = rst.getString("Type");
			String isKey = rst.getString("Key");
			TableColDsc tableColDsc = new TableColDsc();
			tableColDsc.createTableColDsc(colName, type, isKey);
			colDsc.add(tableColDsc);
		}
	}
	public ArrayList<TableColDsc> getTableColDsc(){
		return colDsc;
	}
}
