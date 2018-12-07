package com.neuedu.dbutil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
 
public class DBManager {
 
	//数据库连接信息
	private static String dbDriver;
	private static String dbUrl;
	private static String dbUsername;
	private static String dbPassword;
	
	//读取配置文件
	static{
		
		Properties prop = new Properties();
		try {
			//加载属性文件
			prop.load(DBManager.class.getResourceAsStream("dbconfig.properties"));
			
			//读取数据库连接信息
			dbDriver = prop.getProperty("dbDriver");
			dbUrl = prop.getProperty("dbUrl");
			dbUsername = prop.getProperty("dbUsername");
			dbPassword = prop.getProperty("dbPassword");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//单例
	private static DBManager instance = new DBManager();
	
	public static DBManager getInstance(){
		return instance;
	}
	
	private Connection conn;
	private PreparedStatement pstmt;
 
    //构造方法---加载数据库驱动
	private DBManager(){
		
		//加载驱动
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
 
	//打开数据库连接
	private void openConnection(){
		
		//获取连接
		try {
			conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
 
	//更新方法
	public boolean execUpdate(String sql, Object... params){         //update user set scoere= scoer+ ?  where score>?
 
		try {
			 //获取连接
			 this.openConnection();
			 
			 //创建语句对象
			 this.pstmt = this.conn.prepareStatement(sql);
			 
			 //参数赋值
			 for (int i = 0; i < params.length; i++) {
				pstmt.setObject(i+1, params[i]);
			 }
			 
			 return pstmt.executeUpdate() > 0;
			 
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally{
			this.closeConnection();
		}
		
	}
	
	//查询方法
	public ResultSet execQuery(String sql, Object... params){	
		
		try {
			//获取连接
			 this.openConnection();
			 
			 //创建语句对象
			 this.pstmt = this.conn.prepareStatement(sql);
			 
			 //参数赋值
			 for (int i = 0; i < params.length; i++) {
				pstmt.setObject(i+1, params[i]);
			 }
			
			 return pstmt.executeQuery();
			 
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	//关闭数据库连接
	public void closeConnection(){
		
		try {
			if(pstmt!=null){
				pstmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if(conn!=null){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
	}
	
	
	public static void main(String[] args) throws SQLException {
 
		//DBManager dbManger = new DBManager();
		DBManager dbManger = DBManager.getInstance();
		
		/*String sql = "update user set score = score + ? where score > ?";
		dbManger.execUpdate(sql, new Object[]{1, 50});*/
 
		String sql = "select * from user";
		ResultSet rs = dbManger.execQuery(sql);
		
		while(rs.next()){
			System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getInt(4) );
		}
		
		dbManger.closeConnection();
	}
 
}