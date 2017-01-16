package com.ncu.bean;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.*;

/**
 * 用户表
 * @author oulc
 *
 */
@Entity
@Table(name = "user", uniqueConstraints = {   
        @UniqueConstraint(columnNames = {"USER_ID"}),  
}) 
public class UserBean {  
	
	@Id
	@Column(name = "USER_ID")
    private int userId;  
	
	@Column(name = "CODE")
    private String code;  
	
	@Column(name = "PASSWORD")
    private String password;
	
    @Column(name = "DEL_FLAG")
    private String delFlag;
    
    @Column(name = "CREATE_DATE")
    private Date createDate;
    
    private String tableName = "user";
    public UserBean(){
    }
    
    public void setUserId(int value){
    	this.userId = value;
    }
    
    public int getUserId(){
    	return userId;
    }
    
    public void setcode(String value){
    	this.code = value;
    }
    
    public String getcode(){
    	return code;
    }
    
    public void setPassword(String value){
    	this.password = value;
    }
    
    public String getPassword(){
    	return password;
    }
    
    public void setDelFlag(String value){
    	this.delFlag = value;
    }
    
    public String getDelFlag(){
    	return delFlag;
    }
    
    public void setCreateDate(Date value){
    	this.createDate = value;
    }
    
    public Date getCreateDate(){
    	return createDate;
    }
    
    public String getTableName(){
    	return tableName;
    }
}  