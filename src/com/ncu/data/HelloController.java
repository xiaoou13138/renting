package com.ncu.data;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ncu.bean.User;
import com.sun.jmx.snmp.Timestamp;

@Controller
@RequestMapping("/login")
public class HelloController{
	
   @RequestMapping(method = RequestMethod.GET)
   public String printHello(ModelMap model) {
      model.addAttribute("message", "Hello Spring MVC Framework!");
      //test();
      return "login";
   }
   
   public void test(){
	   
       User user = new User(); 
	   user.setUserId(1);
	   user.setDelFlag("1");
	   user.setcode("12");
	   user.setCreateDate(new Timestamp());
	   user.setPassword("123");
	   //bean.getObject().openSession().save(user);
 
   }

}
