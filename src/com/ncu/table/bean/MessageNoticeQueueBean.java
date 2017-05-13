package com.ncu.table.bean;

import java.util.ArrayList;

import com.ncu.util.beanUtil.BeanUtil;
import javax.persistence.*
;import java.util.Date;
import java.io.Serializable;
import com.ncu.table.ivalue.IMessageNoticeQueueValue;

@Entity
@Table(name ="message_notice_queue")
public class MessageNoticeQueueBean implements IMessageNoticeQueueValue,Serializable{

  @Id
  @Column(name = IMessageNoticeQueueValue.S_UserId)
  private Long userId;

  @Column(name = IMessageNoticeQueueValue.S_MessageNum)
  private Long messageNum;

  @Transient
  public static Class beanClass = MessageNoticeQueueBean.class;

  @Transient
  public static ArrayList<String> primaryKey = BeanUtil.initPK(beanClass);

  public void setUserId(Long value){
    this.userId = value;
  }

  public Long getUserId(){
    return userId;
  }

  public void setMessageNum(Long value){
    this.messageNum = value;
  }

  public Long getMessageNum(){
    return messageNum;
  }

}