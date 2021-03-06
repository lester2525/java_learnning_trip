package jdbc2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

/***
 * 该类用于管理数据库连接
 * @author admin
 *
 */
public class DBUtil {
	//org.apache.commons.dbcp.BasicDataSource;
	// 数据库连接池
	private static BasicDataSource ds;
//	private static String className;
//	private static String url;
//	private static String username;
//	private static String password;
//	
	
	// 初始化
	
	static{
		//初始化静态属性
		//1. 加载配置文件 config.properties
		/**
		 * java.util.Properties
		 * 用来读取properties文件并解析其中的每一行内容。
		 */
		
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("config.properties"));
//			className = prop.getProperty("className");
//			password = prop.getProperty("password");
//			url = prop.getProperty("url");
//			username = prop.getProperty("username");
////			System.out.println(className+password+url+username);
			String className = prop.getProperty("classname");
			String password = prop.getProperty("password");
			String url = prop.getProperty("url");
			String username = prop.getProperty("username");
			int maxactive = Integer.parseInt(prop.getProperty("maxactive"));
			int maxwait = Integer.parseInt(prop.getProperty("maxwait"));
			//初始化连接池
			ds = new BasicDataSource();
			//将JDBC所需要的信息设置到连接至连接池中
			//Class.forName()
			ds.setDriverClassName(className);
			// Driver.Manager.getConnection
			ds.setUrl(url);
			ds.setUsername(username);
			ds.setPassword(password);
			ds.setMaxActive(maxactive);
			ds.setMaxWait(maxwait);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取数据库连接
	 * @throws Exception 
	 */
	public static Connection getConnection() throws Exception{
//		Class.forName(className);
//		Connection conn = DriverManager.getConnection(url,username,password);
//		return conn;
		/**
		 * 连接池提供方法
		 * 	Connection getConnection()
		 * 	该方法可以返回一个连接池中可用连接
		 * 这是一个阻塞方法，当连接池中有空闲连接
		 * 可以使用时，会立刻返回
		 * 当没有时，会进入阻塞
		 *阻塞时间由创建连接池时设定
		 *
		 *在等待期间若有空闲连接，立即返回
		 *当超过最大等待时间，仍没有可用连接时，该方法抛出异常
		 *
		 */
		return ds.getConnection();
		
	}
	
	/**
	 * 关闭数据库连接
	 */
	
	public static void closeConnection(Connection conn){
		try {
			/**
			 * 若该连接市通过连接池获取的，要调用这个连接的close方法
			 * 并不是与数据库断开连接，而仅仅是将该连接还给连接池
			 * 
			 * 
			 */
			conn.setAutoCommit(true);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
