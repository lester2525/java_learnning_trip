package cn.service;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.anno.SaveToMap;
import cn.bean.Person;
import cn.bean.User;
import cn.dao.UserDao;
import cn.util.SaveMap;
@Service
public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userDao;
	@Resource 
	PersonService personService;
	@Override
	public void addUser(User user) throws SQLException{
		
			Person person = new Person();
			person.setId(user.getId());
			person.setName(user.getName());
			personService.addPerson(person);
			userDao.addUser(user);
		
	}

	@Override
	public void delUser(int id) {
		userDao.delUser(id);

	}

	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);

	}

	@Override
	public List<User> findAll() {
		
		return userDao.findAll();
	}
	@SaveToMap
	@Override
	public User findOne(int id) {
		return userDao.findOne(id);
	}

}
