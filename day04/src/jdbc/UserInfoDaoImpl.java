package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/**
 * UserInfoDao 的实现类
 */
import java.util.List;

import jdbc2.DBUtil;

public class UserInfoDaoImpl implements UserInfoDao {

	@Override
	public boolean save(UserInfo userinfo) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "INSERT INTO userinfo "
					+ "(id,username,password,email,nickname,account) "
					+ "VALUES "
					+ "(seq_userinfo_id.NEXTVAL,?,?,?,?,?) ";
			ps = conn.prepareStatement(sql, new String[]{"id"});
			ps.setString(1, userinfo.getUsername());
			ps.setString(2, userinfo.getPassword());
			ps.setString(3, userinfo.getEmail());
			ps.setString(4, userinfo.getNickname());
			ps.setDouble(5, userinfo.getAccount());
			int i = ps.executeUpdate();
			
			if(i > 0){
				ResultSet rs = ps.getGeneratedKeys();
				
				rs.next();
				int id = rs.getInt(1);
				System.out.println("插入的用户id为："+id);
				 return true;
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(ps !=null){
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null){
				DBUtil.closeConnection(conn);
			}
		}
		return false;
	}

	@Override
	public UserInfo findByName(String username) {
		Connection conn = null;
		PreparedStatement ps = null;
		UserInfo userinfo =new UserInfo();
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT * FROM userinfo "
					+ "WHERE "
					+ "username =?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				userinfo.setId(rs.getInt("id"));
				userinfo.setUsername(rs.getString("username"));
				userinfo.setPassword(rs.getString("password"));
				userinfo.setEmail(rs.getString("email"));
				userinfo.setNickname(rs.getString("nickname"));
				userinfo.setAccount(rs.getDouble("account"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(ps !=null){
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null){
				DBUtil.closeConnection(conn);
			}
		}
		 return userinfo;
	}

	@Override
	public List<UserInfo> findAll() {
		List<UserInfo> userList = new ArrayList<UserInfo>();
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT * FROM userinfo ";
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(sql);
			
			while(rs.next()){
				UserInfo userinfo =new UserInfo();
				userinfo.setId(rs.getInt("id"));
				userinfo.setUsername(rs.getString("username"));
				userinfo.setPassword(rs.getString("password"));
				userinfo.setEmail(rs.getString("email"));
				userinfo.setNickname(rs.getString("nickname"));
				userinfo.setAccount(rs.getDouble("account"));
				userList.add(userinfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(ps !=null){
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null){
				DBUtil.closeConnection(conn);
			}
		}
		 return userList;
	}

	@Override
	public List<UserInfo> findAllByPage(int pageSize, int page) {
		List<UserInfo> userList = new ArrayList<UserInfo>();
		Connection conn = null;
		PreparedStatement ps = null;
		int end = pageSize*page;;
		int start = (page-1)*pageSize+1;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT * "
					+ "FROM  (SELECT ROWNUM rn, t.* "
					+ "FROM (SELECT id,username,password,email,nickname,account "
					+ "FROM userinfo ORDER BY id DESC) t "
					+ "WHERE ROWNUM <= "+end+") "
					+ "WHERE RN  >="+start;
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(sql);
			
			while(rs.next()){
				UserInfo userinfo =new UserInfo();
				userinfo.setId(rs.getInt("id"));
				userinfo.setUsername(rs.getString("username"));
				userinfo.setPassword(rs.getString("password"));
				userinfo.setEmail(rs.getString("email"));
				userinfo.setNickname(rs.getString("nickname"));
				userinfo.setAccount(rs.getDouble("account"));
				userList.add(userinfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(ps !=null){
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null){
				DBUtil.closeConnection(conn);
			}
		}
		 return userList;
	}

	@Override
	public boolean update(UserInfo userinfo) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE userinfo SET" 
			+ " username=?"
			+ ", password=?" 
			+ ", email=?"  
			+ ", nickname=?"
			+ ", account=? "  
			+ " WHERE id=?";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, userinfo.getUsername());
			ps.setString(2,userinfo.getPassword());
			ps.setString(3, userinfo.getEmail());
			ps.setString(4, userinfo.getNickname());
			ps.setDouble(5, userinfo.getAccount());
			ps.setInt(6, userinfo.getId());
			
			int i = ps.executeUpdate();
			
			if(i > 0){
//				System.out.println("更新成功!");
				return true;
			}else {
//				System.err.println("更新失败！");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(ps !=null){
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null){
				DBUtil.closeConnection(conn);
			}
		}
		 return false;
	}

	@Override
	public boolean updateForTransfor(UserInfo u1, UserInfo u2) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
//			/**
//			 * JDBC默认是自动提交事务的，即： 每当执行一条dml操作后，就自动执行commit，
//			 * 若希望控制事务，需要取消自动提交事务控制，然后再控制事务 事务控制是由Connection管理的。
//			 */
			conn = DBUtil.getConnection();
			//取消自動提交事務
			conn.setAutoCommit(false);
			
				String sql = "UPDATE userinfo SET " + "account = ? " + "WHERE id = ?";
				ps = conn.prepareStatement(sql);
				ps.setDouble(1, u1.getAccount());
				ps.setInt(2, u1.getId());
				int i = ps.executeUpdate();
				if (i > 0) {
					ps.setDouble(1, u2.getAccount());
					ps.setInt(2, u2.getId());
					i = ps.executeUpdate();
					if(i> 0){
						conn.commit();
						return true;
					}else {
						conn.rollback();
						return false;
					}
				}else {
					conn.rollback();
					return false;
				}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(ps != null){
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				DBUtil.closeConnection(conn);
			}
		}
		return false;
	}

	@Override
	public boolean deleteById(int id) {
		Connection conn = null;
		PreparedStatement ps =null;
		try {
			conn = DBUtil.getConnection();
			String sql = "DELETE FROM userinfo "
					+ "WHERE "
					+ "id =?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			int i = ps.executeUpdate();
			if(i > 0){
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(conn != null){
				DBUtil.closeConnection(conn);
			}
			if(ps !=null){
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

}
