package com.neuedu.dao.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.neuedu.dao.SuperadminDAO;
import com.neuedu.dbutil.DBManager;
import com.neuedu.entity.Greens;
import com.neuedu.entity.Superadmin;

public class SuperadminDAOImpl implements SuperadminDAO {

	private DBManager dbManager = DBManager.getInstance();

	public boolean updateSuperamount(Greens greens) {
		// TODO Auto-generated method stub
		String sql = "update superadmin set superadminamount = superadminamount + ?";
		return dbManager.execUpdate(sql, greens.getGreensprice() * 0.1);
	}

	public Superadmin superLogin(String supernumber, String superpassword) {
		// TODO Auto-generated method stub
		String sql = "select * from superadmin where superadminnumber = ? and superadminpassword = ?";
		ResultSet rs = dbManager.execQuery(sql, supernumber, superpassword);

		if (rs != null) {
			try {
				if (rs.next()) { // 有数据
					Superadmin superadmin = new Superadmin();
					
					superadmin.setSuperadminnumber(rs.getString(1));
					superadmin.setSuperadminpassword(rs.getString(2));
					superadmin.setSuperadminamount(rs.getInt(3));
					
					return superadmin;
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
