package com.neuedu.dao.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.dao.GreenscommentDAO;
import com.neuedu.dbutil.DBManager;
import com.neuedu.entity.Greencomment;

public class GreenscommentDAOImpl implements GreenscommentDAO {

	private DBManager dbManager = DBManager.getInstance();

	public boolean insertGreencomment(Greencomment greenscomment) {
		// TODO Auto-generated method stub
		String sql = "insert into greencomment values(null,?,?,?,?,?,?)";
		return dbManager.execUpdate(sql, greenscomment.getUserid(), greenscomment.getGreensid(),
				greenscomment.getGreencommenttitle(), greenscomment.getGreencommentcontext(),
				greenscomment.getGreencommentpubtime(), greenscomment.getScore());
	}

	public List<Greencomment> findGreenscommentList(int currentPage, int pageSize, int greensid) {
		// TODO Auto-generated method stub
		String sql = "select * from greencomment where greensid = ? limit ?,?";

		ResultSet rs = dbManager.execQuery(sql, greensid, (currentPage - 1) * pageSize, pageSize);

		List<Greencomment> list = new ArrayList<Greencomment>();
		try {
			while (rs.next()) {
				Greencomment greencomment = new Greencomment();

				greencomment.setGreencommentid(rs.getInt(1));
				greencomment.setUserid(rs.getInt(2));
				greencomment.setGreensid(rs.getInt(3));
				greencomment.setGreencommenttitle(rs.getString(4));
				greencomment.setGreencommentcontext(rs.getString(5));
				greencomment.setGreencommentpubtime(rs.getTimestamp(6));
				greencomment.setScore(rs.getInt(7));

				list.add(greencomment);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.closeConnection();
		}

		return null;
	}

	public int findTotalCount(int greensid) {
		// TODO Auto-generated method stub
		String sql = "select count(*) from greencomment where greensid = ?";
		ResultSet rs = dbManager.execQuery(sql, greensid);

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

}
