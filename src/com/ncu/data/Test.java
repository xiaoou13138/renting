package com.ncu.data;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.cglib.proxy.Enhancer;



import com.mysql.jdbc.PreparedStatement;

public class Test {
	public static void main(String args[])throws Exception{
		
	}
	/*public static final String url = "jdbc:mysql://127.0.0.1/product";  
    public static final String name = "com.mysql.jdbc.Driver";  
    public static final String user = "root";  
    public static final String password = "123456";
	public static void main(String[] args) throws Exception {
		Connection conn = null;  
	    PreparedStatement pst = null;
	    try {  
            Class.forName(name);//指定连接类型  
            conn = DriverManager.getConnection(url, user, password);//获取连接  
            System.out.println(conn);
          //  pst = conn.prepareStatement(sql);//准备执行语句  
        } catch (Exception e) {  
            e.printStackTrace();  
        }
	}*/

}
