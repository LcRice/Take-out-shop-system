package com.neuedu.dbutil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
 
public class DBManager {
 
	//���ݿ�������Ϣ
	private static String dbDriver;
	private static String dbUrl;
	private static String dbUsername;
	private static String dbPassword;
	
	//��ȡ�����ļ�
	static{
		
		Properties prop = new Properties();
		try {
			//���������ļ�
			prop.load(DBManager.class.getResourceAsStream("dbconfig.properties"));
			
			//��ȡ���ݿ�������Ϣ
			dbDriver = prop.getProperty("dbDriver");
			dbUrl = prop.getProperty("dbUrl");
			dbUsername = prop.getProperty("dbUsername");
			dbPassword = prop.getProperty("dbPassword");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//����
	private static DBManager instance = new DBManager();
	
	public static DBManager getInstance(){
		return instance;
	}
	
	private Connection conn;
	private PreparedStatement pstmt;
 
    //���췽��---�������ݿ�����
	private DBManager(){
		
		//��������
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
 
	//�����ݿ�����
	private void openConnection(){
		
		//��ȡ����
		try {
			conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
 
	//���·���
	public boolean execUpdate(String sql, Object... params){         //update user set scoere= scoer+ ?  where score>?
 
		try {
			 //��ȡ����
			 this.openConnection();
			 
			 //����������
			 this.pstmt = this.conn.prepareStatement(sql);
			 
			 //������ֵ
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
	
	//��ѯ����
	public ResultSet execQuery(String sql, Object... params){	
		
		try {
			//��ȡ����
			 this.openConnection();
			 
			 //����������
			 this.pstmt = this.conn.prepareStatement(sql);
			 
			 //������ֵ
			 for (int i = 0; i < params.length; i++) {
				pstmt.setObject(i+1, params[i]);
			 }
			
			 return pstmt.executeQuery();
			 
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	//�ر����ݿ�����
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