package com.ncu.table.bean;

import java.util.ArrayList;

import com.ncu.util.beanUtil.BeanUtil;
import javax.persistence.*
;import java.util.Date;
import java.io.Serializable;
import com.ncu.table.ivalue.IMessageValue;

@Entity
@Table(name ="message")
public class MessageBean implements IMessageValue,Serializable{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = IMessageValue.S_MessageId)
  private Long messageId;

  @Column(name = IMessageValue.S_PostId)
  private Long postId;

  @Column(name = IMessageValue.S_SenderId)
  private Long senderId;

  @Column(name = IMessageValue.S_ReceiverId)
  private Long receiverId;

  @Column(name = IMessageValue.S_Content)
  private String content;

  @Column(name = IMessageValue.S_IsPrivate)
  private Long isPrivate;

  @Column(name = IMessageValue.S_IsChecked)
  private Long isChecked;

  @Column(name = IMessageValue.S_SenderDate)
  private Date senderDate;

  @Column(name = IMessageValue.S_DelFlag)
  private Long delFlag;

  @Transient
  public static Class beanClass = MessageBean.class;

  @Transient
  public static ArrayList<String> primaryKey = BeanUtil.initPK(beanClass);

  public void setMessageId(Long value){
    this.messageId = value;
  }

  public Long getMessageId(){
    return messageId;
  }

  public void setPostId(Long value){
    this.postId = value;
  }

  public Long getPostId(){
    return postId;
  }

  public void setSenderId(Long value){
    this.senderId = value;
  }

  public Long getSenderId(){
    return senderId;
  }

  public void setReceiverId(Long value){
    this.receiverId = value;
  }

  public Long getReceiverId(){
    return receiverId;
  }

  public void setContent(String value){
    this.content = value;
  }

  public String getContent(){
    return content;
  }

  public void setIsPrivate(Long value){
    this.isPrivate = value;
  }

  public Long getIsPrivate(){
    return isPrivate;
  }

  public void setIsChecked(Long value){
    this.isChecked = value;
  }

  public Long getIsChecked(){
    return isChecked;
  }

  public void setSenderDate(Date value){
    this.senderDate = value;
  }

  public Date getSenderDate(){
    return senderDate;
  }

  public void setDelFlag(Long value){
    this.delFlag = value;
  }

  public Long getDelFlag(){
    return delFlag;
  }

}