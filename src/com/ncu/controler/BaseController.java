package com.ncu.controler;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.ncu.service.interfaces.IMessageNoticeQueueSV;
import com.ncu.service.interfaces.IUserSV;
import com.ncu.util.APPUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ncu.data.ViewData;

import java.util.HashMap;

public class BaseController {
	private ViewData rtnViewData = new ViewData();
	private JSONObject rtnJSONObject = new JSONObject();
	private HttpSession session = null;
	HttpServletRequest request = null;

	@Autowired
	@Qualifier("UserSVImpl")
	private IUserSV userSV;

	@Resource(name="MessageNoticeQueueSVImpl")
	private IMessageNoticeQueueSV messageNoticeQueueSV;

	@PostConstruct
	public void init(){
		try{
			request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();//获得request
			session = request.getSession();//获得session
			if(session != null){
				long userId = getLongParamFromSession("userId");
				String userName = getStringParamFromSession("userName");
				String userType = getStringParamFromSession("userType");
				if(userId == 0 ||StringUtils.isBlank(userName) || userType ==null){//如果session里面获取不到userId,就尝试从cookie里面获取用户登录的信息
					Cookie[] cookies  = request.getCookies();
					HashMap map =userSV.checkUserInfoByCookie(cookies);
					userId = (long)map.get("userId");
					userName = (String)map.get("userName");
					userType = (String)map.get("userType");
					session.setAttribute("userId",userId);
					session.setAttribute("userName",userName);
					session.setAttribute("userType",userType);
				}else{
					//查询用户有多少条没有查阅过的私信
					long messageNum = messageNoticeQueueSV.getMessageNum(userId);
					rtnViewData.put("messageNum",messageNum);
					rtnJSONObject.put("messageNum",messageNum);
				}
				rtnViewData.put("userId",userId);
				rtnViewData.put("userName",userName);
				rtnViewData.put("userType",userType);
				rtnJSONObject.put("userId",userId);
				rtnJSONObject.put("userName",userName);
				rtnJSONObject.put("userType",userType);
			}
		}catch (Exception e){
			e.printStackTrace();;
		}

	}
	/**
	 * 获取request
	 * @return
	 */
	public HttpServletRequest getRequest(){
		return request;
	}

	/**
	 * 获取session
	 * @return
	 */
	public HttpSession getSession(){
		return session;
	}
	
	/**
	 * 获得界面传输过来的信息
	 * @return
	 */
	public ViewData getViewData(){
		return new ViewData(this.getRequest());
	}
	
	/**
	 * 得到ModelAndView
	 */
	public ModelAndView getModelAndView(){
		return new ModelAndView();
	}


	public ViewData getRtnViewData(){
		return  rtnViewData;
	}

	/**
	 * 从session里面获取long类型的数据  如果没有就返回0
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public long getLongParamFromSession(String code) throws Exception{
		Object value = session.getAttribute(code);
		if(value != null){
			return  (long)  value;
		}
		return 0;
	}

	/**
	 * 从session里面获取String类型的值 如果不存在就返回null
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public String getStringParamFromSession(String code)throws Exception{
		Object value = session.getAttribute(code);
		if(value != null){
			return  (String)  value;
		}
		return null;
	}

	public JSONObject getRtnJSONObject(){
		return rtnJSONObject;
	}



}
