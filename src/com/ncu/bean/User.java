package com.ncu.bean;

import javax.persistence.*;

import com.sun.jmx.snmp.Timestamp;


@SuppressWarnings("deprecation")
@Entity
@Table(name = "USER", uniqueConstraints = {   
        @UniqueConstraint(columnNames = {"USER_ID"}),  
}) 
public class User {  
	
    private int userId;  
	
    private String code;  
	
    private String password;
	
    private String delFlag;
	
    private Timestamp createDate;
    
    public User(){
    }
    
    public void setUserId(int value){
    	this.userId = value;
    }
    @Id
	@Column(name = "USER_ID")
    public int getUserId(){
    	return userId;
    }
    
    public void setcode(String value){
    	this.code = value;
    }
    @Column(name = "CODE")
    public String getcode(){
    	return code;
    }
    
    public void setPassword(String value){
    	this.password = value;
    }
    @Column(name = "PASSWORD")
    public String getPassword(){
    	return password;
    }
    
    public void setDelFlag(String value){
    	this.delFlag = value;
    }
    @Column(name = "DEL_FLAG")
    public String getDelFlag(){
    	return delFlag;
    }
    
    public void setCreateDate(Timestamp value){
    	this.createDate = value;
    }
    @Column(name = "CREATE_DATE")
    public Timestamp getCreateDate(){
    	return createDate;
    }
}  