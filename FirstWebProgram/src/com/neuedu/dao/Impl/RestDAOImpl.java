package com.neuedu.dao.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.dao.RestDAO;
import com.neuedu.dbutil.DBManager;
import com.neuedu.entity.Greens;
import com.neuedu.entity.Orderdetail;
import com.neuedu.entity.Ordersummary;
import com.neuedu.entity.Restaurant;
import com.neuedu.entity.User;

public class RestDAOImpl implements RestDAO {

	private DBManager dbManager = DBManager.getInstance();

	public boolean insertRest(Restaurant rest) {
		// TODO Auto-generated method stub
		String sql = "insert into restaurant values(null,?,?,?,?,?,?,?,?,?,?)";
		return dbManager.execUpdate(sql, "admin", rest.getRestname(), rest.getRestnumber(), rest.getRestpassword(),
				rest.getRestphoto(), rest.getRestcardnumber(), rest.getRestcardpassword(), rest.getRestamount(),
				rest.getRestaddress(), 0);
	}

	public Restaurant checkRestnumber(String restnumber) {
		// TODO Auto-generated method stub
		String sql = "select * from restaurant where restnumber = ?";

		ResultSet rs = dbManager.execQuery(sql, restnumber);

		if (rs != null) {
			try {
				if (rs.next()) { // 有数据

					// 创建并填充实体类
					Restaurant rest = new Restaurant();

					rest.setRestid(rs.getInt(1));
					rest.setSuperadminnumber(rs.getString(2));
					rest.setRestname(rs.getString(3));
					rest.setRestnumber(rs.getString(4));
					rest.setRestpassword(rs.getString(5));
					rest.setRestphoto(rs.getString(6));
					rest.setRestcardnumber(rs.getString(7));
					rest.setRestcardpassword(rs.getString(8));
					rest.setRestamount(rs.getInt(9));
					rest.setRestaddress(rs.getInt(10));
					rest.setRestcommentcount(rs.getInt(11));

					return rest;
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

	public Restaurant findRest(String restnumber, String restpassword) {
		// TODO Auto-generated method stub
		String sql = "select * from restaurant where restnumber = ? and restpassword = ?";

		ResultSet rs = dbManager.execQuery(sql, restnumber,restpassword);

		if (rs != null) {
			try {
				if (rs.next()) { // 有数据

					// 创建并填充实体类
					Restaurant rest = new Restaurant();
					
					rest.setRestid(rs.getInt(1));
					rest.setSuperadminnumber(rs.getString(2));
					rest.setRestname(rs.getString(3));
					rest.setRestnumber(rs.getString(4));
					rest.setRestpassword(rs.getString(5));
					rest.setRestphoto(rs.getString(6));
					rest.setRestcardnumber(rs.getString(7));
					rest.setRestcardpassword(rs.getString(8));
					rest.setRestamount(rs.getInt(9));
					rest.setRestaddress(rs.getInt(10));
					rest.setRestcommentcount(rs.getInt(11));

					return rest;
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

	public boolean updateRest(Restaurant oldrest, Restaurant newrest) {
		// TODO Auto-generated method stub
		String sql = "update restaurant set restname = ?, restphoto = ?,"
				+ " restnumber = ?, restpassword = ?, restcardnumber = ?, restcardpassword = ?,"
				+ " restaddress = ? where restid = ?";
		return dbManager.execUpdate(sql, newrest.getRestname(),newrest.getRestphoto(),
				newrest.getRestnumber(),newrest.getRestpassword(),newrest.getRestcardnumber(),
				newrest.getRestcardpassword(),newrest.getRestaddress(),oldrest.getRestid());
	}

	public boolean updateRestamount(Greens greens) {
		// TODO Auto-generated method stub
		String sql = "update restaurant set restamount = restamount + ? where restid = ?";
		return dbManager.execUpdate(sql, greens.getGreensprice()*0.9, greens.getRest().getRestid());
	}

	public List<Orderdetail> findOrderList(int currentPage, int pageSize,int restid) {
		// TODO Auto-generated method stub
		String sql = "select `user`.username,tt.greensname,tt.restid,tt.count,tt.ordertime,tt.orderstatus,tt.userorderid,`user`.useraddress from user,"
					+" (SELECT t.userid,t.greensid,greens.greensname,greens.restid,t.count,t.ordertime,t.orderstatus,t.userorderid from greens,"
					+" (select ordersummary.userid,orderdetail.greensid,orderdetail.count,ordersummary.ordertime,orderdetail.orderstatus,orderdetail.userorderid"
					+" from orderdetail join ordersummary on orderdetail.orderid = ordersummary.orderid) as t"
					+" where greens.greensid = t.greensid and greens.restid = ?) as tt"
					+" where `user`.userid = tt.userid limit ?,? "; 
		
		ResultSet rs = dbManager.execQuery(sql, restid, (currentPage - 1) * pageSize, pageSize);

		List<Orderdetail> list = new ArrayList<Orderdetail>();
		
		
		try {
			while (rs.next()) {
				Orderdetail orderdetail = new Orderdetail();
				User user = new User();
				Greens greens = new Greens();
				Restaurant rest = new Restaurant();
				Ordersummary ordersummary = new Ordersummary();
				
				user.setUsername(rs.getString(1));
				user.setUseraddress(rs.getInt(8));
				ordersummary.setUser(user);
				
				greens.setGreensname(rs.getString(2));
				rest.setRestid(rs.getInt(3));
				greens.setRest(rest);
				orderdetail.setGreens(greens);
				
				orderdetail.setCount(rs.getInt(4));
				
				ordersummary.setOrdertime(rs.getTimestamp(5));
				orderdetail.setOrderstatus(rs.getInt(6));
				orderdetail.setOrdersummary(ordersummary);
				
				orderdetail.setUserorderid(rs.getInt(7));
				
				list.add(orderdetail);
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
		String sql = "select count(`user`.username) from user,"
				+" (SELECT t.userid,t.greensid,greens.greensname,greens.restid,t.count,t.ordertime,t.orderstatus from greens,"
				+" (select ordersummary.userid,orderdetail.greensid,orderdetail.count,ordersummary.ordertime,orderdetail.orderstatus"
				+" from orderdetail join ordersummary on orderdetail.orderid = ordersummary.orderid) as t"
				+" where greens.greensid = t.greensid and greens.restid = ?) as tt"
				+" where `user`.userid = tt.userid";
		
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

	public boolean updateRestcommentcount(int restid) {
		// TODO Auto-generated method stub
		String sql = "update restaurant set restcommentcount = restcommentcount + ? where restid = ?";
		return dbManager.execUpdate(sql, 1, restid);
	}

	public List<Restaurant> findRestList(int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		String sql = "select * from restaurant limit ?,?";
		ResultSet rs = dbManager.execQuery(sql, (currentPage - 1) * pageSize, pageSize);

		List<Restaurant> list = new ArrayList<Restaurant>();
		
		
		try {
			while (rs.next()) {
				Restaurant restaurant = new Restaurant();
				
				restaurant.setRestid(rs.getInt(1));
				restaurant.setSuperadminnumber(rs.getString(2));
				restaurant.setRestname(rs.getString(3));
				restaurant.setRestnumber(rs.getString(4));
				restaurant.setRestpassword(rs.getString(5));
				restaurant.setRestphoto(rs.getString(6));
				restaurant.setRestcardnumber(rs.getString(7));
				restaurant.setRestcardpassword(rs.getString(8));
				restaurant.setRestamount(rs.getInt(9));
				restaurant.setRestaddress(rs.getInt(10));
				restaurant.setRestcommentcount(rs.getInt(11));
				
				list.add(restaurant);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.closeConnection();
		}

		return null;
	}

	public int findTotalCount() {
		// TODO Auto-generated method stub
		String sql = "select count(*) from restaurant";
		ResultSet rs = dbManager.execQuery(sql);

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

	public Restaurant findRest(int restid) {
		// TODO Auto-generated method stub
		String sql = "select * from restaurant where restid = ?";
		ResultSet rs = dbManager.execQuery(sql, restid);

		if (rs != null) {
			try {
				if (rs.next()) { // 有数据

					// 创建并填充实体类
					Restaurant rest = new Restaurant();

					rest.setRestid(rs.getInt(1));
					rest.setSuperadminnumber(rs.getString(2));
					rest.setRestname(rs.getString(3));
					rest.setRestnumber(rs.getString(4));
					rest.setRestpassword(rs.getString(5));
					rest.setRestphoto(rs.getString(6));
					rest.setRestcardnumber(rs.getString(7));
					rest.setRestcardpassword(rs.getString(8));
					rest.setRestamount(rs.getInt(9));
					rest.setRestaddress(rs.getInt(10));
					rest.setRestcommentcount(rs.getInt(11));

					return rest;
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
