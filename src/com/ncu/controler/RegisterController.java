package com.ncu.controler;

import java.util.Date;

import javax.annotation.Resource;

import com.ncu.util.APPUtil;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
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
@Scope("prototype")
public class RegisterController extends BaseController{
	@Resource(name="UserSVImpl")
	private IUserSV sv;
	
	public static Logger log = Logger.getLogger(LoginController.class);
	/**
	 * 用户请求网页
	 * @return
	 */
	@RequestMapping(value="/regist")
	public ModelAndView toLogin()throws Exception{
		ModelAndView mv = this.getModelAndView();
		ViewData data = this.getViewData();
		mv.setViewName("regist");
		mv.addObject("data",data);
		return mv;
	}
	
	
	/**
	 * 用户注册
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/regist_regist",produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object register(){
		JSONObject rtnObject = new JSONObject();
		try{
			ViewData data = this.getViewData();
			//获取用户的信息
			JSONObject userInfoObject = data.getJSONObject("DATA");
			sv.saveUserInfoByViewData(userInfoObject);
			rtnObject.put("result", "Y");
		}catch (Exception e){
			rtnObject.put("result", "N");
			e.printStackTrace();
		}
		return rtnObject;
	}

	@RequestMapping(value="/regist_checkCode",produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object checkCode(){
		JSONObject rtnObject = this.getRtnJSONObject();
		String rtn = "N";
		try{
			ViewData viewData = this.getViewData();
			JSONObject viewObject = viewData.getJSONObject("DATA");
			String code = APPUtil.getSafeStringFromJSONObject(viewObject,"code");
			if(!sv.checkHasEqualCode(code)) {
				rtn = "Y";
			}
		}catch (Exception e){
			e.printStackTrace();;
		}
		rtnObject.put("result",rtn);
		return rtnObject;
	}


}
