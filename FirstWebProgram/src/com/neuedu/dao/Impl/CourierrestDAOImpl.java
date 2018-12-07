package com.neuedu.dao.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.neuedu.dao.CourierrestDAO;
import com.neuedu.dbutil.DBManager;
import com.neuedu.entity.Courierrest;

public class CourierrestDAOImpl implements CourierrestDAO {

	private DBManager dbManager = DBManager.getInstance();

	public boolean insertCourierrest(int courierid, int restid, int userorderid) {
		// TODO Auto-generated method stub
		String sql = "insert into courierrest values(null,?,?,?,?)";
		return dbManager.execUpdate(sql, courierid, restid, 1, userorderid);
	}

	public Courierrest findCourierrest(int courierid) {
		// TODO Auto-generated method stub
		String sql = "select * from courierrest where courierid = ? and status = ?";
		ResultSet rs = dbManager.execQuery(sql, courierid, 1);

		if (rs != null) {
			try {
				if (rs.next()) { // 有数据

					Courierrest courierrest = new Courierrest();
					
					courierrest.setCourierrestid(rs.getInt(1));
					courierrest.setCourierid(rs.getInt(2));
					courierrest.setRestid(rs.getInt(3));
					courierrest.setStatus(rs.getInt(4));
					courierrest.setUserorderid(rs.getInt(5));
					
					return courierrest;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {

				// 关闭数据库连接
				dbManager.closeConnection();
			}
		}
		return null;
	}

	public boolean updateStatus(int courierrestid) {
		// TODO Auto-generated method stub
		String sql = "update courierrest set status = ? where courierrestid = ?";
		return dbManager.execUpdate(sql, 2, courierrestid);
	}

	public Courierrest findCourierrestByUser(int userorderid) {
		// TODO Auto-generated method stub
		String sql = "select * from courierrest where status = ? and userorderid = ?";
		ResultSet rs = dbManager.execQuery(sql, 2, userorderid);

		if (rs != null) {
			try {
				if (rs.next()) { // 有数据

					Courierrest courierrest = new Courierrest();
					
					courierrest.setCourierrestid(rs.getInt(1));
					courierrest.setCourierid(rs.getInt(2));
					courierrest.setRestid(rs.getInt(3));
					courierrest.setStatus(rs.getInt(4));
					courierrest.setUserorderid(rs.getInt(5));
					
					return courierrest;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {

				// 关闭数据库连接
				dbManager.closeConnection();
			}
		}
		return null;
	}


}
