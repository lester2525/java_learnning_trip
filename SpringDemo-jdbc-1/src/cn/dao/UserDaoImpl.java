package cn.dao;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.anno.SaveToMap;
import cn.bean.User;
import cn.mapper.UserMapper;
@Repository
public class UserDaoImpl implements UserDao {
	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource
	private UserMapper userMapper;
	@Override
	public void addUser(User user) throws SQLException{
		String sql = "INSERT INTO user VALUES(null,?,?)";
		jdbcTemplate.update(sql,user.getName(),user.getAge());
		int a = 1/0;
	}

	@Override
	public void delUser(int id) {
		String sql = "DELETE FROM user WHERE id=?";
		jdbcTemplate.update(sql,id);

	}

	@Override
	public void updateUser(User user) {
		String sql = "UPDATE user SET name=?,age=? WHERE id=?";
		jdbcTemplate.update(sql,user.getName(),user.getAge(),user.getId());

	}

	@Override
	public List<User> findAll() {
		String sql = "SELECT * FROM user";
		List<User> userlist = jdbcTemplate.query(sql,userMapper);
		return userlist;
	}


	@Override
	public User findOne(int id) {
		String sql = "SELECT * FROM user WHERE id=?";
		User user = jdbcTemplate.queryForObject(sql, userMapper,id);
		return user;
	}

}
