package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import beans.Hello;

public class TestHello {
	@Test
	public void test01(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		//获取对象
//		Hello hello = (Hello) context.getBean("大大大");
		Hello hello = (Hello) context.getBean(Hello.class);
		hello.say();
	}
}
