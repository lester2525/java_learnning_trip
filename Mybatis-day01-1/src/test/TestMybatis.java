package test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.naming.InitialContext;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import bean.User;

public class TestMybatis {
	SqlSessionFactory sqlSessionFactory =null;
	@Before
	public  void init() throws IOException{
		//获取sqlMapperConfig.xml
	InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
	//SqlSessinFactory
	sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	//sqlSession
	
				//namespace id
	}
	@Test
	public void test01() throws IOException{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<User> uList = sqlSession.selectList("bean.UserMapper.findAll");
		for (User user : uList) {
			System.out.println(user);
		}
		
	}
	
	@Test
	public void test02(){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int i = sqlSession.insert("bean.UserMapper.addUser");
		if(i>0){
			System.out.println("插入成功");
			sqlSession.commit();
		}else {
			System.err.println("插入失败");
			
		}
	}
	
	@Test
	public void test03(){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		User user = sqlSession.selectOne("bean.UserMapper.findOne");
		System.out.println(user);
	}
}