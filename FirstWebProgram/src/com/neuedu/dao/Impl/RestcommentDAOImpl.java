package com.neuedu.dao.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.dao.RestcommentDAO;
import com.neuedu.dbutil.DBManager;
import com.neuedu.entity.Restcomment;

public class RestcommentDAOImpl implements RestcommentDAO {

	private DBManager dbManager = DBManager.getInstance();
	public boolean insertRestcomment(Restcomment restcomment) {
		// TODO Auto-generated method stub
		String sql = "insert into restcomment values(null,?,?,?,?,?,?)";
		return dbManager.execUpdate(sql, restcomment.getUserid(),restcomment.getRestid(),
				restcomment.getRestcommenttitle(),restcomment.getRestcommentcontext(),
				restcomment.getRestcommentpubtime(),restcomment.getScore());
	}

	public List<Restcomment> findRestcommentList(int currentPage, int pageSize, int restid) {
		// TODO Auto-generated method stub
		String sql = "select * from restcomment where restid = ? limit ?,?";

		ResultSet rs = dbManager.execQuery(sql, restid, (currentPage - 1) * pageSize, pageSize);

		List<Restcomment> list = new ArrayList<Restcomment>();
		try {
			while (rs.next()) {
				Restcomment restcomment = new Restcomment();

				restcomment.setRestcommentid(rs.getInt(1));
				restcomment.setUserid(rs.getInt(2));
				restcomment.setRestid(rs.getInt(3));
				restcomment.setRestcommenttitle(rs.getString(4));
				restcomment.setRestcommentcontext(rs.getString(5));
				restcomment.setRestcommentpubtime(rs.getTimestamp(6));
				restcomment.setScore(rs.getInt(7));

				list.add(restcomment);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.closeConnection();
		}

		return null;
	}

	public int findTotalCount(int restid) {
		// TODO Auto-generated method stub
		String sql = "select count(*) from restcomment where restid = ?";
		ResultSet rs = dbManager.execQuery(sql, restid);

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
