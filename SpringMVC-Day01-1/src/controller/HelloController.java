package controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HelloController{
	@RequestMapping(value="/hello.action")
	public String hello(Model model){
		String msg="成功";
		model.addAttribute("msg",msg);
		return "hello";//返回页面逻辑名称
	}
	//获取页面参数
	@RequestMapping(value="/say.action")
	public String say(HttpServletRequest request,HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		System.out.println(name);
		return "hello";
	}
	
	@RequestMapping(value="/addName.action")
	public String addName(String name){
		System.out.println(name);
		return "hello";
	}
	
	

}
