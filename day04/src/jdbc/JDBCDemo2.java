package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * ��������
 * seq_userinfo_id
 * ��1��ʼ������Ϊ1
 * @author admin
 *
 */
public class JDBCDemo2 {
	 public static void main(String[] args) {
		try {
			//��������
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//�������ݿ�
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Or175824");
			System.out.println("���ӳɹ�");
			//ִ��sql���
			Statement state = conn.createStatement();
			String sql = "CREATE SEQUENCE seq_userinfo_id "
						+ "START WITH 1 "
						+ "INCREMENT BY 1";
			System.out.println(sql);
			state.execute(sql);
			System.out.println("ִ成功");
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
			
		}
		 
	}
}
