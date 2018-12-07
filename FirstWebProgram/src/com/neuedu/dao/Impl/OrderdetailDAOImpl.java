package com.neuedu.dao.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.dao.OrderdetailDAO;
import com.neuedu.dbutil.DBManager;
import com.neuedu.entity.Courier;
import com.neuedu.entity.Greens;
import com.neuedu.entity.Orderdetail;
import com.neuedu.entity.Ordersummary;
import com.neuedu.entity.Restaurant;
import com.neuedu.entity.User;

public class OrderdetailDAOImpl implements OrderdetailDAO {

	private DBManager dbManager = DBManager.getInstance();

	public boolean insertDate(int greensid, int orderid, int count) {
		// TODO Auto-generated method stub
		String sql = "insert into orderdetail values(null,?,?,?,?)";
		return dbManager.execUpdate(sql, greensid, orderid, count, 1);
	}

	public Orderdetail findOrderdetail(int userorderid) {
		// TODO Auto-generated method stub
		String sql = "select * from orderdetail where userorderid = ?";

		ResultSet rs = dbManager.execQuery(sql, userorderid);

		if (rs != null) {
			try {
				if (rs.next()) { // 有数据
					Orderdetail orderdetail = new Orderdetail();
					
					orderdetail.setUserorderid(rs.getInt(1));
					orderdetail.setGreensid(rs.getInt(2));
					orderdetail.setOrderid(rs.getInt(3));
					orderdetail.setCount(rs.getInt(4));
					
					return orderdetail;
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

	public boolean updateOrderstatus(int userorderid, int orderstatus) {
		// TODO Auto-generated method stub
		String sql = "update orderdetail set orderstatus = ? where userorderid = ?";
		return dbManager.execUpdate(sql, orderstatus, userorderid);
	}

	public List<Orderdetail> findOrderdetailListByUser(int currentPage, int pageSize, int userid) {
		// TODO Auto-generated method stub
		String sql = "SELECT tttt.couriername,tttt.greensname,restaurant.restname,tttt.orderstatus,tttt.userorderid from restaurant JOIN"
				+" (SELECT ttt.couriername,greens.greensname,greens.restid,ttt.orderstatus,ttt.userorderid from greens JOIN"
				+" (SELECT tt.couriername,tt.greensid,tt.orderstatus,tt.userorderid FROM ordersummary JOIN" 
				+" (select t.couriername,orderdetail.greensid,orderdetail.orderid,orderdetail.orderstatus,orderdetail.userorderid from orderdetail JOIN"
				+" (select courier.couriername,courierrest.userorderid" 
				+" from courierrest JOIN courier" 
				+" on courier.courierid = courierrest.courierid) as t"
				+" on orderdetail.userorderid = t.userorderid and orderdetail.orderstatus in (1,2,3)) as tt"
				+" on ordersummary.orderid = tt.orderid and userid = ?) as ttt"
				+" on greens.greensid = ttt.greensid) as tttt"
				+" on restaurant.restid = tttt.restid limit ?,?";
		
		ResultSet rs = dbManager.execQuery(sql, userid, (currentPage - 1) * pageSize, pageSize);

		List<Orderdetail> list = new ArrayList<Orderdetail>();

		try {
			while (rs.next()) {
				Orderdetail orderdetail = new Orderdetail();
				Courier courier = new Courier();
				Greens greens = new Greens();
				Restaurant rest = new Restaurant();
				
				courier.setCouriername(rs.getString(1));
				rest.setCourier(courier);
				
				greens.setGreensname(rs.getString(2));
				rest.setRestname(rs.getString(3));
				
				greens.setRest(rest);
				
				orderdetail.setOrderstatus(rs.getInt(4));
				orderdetail.setGreens(greens);
				
				orderdetail.setUserorderid(rs.getInt(5));
				
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

	public int findTotalCount(int userid) {
		// TODO Auto-generated method stub
		String sql = "SELECT count(tt.couriername) FROM ordersummary JOIN"
				+" (select t.couriername,orderdetail.greensid,orderdetail.orderid,orderdetail.orderstatus from orderdetail JOIN"
				+" (select courier.couriername,courierrest.userorderid" 
				+" from courierrest JOIN courier" 
				+" on courier.courierid = courierrest.courierid) as t"
				+" on orderdetail.userorderid = t.userorderid and orderdetail.orderstatus in (1,2,3)) as tt"
				+" on ordersummary.orderid = tt.orderid and userid = ?";
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

	public List<Orderdetail> findFinishedOrderListByUser(int currentPage, int pageSize, int userid) {
		// TODO Auto-generated method stub
		String sql = "SELECT tttt.couriername,tttt.greensname,restaurant.restname,tttt.orderstatus,tttt.userorderid from restaurant JOIN"
				+" (SELECT ttt.couriername,greens.greensname,greens.restid,ttt.orderstatus,ttt.userorderid from greens JOIN"
				+" (SELECT tt.couriername,tt.greensid,tt.orderstatus,tt.userorderid FROM ordersummary JOIN" 
				+" (select t.couriername,orderdetail.greensid,orderdetail.orderid,orderdetail.orderstatus,orderdetail.userorderid from orderdetail JOIN"
				+" (select courier.couriername,courierrest.userorderid" 
				+" from courierrest JOIN courier" 
				+" on courier.courierid = courierrest.courierid) as t"
				+" on orderdetail.userorderid = t.userorderid and orderdetail.orderstatus = 4) as tt"
				+" on ordersummary.orderid = tt.orderid and userid = ?) as ttt"
				+" on greens.greensid = ttt.greensid) as tttt"
				+" on restaurant.restid = tttt.restid limit ?,?";
		
		ResultSet rs = dbManager.execQuery(sql, userid, (currentPage - 1) * pageSize, pageSize);

		List<Orderdetail> list = new ArrayList<Orderdetail>();

		try {
			while (rs.next()) {
				Orderdetail orderdetail = new Orderdetail();
				Courier courier = new Courier();
				Greens greens = new Greens();
				Restaurant rest = new Restaurant();
				
				courier.setCouriername(rs.getString(1));
				rest.setCourier(courier);
				
				greens.setGreensname(rs.getString(2));
				rest.setRestname(rs.getString(3));
				
				greens.setRest(rest);
				
				orderdetail.setOrderstatus(rs.getInt(4));
				orderdetail.setGreens(greens);
				
				orderdetail.setUserorderid(rs.getInt(5));
				
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

	public int findFinishedTotalCount(int userid) {
		// TODO Auto-generated method stub
		String sql = "SELECT count(tt.couriername) FROM ordersummary JOIN"
				+" (select t.couriername,orderdetail.greensid,orderdetail.orderid,orderdetail.orderstatus from orderdetail JOIN"
				+" (select courier.couriername,courierrest.userorderid" 
				+" from courierrest JOIN courier" 
				+" on courier.courierid = courierrest.courierid) as t"
				+" on orderdetail.userorderid = t.userorderid and orderdetail.orderstatus = 4) as tt"
				+" on ordersummary.orderid = tt.orderid and userid = ?";
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

	public List<Orderdetail> findOrderdetailListByRest(int currentPage, int pageSize, int restid) {
		// TODO Auto-generated method stub
		String sql = "select `user`.username,greens.greensname,t.count,t.orderstatus,ordersummary.ordertime from ordersummary JOIN"
				+" (select orderdetail.greensid,orderdetail.orderid,orderdetail.count,orderdetail.orderstatus" 
				+" from orderdetail" 
				+" where orderdetail.greensid in"
				+" (select greensid from greens where restid = ?)) as t" 
				+" on ordersummary.orderid = t.orderid" 
				+" JOIN user on ordersummary.userid=`user`.userid"
				+" JOIN greens on t.greensid = greens.greensid limit ?,?";
		
		ResultSet rs = dbManager.execQuery(sql, restid, (currentPage - 1) * pageSize, pageSize);

		List<Orderdetail> list = new ArrayList<Orderdetail>();

		try {
			while (rs.next()) {
				Orderdetail orderdetail = new Orderdetail();
				Ordersummary ordersummary = new Ordersummary();
				Greens greens = new Greens();
				User user = new User();
				
				user.setUsername(rs.getString(1));
				ordersummary.setUser(user);
				
				greens.setGreensname(rs.getString(2));
				orderdetail.setGreens(greens);
				
				orderdetail.setCount(rs.getInt(3));
				orderdetail.setOrderstatus(rs.getInt(4));
				
				ordersummary.setOrdertime(rs.getTimestamp(5));
				orderdetail.setOrdersummary(ordersummary);
				
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

	public int findRestTotalCount(int restid) {
		// TODO Auto-generated method stub
		String sql = "select count(`user`.username) from ordersummary JOIN"
				+" (select orderdetail.greensid,orderdetail.orderid,orderdetail.count,orderdetail.orderstatus" 
				+" from orderdetail" 
				+" where orderdetail.greensid in"
				+" (select greensid from greens where restid = ?)) as t" 
				+" on ordersummary.orderid = t.orderid" 
				+" JOIN user on ordersummary.userid=`user`.userid"
				+" JOIN greens on t.greensid = greens.greensid";
		
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

	public List<Orderdetail> findUnfinishedOrderListByRest(int restid) {
		// TODO Auto-generated method stub
		String sql = "select greens.greensname,`user`.username,orderdetail.count,ordersummary.ordertime,orderdetail.userorderid" 
				+" from orderdetail JOIN greens ON greens.greensid = orderdetail.greensid"
				+" JOIN ordersummary ON orderdetail.orderid = ordersummary.orderid"
				+" JOIN `user` ON `user`.userid = ordersummary.userid"
				+" JOIN restaurant ON greens.restid = restaurant.restid and restaurant.restid=?"
				+" where orderdetail.orderstatus = ?";
		
		ResultSet rs = dbManager.execQuery(sql, restid, 1);

		List<Orderdetail> list = new ArrayList<Orderdetail>();

		try {
			while (rs.next()) {
				Orderdetail orderdetail = new Orderdetail();
				Ordersummary ordersummary = new Ordersummary();
				Greens greens = new Greens();
				User user = new User();
				
				user.setUsername(rs.getString(2));
				ordersummary.setUser(user);
				
				greens.setGreensname(rs.getString(1));
				orderdetail.setGreens(greens);
				
				orderdetail.setCount(rs.getInt(3));
				
				ordersummary.setOrdertime(rs.getTimestamp(4));
				orderdetail.setOrdersummary(ordersummary);
				orderdetail.setUserorderid(rs.getInt(5));
				
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

}