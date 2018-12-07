package com.neuedu.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//Statement�ӿ�
public class DBManager2 {

	private Connection conn;
	private Statement stmt;

    //���췽��---�������ݿ�����
	public DBManager2(){
		
		//��������
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	//�����ݿ�����
	private void openConnection(){
		
		//��ȡ����
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tylg", "root", "123");
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//���·���
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
	
	//��ѯ����
	public ResultSet execQuery(String sql){			
		try {
			
			 this.openConnection();
			 
			return stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	//�ر����ݿ�����
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
