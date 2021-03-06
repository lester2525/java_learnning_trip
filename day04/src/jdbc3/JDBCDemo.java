package jdbc3;

import java.sql.Connection;
import java.sql.Statement;

import jdbc2.DBUtil;

/**
 * 批量执行：
 * 		当有大批量数据插入到数据库某张表时，影响插入主要因素
 * 			事务：一条DML执行一次事务效率是很差
 * 					可以考虑多条记录同时使用同一个事务
 * 			2：PrepareStatement 的使用可以减少执行计划的生成
 * 			3： 减少网络调用，客户一次发送一条SQL与一次发送多条SQL语句到数据库
 * 					服务端的效率也是不一样
 * 
 * 		批量执行就是减少网络调用。大批量SQL执行时应当考虑使用批量执行操作
 * 			
 * @author admin
 *
 */
public class JDBCDemo {
		public static void main(String[] args) {
			Connection conn = null;
			try {
				conn = DBUtil.getConnection();
				Statement state = conn.createStatement();
				long start = System.currentTimeMillis();
				System.out.println("程序开始:"+"时间为:"+start);
				for(int i = 100;i < 200; i++){
					String sql = "INSERT INTO userinfo "
							+ "(id, username, password, email, nickname, account) "
							+ "VALUES "
							+ "("+i+",'test"+i+"','123456','test"+i+"@qq.com','nick"+i+"',5000)";
//					System.out.println(sql);
					state.executeUpdate(sql);
				}
				long end = System.currentTimeMillis();
				
				System.out.println("插入完毕，耗时"+"ms");
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(conn !=null){
					DBUtil.closeConnection(conn);
				}
			}
		}
}
