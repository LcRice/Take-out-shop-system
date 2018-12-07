package com.neuedu.dao.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.neuedu.dao.OrdersummaryDAO;
import com.neuedu.dbutil.DBManager;
import com.neuedu.entity.Ordersummary;

public class OrdersummaryDAOImpl implements OrdersummaryDAO {

	private DBManager dbManager = DBManager.getInstance();

	public boolean insertDate(int userid, Date date) {
		// TODO Auto-generated method stub
		String sql = "insert into ordersummary values(null,?,?)";
		return dbManager.execUpdate(sql, userid, date);
	}

	public int getMaxOrderid() {
		// TODO Auto-generated method stub
		String sql = "select max(orderid) from ordersummary";
		
		ResultSet rs = dbManager.execQuery(sql);

		try {
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

}
