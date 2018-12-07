package com.neuedu.dao.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.dao.GreensDAO;
import com.neuedu.dbutil.DBManager;
import com.neuedu.entity.Greens;
import com.neuedu.entity.Restaurant;
import com.neuedu.entity.Shoppingcar;

public class GreensDAOImpl implements GreensDAO {

	private DBManager dbManager = DBManager.getInstance();

	public boolean insertGreens(Greens greens, int restid) {
		// TODO Auto-generated method stub
		String sql = "insert into greens values(null,?,?,?,?,?,?)";

		return dbManager.execUpdate(sql, restid, greens.getGreensname(), greens.getGreensprice(),
				greens.getGreensphoto(), greens.getGreensnumber(), greens.getGreenscommentcount());
	}

	public Greens checkGreensname(String greensname, int restid) {
		// TODO Auto-generated method stub
		String sql = "select *" + " from greens" + " where greensname = ? and restid = ?";

		ResultSet rs = dbManager.execQuery(sql, greensname, restid);

		if (rs != null) {
			try {
				if (rs.next()) { // 有数据
					Greens greens = new Greens();
					Restaurant rest = new Restaurant();

					greens.setGreensid(rs.getInt(1));

					rest.setRestid(rs.getInt(2));
					greens.setRest(rest);

					greens.setGreensname(rs.getString(3));
					greens.setGreensprice(rs.getInt(4));
					greens.setGreensphoto(rs.getString(5));
					greens.setGreensnumber(rs.getInt(6));
					greens.setGreenscommentcount(rs.getInt(7));

					return greens;
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

	public boolean updateGreens(Greens greens, Greens newgreens) {
		// TODO Auto-generated method stub
		String sql = "update greens set greensprice = ?, greensphoto = ?, greensnumber = ? where greensid = ?";
		return dbManager.execUpdate(sql, newgreens.getGreensprice(), newgreens.getGreensphoto(),
				greens.getGreensnumber() + newgreens.getGreensnumber(), greens.getGreensid());
	}

	public List<Greens> findGreensList(int currentPage, int pageSize, int restid) {
		// TODO Auto-generated method stub
		String sql = "select greens.greensphoto,greens.greensname,greens.greensprice,greens.greensnumber,restaurant.restname,greens.greensid "
				+ "from greens join restaurant on greens.restid = restaurant.restid "
				+ "where greens.restid = ? order by greens.greensid limit ?, ?";

		ResultSet rs = dbManager.execQuery(sql, restid, (currentPage - 1) * pageSize, pageSize);

		List<Greens> list = new ArrayList<Greens>();

		try {
			while (rs.next()) {
				Greens greens = new Greens();

				greens.setGreensphoto(rs.getString(1));
				greens.setGreensname(rs.getString(2));
				greens.setGreensprice(rs.getInt(3));
				greens.setGreensnumber(rs.getInt(4));

				Restaurant rest = new Restaurant();
				rest.setRestname(rs.getString(5));

				greens.setRest(rest);

				greens.setGreensid(rs.getInt(6));

				list.add(greens);
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
		String sql = "select count(*) from greens where restid = ?";
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

	public int findMaxGreensid() {
		// TODO Auto-generated method stub
		String sql = "select max(greensid) from greens";

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

	public Greens findGreensByGreensid(int greensid) {
		// TODO Auto-generated method stub
		String sql = "select * from greens where greensid = ?";
		ResultSet rs = dbManager.execQuery(sql, greensid);

		if (rs != null) {
			try {
				if (rs.next()) { // 有数据
					Greens greens = new Greens();
					Restaurant rest = new Restaurant();

					greens.setGreensid(rs.getInt(1));

					rest.setRestid(rs.getInt(2));
					greens.setRest(rest);

					greens.setGreensname(rs.getString(3));
					greens.setGreensprice(rs.getInt(4));
					greens.setGreensphoto(rs.getString(5));
					greens.setGreensnumber(rs.getInt(6));
					greens.setGreenscommentcount(rs.getInt(7));

					return greens;
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

	public boolean updateGreensAll(Greens oldgreens, Greens newgreens) {
		// TODO Auto-generated method stub
		String sql = "update greens set greensname = ?, greensprice = ?, greensphoto = ?, greensnumber = ? where greensid = ?";
		return dbManager.execUpdate(sql, newgreens.getGreensname(), newgreens.getGreensprice(),
				newgreens.getGreensphoto(), newgreens.getGreensnumber(), oldgreens.getGreensid());
	}

	public boolean deleteGreensByGreensid(int greensid) {
		// TODO Auto-generated method stub
		String sql = "delete from greens where greensid = ?";

		return dbManager.execUpdate(sql, greensid);
	}

	public boolean deleteGreensBatch(String[] greensids) {
		// TODO Auto-generated method stub
		String sql = "delete from greens where greensid in (";

		StringBuilder sb = new StringBuilder(sql);

		for (int i = 0; i < greensids.length; i++) {
			sb.append("?,");
		}

		sql = sb.deleteCharAt(sb.length() - 1).append(")").toString();

		return dbManager.execUpdate(sql, greensids);
	}

	public List<Greens> findGreensList(int currentPage, int pageSize, String greensname) {
		// TODO Auto-generated method stub
		String sql = "select greens.greensphoto,greens.greensname,greens.greensprice,greens.greensnumber,restaurant.restname,greens.greensid"
					+" from greens join restaurant on greens.restid = restaurant.restid where greens.greensname like ? or restaurant.restname like ? limit ?,?";
		
		ResultSet rs = dbManager.execQuery(sql, "%" + greensname + "%", "%" + greensname + "%", (currentPage - 1) * pageSize, pageSize);

		List<Greens> list = new ArrayList<Greens>();

		try {
			while (rs.next()) {
				Greens greens = new Greens();

				greens.setGreensphoto(rs.getString(1));
				greens.setGreensname(rs.getString(2));
				greens.setGreensprice(rs.getInt(3));
				greens.setGreensnumber(rs.getInt(4));

				Restaurant rest = new Restaurant();
				rest.setRestname(rs.getString(5));

				greens.setRest(rest);

				greens.setGreensid(rs.getInt(6));

				list.add(greens);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.closeConnection();
		}

		return null;
	}

	public int findTotalCount(String greensname) {
		// TODO Auto-generated method stub
		String sql = "select count(DISTINCT greensid) from greens join restaurant on greens.restid = restaurant.restid where greens.greensname like ? or restaurant.restname like ?";
		ResultSet rs = dbManager.execQuery(sql, "%" + greensname + "%", "%" + greensname + "%");

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

	public boolean updateGreensnumber(Greens greens, Shoppingcar shoppingcar) {
		// TODO Auto-generated method stub
		String sql = "update greens set greensnumber = greensnumber - ? where greensid = ?";
		return dbManager.execUpdate(sql, shoppingcar.getCount(), greens.getGreensid());
	}

	public boolean updateGreencommentcount(int greensid) {
		// TODO Auto-generated method stub
		String sql = "update greens set greenscommentcount = greenscommentcount + ? where greensid = ?";
		return dbManager.execUpdate(sql, 1, greensid);
	}

}
