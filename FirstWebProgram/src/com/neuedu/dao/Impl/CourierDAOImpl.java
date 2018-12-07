package com.neuedu.dao.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.dao.CourierDAO;
import com.neuedu.dbutil.DBManager;
import com.neuedu.entity.Courier;
import com.neuedu.entity.Greens;
import com.neuedu.entity.Orderdetail;
import com.neuedu.entity.Ordersummary;
import com.neuedu.entity.Restaurant;
import com.neuedu.entity.User;

public class CourierDAOImpl implements CourierDAO {

	private DBManager dbManager = DBManager.getInstance();
	public boolean insertCourier(Courier courier) {
		// TODO Auto-generated method stub
		String sql = "insert into courier values(null,?,?,?,?,?,?,?,?,?)";
		return dbManager.execUpdate(sql, "admin",courier.getCouriername(),courier.getCouriernumber(),
				courier.getCourierpassword(),courier.getCourierphoto(),courier.getCouriercamount(),
				courier.getCourierstatus(),courier.getCouriercommentcount(),courier.getCourierwellreceived());
	}

	public Courier checkCouriernumber(String couriernumber) {
		// TODO Auto-generated method stub
		String sql = "select * from courier where couriernumber = ?";

		ResultSet rs = dbManager.execQuery(sql, couriernumber);

		if (rs != null) {
			try {
				if (rs.next()) { // 有数据

					// 创建并填充实体类
					Courier courier = new Courier();

					courier.setCourierid(rs.getInt(1));
					courier.setSuperadminnumber(rs.getString(2));
					courier.setCouriername(rs.getString(3));
					courier.setCouriernumber(rs.getString(4));
					courier.setCourierpassword(rs.getString(5));
					courier.setCourierphoto(rs.getString(6));
					courier.setCouriercamount(rs.getInt(7));
					courier.setCourierstatus(rs.getString(8));
					courier.setCouriercommentcount(rs.getInt(9));
					courier.setCourierwellreceived(rs.getInt(10));

					return courier;
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

	public List<Courier> findCourierList(int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		String sql = "select * FROM courier where courierstatus = '空闲' limit ?,?";
		
		ResultSet rs = dbManager.execQuery(sql, (currentPage - 1) * pageSize, pageSize);
		
		List<Courier> list = new ArrayList<Courier>();
		
		try {
			while (rs.next()) {
				Courier courier = new Courier();
				
				courier.setCourierid(rs.getInt(1));
				courier.setSuperadminnumber(rs.getString(2));
				courier.setCouriername(rs.getString(3));
				courier.setCouriernumber(rs.getString(4));
				courier.setCourierpassword(rs.getString(5));
				courier.setCourierphoto(rs.getString(6));
				courier.setCouriercamount(rs.getInt(7));
				courier.setCourierstatus(rs.getString(8));
				courier.setCouriercommentcount(rs.getInt(9));
				courier.setCourierwellreceived(rs.getInt(10));
				
				list.add(courier);
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
		String sql = "select count(*) FROM courier where courierstatus = '空闲'";
		
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

	public boolean updateCourierstatus(String courierstatus,int courierid) {
		// TODO Auto-generated method stub
		String sql = "update courier set courierstatus = ? where courierid = ?"; 
		return dbManager.execUpdate(sql, courierstatus, courierid);
	}

	public boolean updateCourieramount(int courierid) {
		// TODO Auto-generated method stub
		String sql = "update courier set couriercamount = couriercamount + ? where courierid = ?";
		return dbManager.execUpdate(sql, 5, courierid);
	}

	public Courier findCourier(String couriernumber, String courierpassword) {
		// TODO Auto-generated method stub
		String sql = "select * from courier where couriernumber = ? and courierpassword = ?";
		ResultSet rs = dbManager.execQuery(sql, couriernumber,courierpassword);

		if (rs != null) {
			try {
				if (rs.next()) { // 有数据

					// 创建并填充实体类
					Courier courier = new Courier();

					courier.setCourierid(rs.getInt(1));
					courier.setSuperadminnumber(rs.getString(2));
					courier.setCouriername(rs.getString(3));
					courier.setCouriernumber(rs.getString(4));
					courier.setCourierpassword(rs.getString(5));
					courier.setCourierphoto(rs.getString(6));
					courier.setCouriercamount(rs.getInt(7));
					courier.setCourierstatus(rs.getString(8));
					courier.setCouriercommentcount(rs.getInt(9));
					courier.setCourierwellreceived(rs.getInt(10));

					return courier;
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

	public boolean updateCourierWellReceived(int courierid) {
		// TODO Auto-generated method stub
		String sql = "update courier set courierwellreceived = courierwellreceived + ? where courierid = ?";
		return dbManager.execUpdate(sql, 1, courierid);
	}

	public boolean updateCouriercomment(int courierid) {
		// TODO Auto-generated method stub
		String sql = "update courier set couriercommentcount = couriercommentcount + ? where courierid = ?";
		return dbManager.execUpdate(sql, 1, courierid);
	}

	public Courier findCourier(int courierid) {
		// TODO Auto-generated method stub
		String sql = "select * from courier where courierid = ?";
		
		ResultSet rs = dbManager.execQuery(sql, courierid);

		if (rs != null) {
			try {
				if (rs.next()) { // 有数据

					// 创建并填充实体类
					Courier courier = new Courier();

					courier.setCourierid(rs.getInt(1));
					courier.setSuperadminnumber(rs.getString(2));
					courier.setCouriername(rs.getString(3));
					courier.setCouriernumber(rs.getString(4));
					courier.setCourierpassword(rs.getString(5));
					courier.setCourierphoto(rs.getString(6));
					courier.setCouriercamount(rs.getInt(7));
					courier.setCourierstatus(rs.getString(8));
					courier.setCouriercommentcount(rs.getInt(9));
					courier.setCourierwellreceived(rs.getInt(10));

					return courier;
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

	public List<Orderdetail> findCourierOrderlist(int currentPage, int pageSize, int courierid) {
		// TODO Auto-generated method stub
		String sql = "SELECT tttt.couriername,tttt.greensname,restaurant.restname,`user`.username,tttt.orderstatus,tttt.userorderid,tttt.ordertime from restaurant JOIN"
				+" (SELECT ttt.couriername,greens.greensname,greens.restid,ttt.userid,ttt.orderstatus,ttt.userorderid,ttt.ordertime from greens JOIN"
				+" (SELECT tt.couriername,tt.greensid,ordersummary.userid,tt.orderstatus,tt.userorderid,ordersummary.ordertime FROM ordersummary JOIN" 
				+" (select t.couriername,orderdetail.greensid,orderdetail.orderid,orderdetail.orderstatus,orderdetail.userorderid from orderdetail JOIN"
				+" (select courier.couriername,courierrest.userorderid" 
				+" from courierrest JOIN courier" 
				+" on courier.courierid = courierrest.courierid and courier.courierid = ?) as t"
				+" on orderdetail.userorderid = t.userorderid) as tt"	
				+" on ordersummary.orderid = tt.orderid) as ttt"
				+" on greens.greensid = ttt.greensid) as tttt"
				+" on restaurant.restid = tttt.restid"
				+" JOIN user on `user`.userid = tttt.userid limit ?,?";
		
		ResultSet rs = dbManager.execQuery(sql, courierid, (currentPage - 1) * pageSize, pageSize);

		List<Orderdetail> list = new ArrayList<Orderdetail>();

		try {
			while (rs.next()) {
				Orderdetail orderdetail = new Orderdetail();
				Courier courier = new Courier();
				User user = new User();
				Greens greens = new Greens();
				Restaurant rest = new Restaurant();
				Ordersummary ordersummary = new Ordersummary();
				
				courier.setCouriername(rs.getString(1));
				rest.setCourier(courier);
				
				greens.setGreensname(rs.getString(2));
				rest.setRestname(rs.getString(3));
				
				greens.setRest(rest);
				
				user.setUsername(rs.getString(4));
				ordersummary.setOrdertime(rs.getTimestamp(7));
				ordersummary.setUser(user);
				orderdetail.setOrdersummary(ordersummary);
				
				orderdetail.setOrderstatus(rs.getInt(5));
				orderdetail.setGreens(greens);
				
				orderdetail.setUserorderid(rs.getInt(6));
				
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

	public int findTotalCount(int courierid) {
		// TODO Auto-generated method stub
		String sql = "SELECT count(tttt.couriername) from restaurant JOIN"
				+" (SELECT ttt.couriername,greens.greensname,greens.restid,ttt.userid,ttt.orderstatus,ttt.userorderid,ttt.ordertime from greens JOIN"
				+" (SELECT tt.couriername,tt.greensid,ordersummary.userid,tt.orderstatus,tt.userorderid,ordersummary.ordertime FROM ordersummary JOIN" 
				+" (select t.couriername,orderdetail.greensid,orderdetail.orderid,orderdetail.orderstatus,orderdetail.userorderid from orderdetail JOIN"
				+" (select courier.couriername,courierrest.userorderid" 
				+" from courierrest JOIN courier" 
				+" on courier.courierid = courierrest.courierid and courier.courierid=?) as t"
				+" on orderdetail.userorderid = t.userorderid) as tt"	
				+" on ordersummary.orderid = tt.orderid) as ttt"
				+" on greens.greensid = ttt.greensid) as tttt"
				+" on restaurant.restid = tttt.restid"
				+" JOIN user on `user`.userid = tttt.userid";
		
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

	public Courier findMAXCourier() {
		// TODO Auto-generated method stub
		String sql = "select * from courier where courierstatus=? ORDER BY courierwellreceived DESC limit ?,?";
		
		ResultSet rs = dbManager.execQuery(sql, "空闲", 0, 1);

		if (rs != null) {
			try {
				if (rs.next()) { // 有数据

					// 创建并填充实体类
					Courier courier = new Courier();

					courier.setCourierid(rs.getInt(1));
					courier.setSuperadminnumber(rs.getString(2));
					courier.setCouriername(rs.getString(3));
					courier.setCouriernumber(rs.getString(4));
					courier.setCourierpassword(rs.getString(5));
					courier.setCourierphoto(rs.getString(6));
					courier.setCouriercamount(rs.getInt(7));
					courier.setCourierstatus(rs.getString(8));
					courier.setCouriercommentcount(rs.getInt(9));
					courier.setCourierwellreceived(rs.getInt(10));

					return courier;
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
