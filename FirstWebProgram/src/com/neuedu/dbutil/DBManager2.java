package com.neuedu.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//Statement接口
public class DBManager2 {

	private Connection conn;
	private Statement stmt;

    //构造方法---加载数据库驱动
	public DBManager2(){
		
		//加载驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	//打开数据库连接
	private void openConnection(){
		
		//获取连接
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tylg", "root", "123");
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//更新方法
	public boolean execUpdate(String sql){

		try {
			 
			 this.openConnection();
			 
			 return stmt.executeUpdate(sql) > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally{
			this.closeConnection();
		}
		
	}
	
	//查询方法
	public ResultSet execQuery(String sql){			
		try {
			
			 this.openConnection();
			 
			return stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	//关闭数据库连接
	public void closeConnection(){
		
		try {
			if(stmt!=null){
				stmt.close();
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

		DBManager2 dbManger = new DBManager2();
		
		//String sql = "update user set score = score + 1";
		//dbManger.execUpdate(sql);
		
		String sql = "select * from user";
		ResultSet rs = dbManger.execQuery(sql);
		
		while(rs.next()){
			System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getInt(4) );
		}
		
		dbManger.closeConnection();
	}

}
