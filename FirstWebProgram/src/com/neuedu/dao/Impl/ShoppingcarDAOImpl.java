package com.neuedu.dao.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.dao.ShoppingcarDAO;
import com.neuedu.dbutil.DBManager;
import com.neuedu.entity.Greens;
import com.neuedu.entity.Restaurant;
import com.neuedu.entity.Shoppingcar;
import com.neuedu.entity.User;

public class ShoppingcarDAOImpl implements ShoppingcarDAO {

	private DBManager dbManager = DBManager.getInstance();

	public boolean insertShopping(User user, Greens greens) {
		// TODO Auto-generated method stub
		String sql = "insert into shoppingcar values(null,?,?,?)";
		return dbManager.execUpdate(sql, user.getUserid(), greens.getGreensid(), 1);
	}

	public Shoppingcar checkShopping(User user, Greens greens) {
		// TODO Auto-generated method stub
		String sql = "select * from shoppingcar where userid = ? and greensid = ?";
		ResultSet rs = dbManager.execQuery(sql, user.getUserid(), greens.getGreensid());

		if (rs != null) {
			try {
				if (rs.next()) { // 有数据

					// 创建并填充实体类
					Shoppingcar shoppingcar = new Shoppingcar();

					shoppingcar.setShoppingcarid(rs.getInt(1));
					shoppingcar.setUserid(rs.getInt(2));
					shoppingcar.setGreensid(rs.getInt(3));
					shoppingcar.setCount(rs.getInt(4));

					return shoppingcar;
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

	public boolean updateShopping(User user, Greens greens) {
		// TODO Auto-generated method stub
		String sql = "update shoppingcar set count = count + 1 where userid = ? and greensid = ?";

		return dbManager.execUpdate(sql, user.getUserid(), greens.getGreensid());
	}

	public boolean deleteShopping(User user, Greens greens) {
		// TODO Auto-generated method stub
		String sql = "delete from shoppingcar where userid = ? and greensid = ?";
		return dbManager.execUpdate(sql, user.getUserid(), greens.getGreensid());
	}

	public List<Shoppingcar> findShoppingList(int currentPage, int pageSize, int userid) {
		// TODO Auto-generated method stub
		String sql = "select greens.greensphoto,greens.greensname,greens.greensprice,restaurant.restname,greens.greensid,shoppingcar.count"
				+ " from shoppingcar join greens on shoppingcar.greensid = greens.greensid join restaurant on greens.restid = restaurant.restid"
				+ " where userid = ? limit ?,?";

		ResultSet rs = dbManager.execQuery(sql, userid, (currentPage - 1) * pageSize, pageSize);

		List<Shoppingcar> list = new ArrayList<Shoppingcar>();

		try {
			while (rs.next()) {
				Shoppingcar shoppingcar = new Shoppingcar();
				Greens greens = new Greens();
				Restaurant rest = new Restaurant();

				greens.setGreensphoto(rs.getString(1));
				greens.setGreensname(rs.getString(2));
				greens.setGreensprice(rs.getInt(3));
				greens.setGreensid(rs.getInt(5));
				shoppingcar.setGreens(greens);

				rest.setRestname(rs.getString(4));
				shoppingcar.setRest(rest);
				
				shoppingcar.setCount(rs.getInt(6));

				list.add(shoppingcar);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.closeConnection();
		}
		return null;
	}

	public int findTotalCount(int userid) {
		// TODO Auto-generated method stub
		String sql = "select count(*) from shoppingcar where userid = ?";
		ResultSet rs = dbManager.execQuery(sql, userid);

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
