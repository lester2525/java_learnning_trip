package com.cn.hnust.controller;

import java.util.List;
import java.util.function.DoublePredicate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.hnust.pojo.User;
import com.cn.hnust.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private IUserService userService;
	
	@RequestMapping("/showUser")
	public String toIndex(HttpServletRequest request,Model model){
		int userId = Integer.parseInt(request.getParameter("id"));
		User user = this.userService.getUserById(userId);
		model.addAttribute("user", user);
		return "showUser";
	}
	
	@RequestMapping("/showAllUsers")
	public String toAllUsers(Model model){
		List<User> uList = this.userService.selectAll();
		for (User user : uList) {
			System.out.println(user);
		}
		model.addAttribute("uList",uList);
		return "allUser";
	}
	
	@RequestMapping("/modifyUser")
	public String toModify(HttpServletRequest request,Model model){
		int  userId = Integer.parseInt(request.getParameter("userid"));
		User user = userService.getUserById(userId);
		model.addAttribute("user",user);
		return "modifyUser";
	}
	
	@RequestMapping("/updateUser")
	public String doUpdate(User user){
		int i = userService.updateByPrimaryKey(user);
		if(i>0){
			System.out.println("修改成功");
		}else {
			System.err.println("修改失败");
		}
		return "forward:/user/showAllUsers";
	}
	@RequestMapping("/delUser")
	public String toDel(HttpServletRequest request){
		int  userId = Integer.parseInt(request.getParameter("userid"));
		int i = userService.deleteByPrimaryKey(userId);
		if(i>0){
			System.out.println("删除成功");
		}else {
			System.err.println("删除失败");
		}
		return "forward:/user/showAllUsers";
	}
	@RequestMapping("/toAdd")
	public String toAdd(){
		return "addUser";
	}
	@RequestMapping("/addUser")
	public String toAddUser(User user){
		int i = userService.insert(user);
		if(i>0){
			System.out.println("添加成功");
		}else {
			System.err.println("添加失败");
		}
		return "forward:/user/showAllUsers";
	}
}