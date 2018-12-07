package com.neuedu.dao.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.dao.CouriercommentDAO;
import com.neuedu.dbutil.DBManager;
import com.neuedu.entity.Couriercomment;

public class CouriercommentDAOImpl implements CouriercommentDAO {

	private DBManager dbManager = DBManager.getInstance();
	public List<Couriercomment> findCourierCommentList(int currentPage, int pageSize, int courierid) {
		// TODO Auto-generated method stub
		String sql = "select * from couriercomment where courierid = ? limit ?,?";
		
		ResultSet rs = dbManager.execQuery(sql, courierid, (currentPage - 1) * pageSize, pageSize);
		
		List<Couriercomment> list = new ArrayList<Couriercomment>();
		try {
			while (rs.next()) {
				Couriercomment couriercomment = new Couriercomment();
				
				couriercomment.setCouriercommentid(rs.getInt(1));
				couriercomment.setUserid(rs.getInt(2));
				couriercomment.setCourierid(rs.getInt(3));
				couriercomment.setCouriercommenttitle(rs.getString(4));
				couriercomment.setCouriercommentcontext(rs.getString(5));
				couriercomment.setCouriercommentpubtime(rs.getTimestamp(6));
				couriercomment.setScore(rs.getInt(7));
				
				list.add(couriercomment);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.closeConnection();
		}

		return null;
	}

	public int findTotalCount(int courierid) {
		// TODO Auto-generated method stub
		String sql = "select count(*) from couriercomment where courierid = ?";
		ResultSet rs = dbManager.execQuery(sql, courierid);

		try {
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.closeConnection();
		}

		return -1;
	}

	public boolean insertCouriercomment(Couriercomment couriercomment) {
		// TODO Auto-generated method stub
		String sql = "insert into couriercomment values(null,?,?,?,?,?,?)";
		return dbManager.execUpdate(sql, couriercomment.getUserid(),couriercomment.getCourierid(),
				couriercomment.getCouriercommenttitle(),couriercomment.getCouriercommentcontext(),
				couriercomment.getCouriercommentpubtime(),couriercomment.getScore());
	}

}
