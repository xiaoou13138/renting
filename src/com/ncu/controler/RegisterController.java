package com.ncu.controler;

import java.util.Date;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ncu.data.ViewData;
import com.ncu.service.interfaces.IUserSV;
import com.ncu.table.bean.UserBean;
import com.ncu.table.ivalue.IUserValue;
import com.ncu.util.TimeUtil;

@Controller
public class RegisterController extends BaseController{
	@Resource(name="UserSVImpl")
	private IUserSV sv;
	
	public static Logger log = Logger.getLogger(LoginController.class);
	/**
	 * 用户请求网页
	 * @return
	 */
	@RequestMapping(value="/register")
	public ModelAndView toLogin()throws Exception{
		ModelAndView mv = this.getModelAndView();
		ViewData data = this.getViewData();
		mv.setViewName("register");
		mv.addObject("data",data);
		return mv;
	}
	
	
	/**
	 * 用户注册
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/register_register",produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object register() throws Exception{
		JSONObject rtnObject = new JSONObject();
		ViewData data = this.getViewData();
		//获取用户的信息
		JSONObject userInfoObject = data.getJSONObject("USERINFO");
		String code = userInfoObject.getString("code");
		String password = userInfoObject.getString("password");
		String telPhone = userInfoObject.getString("telPhone");
		String name = userInfoObject.getString("name");
		String sex = userInfoObject.getString("sex");
		String userType = userInfoObject.getString("userType");
		//获取当前系统时间
		Date date = TimeUtil.getCurrentTimeyyyyMMddhhmmss();
		//判断用户的code是否是唯一的
		if(sv.checkHasEqualCode(code)){
			rtnObject.put("result", "codeError");
			return rtnObject;
		}
		//创建用户数据容器拼装
		IUserValue valueNew = new UserBean();
		valueNew.setCode(code);
		valueNew.setPassword(password);
		valueNew.setCreateDate(date);
		valueNew.setDelFlag(1);
		valueNew.setPhone(telPhone);
		valueNew.setName(name);
		valueNew.setSex(sex);
		valueNew.setUserType(userType);
		//存储用户的信息
		sv.save(valueNew);
		rtnObject.put("result", "success");
		return rtnObject;
		
	}

}
