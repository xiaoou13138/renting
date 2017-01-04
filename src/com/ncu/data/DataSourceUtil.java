package com.ncu.data;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class DataSourceUtil {
	public static final int DRUID_MYSQL_SOURCE = 1;
	
	public static String configFile = "system/druid.properties";
	
	public static Properties properties = null;
	static {
		properties = new Properties();
		InputStream inputStream = null;
		try {
			configFile = DataSourceUtil.class.getClassLoader().getResource("").getPath()  
	                    + configFile;
			File file = new File(configFile);
			inputStream = new BufferedInputStream(new FileInputStream(file));
			properties.load(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
	
	public static final DataSource getDataSource(int sourceType)throws Exception{
		DataSource dataSource = null;
		switch (sourceType){
		case DRUID_MYSQL_SOURCE:
			dataSource = DruidDataSourceFactory.createDataSource(properties);
			break;
		}
		return dataSource;
	}

}
