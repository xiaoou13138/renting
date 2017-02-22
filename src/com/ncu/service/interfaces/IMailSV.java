package com.ncu.service.interfaces;

import javax.mail.Message;

public interface IMailSV {
	/**
	 * 邮件配置的初始化
	 */
	public void init();
	
	/**
	 * 根据message发送邮件
	 * @param message
	 * @throws Exception
	 */
	public void send(Message message) throws Exception;
	
	/**
	 * 创建Message对象  里面包含了所有的发送信息
	 * @param mailAddress 发送的地址
	 * @return
	 * @throws Exception
	 */
	public Message crateMessage(String mailAddress) throws Exception;
}
