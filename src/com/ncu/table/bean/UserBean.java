package com.ncu.table.bean;

import java.util.ArrayList;

import com.ncu.util.beanUtil.BeanUtil;
import javax.persistence.*
;import java.util.Date;
import java.io.Serializable;
import com.ncu.table.ivalue.IUserValue;

@Entity
@Table(name ="user")
public class UserBean implements IUserValue,Serializable{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = IUserValue.S_UserId)
  private Long userId;

  @Column(name = IUserValue.S_UserAccountCode)
  private String userAccountCode;

  @Column(name = IUserValue.S_UserName)
  private String userName;

  @Column(name = IUserValue.S_RealName)
  private String realName;

  @Column(name = IUserValue.S_Password)
  private String password;

  @Column(name = IUserValue.S_UserPhone)
  private Long userPhone;

  @Column(name = IUserValue.S_UserAge)
  private Long userAge;

  @Column(name = IUserValue.S_UserSex)
  private String userSex;

  @Column(name = IUserValue.S_UserType)
  private String userType;

  @Column(name = IUserValue.S_ModifyDate)
  private Date modifyDate;

  @Column(name = IUserValue.S_CreateDate)
  private Date createDate;

  @Column(name = IUserValue.S_DelFlag)
  private Long delFlag;

  @Transient
  public static Class beanClass = UserBean.class;

  @Transient
  public static ArrayList<String> primaryKey = BeanUtil.initPK(beanClass);

  public void setUserId(Long value){
    this.userId = value;
  }

  public Long getUserId(){
    return userId;
  }

  public void setUserAccountCode(String value){
    this.userAccountCode = value;
  }

  public String getUserAccountCode(){
    return userAccountCode;
  }

  public void setUserName(String value){
    this.userName = value;
  }

  public String getUserName(){
    return userName;
  }

  public void setRealName(String value){
    this.realName = value;
  }

  public String getRealName(){
    return realName;
  }

  public void setPassword(String value){
    this.password = value;
  }

  public String getPassword(){
    return password;
  }

  public void setUserPhone(Long value){
    this.userPhone = value;
  }

  public Long getUserPhone(){
    return userPhone;
  }

  public void setUserAge(Long value){
    this.userAge = value;
  }

  public Long getUserAge(){
    return userAge;
  }

  public void setUserSex(String value){
    this.userSex = value;
  }

  public String getUserSex(){
    return userSex;
  }

  public void setUserType(String value){
    this.userType = value;
  }

  public String getUserType(){
    return userType;
  }

  public void setModifyDate(Date value){
    this.modifyDate = value;
  }

  public Date getModifyDate(){
    return modifyDate;
  }

  public void setCreateDate(Date value){
    this.createDate = value;
  }

  public Date getCreateDate(){
    return createDate;
  }

  public void setDelFlag(Long value){
    this.delFlag = value;
  }

  public Long getDelFlag(){
    return delFlag;
  }

}