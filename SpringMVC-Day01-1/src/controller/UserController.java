package controller;

import java.lang.ProcessBuilder.Redirect;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.CustomAutowireConfigurer;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bean.User;

@Controller
public class UserController {
	@RequestMapping(value="/toUser.action")
	public String toUser(){
		return "user";
	}
	
	@RequestMapping(value="/addUser.action")
	public String addUser(Model model,User user,@RequestParam(value="state",required=true,defaultValue="null") String state){
		System.out.println(user);
		System.out.println(state);
		String msg ="添加完成";
		model.addAttribute("msg",msg);
		return "hello";
	}
	
	//转发到用户注册页面
	@RequestMapping(value="/helloForWard.action")
	public String Forward(){
		System.out.println("实现转发");
		return "forward:/toUser.action";
	}
	
	//重定向到用户注册页面
	@RequestMapping(value="/helloRedirect.action")
	public String Redirect(){
		System.out.println("实现重定向");
		return "redirect:/toUser.action";
	}
	
	//日期格式转换
	@InitBinder
	public void IntiBonder(ServletRequestDataBinder binder){
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
		
	}
}
