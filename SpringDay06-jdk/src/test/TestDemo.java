package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import service.UserService;

public class TestDemo {
	@Test
	public void test01(){
		ApplicationContext context= new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService us = (UserService)context.getBean("userService");
		us.addUser();
		us.updateUser();
		
	}
}
