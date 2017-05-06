package com.ncu.table.bean;

import java.util.ArrayList;

import com.ncu.util.beanUtil.BeanUtil;
import javax.persistence.*
;import java.util.Date;
import java.io.Serializable;
import com.ncu.table.ivalue.IHouseValue;

@Entity
@Table(name ="house")
public class HouseBean implements IHouseValue,Serializable{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = IHouseValue.S_HouseId)
  private Long houseId;

  @Column(name = IHouseValue.S_LandlordId)
  private Long landlordId;

  @Column(name = IHouseValue.S_Province)
  private String province;

  @Column(name = IHouseValue.S_City)
  private String city;

  @Column(name = IHouseValue.S_DetailAddress)
  private String detailAddress;

  @Column(name = IHouseValue.S_Room)
  private Long room;

  @Column(name = IHouseValue.S_Hall)
  private Long hall;

  @Column(name = IHouseValue.S_Toilet)
  private Long toilet;

  @Column(name = IHouseValue.S_HouseName)
  private String houseName;

  @Column(name = IHouseValue.S_HouseType)
  private String houseType;

  @Column(name = IHouseValue.S_RentType)
  private String rentType;

  @Column(name = IHouseValue.S_HouseArea)
  private Long houseArea;

  @Column(name = IHouseValue.S_Information)
  private String information;

  @Column(name = IHouseValue.S_DepositType)
  private String depositType;

  @Column(name = IHouseValue.S_Money)
  private Long money;

  @Column(name = IHouseValue.S_CreateDate)
  private Date createDate;

  @Column(name = IHouseValue.S_ModifyDate)
  private Date modifyDate;

  @Column(name = IHouseValue.S_DelFlag)
  private Long delFlag;

  @Transient
  public static Class beanClass = HouseBean.class;

  @Transient
  public static ArrayList<String> primaryKey = BeanUtil.initPK(beanClass);

  public void setHouseId(Long value){
    this.houseId = value;
  }

  public Long getHouseId(){
    return houseId;
  }

  public void setLandlordId(Long value){
    this.landlordId = value;
  }

  public Long getLandlordId(){
    return landlordId;
  }

  public void setProvince(String value){
    this.province = value;
  }

  public String getProvince(){
    return province;
  }

  public void setCity(String value){
    this.city = value;
  }

  public String getCity(){
    return city;
  }

  public void setDetailAddress(String value){
    this.detailAddress = value;
  }

  public String getDetailAddress(){
    return detailAddress;
  }

  public void setRoom(Long value){
    this.room = value;
  }

  public Long getRoom(){
    return room;
  }

  public void setHall(Long value){
    this.hall = value;
  }

  public Long getHall(){
    return hall;
  }

  public void setToilet(Long value){
    this.toilet = value;
  }

  public Long getToilet(){
    return toilet;
  }

  public void setHouseName(String value){
    this.houseName = value;
  }

  public String getHouseName(){
    return houseName;
  }

  public void setHouseType(String value){
    this.houseType = value;
  }

  public String getHouseType(){
    return houseType;
  }

  public void setRentType(String value){
    this.rentType = value;
  }

  public String getRentType(){
    return rentType;
  }

  public void setHouseArea(Long value){
    this.houseArea = value;
  }

  public Long getHouseArea(){
    return houseArea;
  }

  public void setInformation(String value){
    this.information = value;
  }

  public String getInformation(){
    return information;
  }

  public void setDepositType(String value){
    this.depositType = value;
  }

  public String getDepositType(){
    return depositType;
  }

  public void setMoney(Long value){
    this.money = value;
  }

  public Long getMoney(){
    return money;
  }

  public void setCreateDate(Date value){
    this.createDate = value;
  }

  public Date getCreateDate(){
    return createDate;
  }

  public void setModifyDate(Date value){
    this.modifyDate = value;
  }

  public Date getModifyDate(){
    return modifyDate;
  }

  public void setDelFlag(Long value){
    this.delFlag = value;
  }

  public Long getDelFlag(){
    return delFlag;
  }

}