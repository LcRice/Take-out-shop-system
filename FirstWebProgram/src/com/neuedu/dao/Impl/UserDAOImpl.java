package com.neuedu.dao.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.dao.UserDAO;
import com.neuedu.dbutil.DBManager;
import com.neuedu.entity.Greens;
import com.neuedu.entity.Orderdetail;
import com.neuedu.entity.Ordersummary;
import com.neuedu.entity.Restaurant;
import com.neuedu.entity.User;

public class UserDAOImpl implements UserDAO {

	private DBManager dbManager = DBManager.getInstance();

	public boolean insertUser(User user) {
		// TODO Auto-generated method stub
		String sql = "insert into user values(null,?,?,?,?,?,?,?,?,?)";
		return dbManager.execUpdate(sql, "admin", user.getUsername(), user.getUsernumber(), user.getUserpassword(),
				user.getUserphoto(), user.getUsercardnumber(), user.getUsercardpassword(), user.getUseramount(),
				user.getUseraddress());
	}

	public User checkUsernumber(String usernumber) {
		// TODO Auto-generated method stub
		String sql = "select * from user where usernumber = ?";

		ResultSet rs = dbManager.execQuery(sql, usernumber);

		if (rs != null) {
			try {
				if (rs.next()) { // 有数据

					// 创建并填充实体类
					User user = new User();

					user.setUserid(rs.getInt(1));
					user.setSuperadminnumber(rs.getString(2));
					user.setUsername(rs.getString(3));
					user.setUsernumber(rs.getString(4));
					user.setUserpassword(rs.getString(5));
					user.setUserphoto(rs.getString(6));
					user.setUsercardnumber(rs.getString(7));
					user.setUsercardpassword(rs.getString(8));
					user.setUseramount(rs.getInt(9));
					user.setUseraddress(rs.getInt(10));

					return user;
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

	public User findUser(String usernumber, String userpassword) {
		// TODO Auto-generated method stub
		String sql = "select * from user where usernumber = ? and userpassword = ?";

		ResultSet rs = dbManager.execQuery(sql, usernumber,userpassword);

		if (rs != null) {
			try {
				if (rs.next()) { // 有数据

					// 创建并填充实体类
					User user = new User();

					user.setUserid(rs.getInt(1));
					user.setSuperadminnumber(rs.getString(2));
					user.setUsername(rs.getString(3));
					user.setUsernumber(rs.getString(4));
					user.setUserpassword(rs.getString(5));
					user.setUserphoto(rs.getString(6));
					user.setUsercardnumber(rs.getString(7));
					user.setUsercardpassword(rs.getString(8));
					user.setUseramount(rs.getInt(9));
					user.setUseraddress(rs.getInt(10));

					return user;
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

	public boolean updateUser(User olduser, User newuser) {
		// TODO Auto-generated method stub
		String sql = "update user set username = ?, userphoto = ?, usernumber = ?, userpassword = ?,"
				+ " usercardnumber = ?, usercardpassword = ?, useraddress = ? where userid = ?";
		return dbManager.execUpdate(sql, newuser.getUsername(),newuser.getUserphoto(),newuser.getUsernumber(),
				newuser.getUserpassword(),newuser.getUsercardnumber(),newuser.getUsercardpassword(),newuser.getUseraddress(),
				olduser.getUserid());
	}

	public boolean updateUser(User user, int price) {
		// TODO Auto-generated method stub
		String sql = "update user set useramount = useramount - ? where userid = ?";
		return dbManager.execUpdate(sql, price, user.getUserid());
	}

	public List<User> findUserList(int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		String sql = "select * from user limit ?,?";
		
		ResultSet rs = dbManager.execQuery(sql, (currentPage - 1) * pageSize, pageSize);
		
		List<User> list = new ArrayList<User>();
		
		try {
			while (rs.next()) {
				User user = new User();
				
				user.setUserid(rs.getInt(1));
				user.setSuperadminnumber(rs.getString(2));
				user.setUsername(rs.getString(3));
				user.setUsernumber(rs.getString(4));
				user.setUserpassword(rs.getString(5));
				user.setUserphoto(rs.getString(6));
				user.setUsercardnumber(rs.getString(7));
				user.setUsercardpassword(rs.getString(8));
				user.setUseramount(rs.getInt(9));
				user.setUseraddress(rs.getInt(10));
				
				list.add(user);
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
		String sql = "select count(*) from user";
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

	public User findUser(int userid) {
		// TODO Auto-generated method stub
		String sql = "select * from user where userid = ?";
		
		ResultSet rs = dbManager.execQuery(sql, userid);

		if (rs != null) {
			try {
				if (rs.next()) { // 有数据

					// 创建并填充实体类
					User user = new User();

					user.setUserid(rs.getInt(1));
					user.setSuperadminnumber(rs.getString(2));
					user.setUsername(rs.getString(3));
					user.setUsernumber(rs.getString(4));
					user.setUserpassword(rs.getString(5));
					user.setUserphoto(rs.getString(6));
					user.setUsercardnumber(rs.getString(7));
					user.setUsercardpassword(rs.getString(8));
					user.setUseramount(rs.getInt(9));
					user.setUseraddress(rs.getInt(10));

					return user;
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

	public List<Orderdetail> findOrderList(int currentPage, int pageSize, int userid) {
		// TODO Auto-generated method stub
		String sql = "select greens.greensname,restaurant.restname,orderdetail.count,orderdetail.orderstatus,t.ordertime" 
				+" from orderdetail JOIN"
				+" (select orderid,ordertime from ordersummary where userid = ?) as t"
				+" on orderdetail.orderid = t.orderid"
				+" JOIN greens ON orderdetail.greensid = greens.greensid"
				+" JOIN restaurant ON greens.restid = restaurant.restid limit ?, ?";
		
		ResultSet rs = dbManager.execQuery(sql, userid, (currentPage - 1) * pageSize, pageSize);

		List<Orderdetail> list = new ArrayList<Orderdetail>();
		
		
		try {
			while (rs.next()) {
				Orderdetail orderdetail = new Orderdetail();
				User user = new User();
				Greens greens = new Greens();
				Restaurant rest = new Restaurant();
				Ordersummary ordersummary = new Ordersummary();
				
				greens.setGreensname(rs.getString(1));
				rest.setRestname(rs.getString(2));
				greens.setRest(rest);
				
				orderdetail.setCount(rs.getInt(3));
				orderdetail.setOrderstatus(rs.getInt(4));
				ordersummary.setOrdertime(rs.getTimestamp(5));
				orderdetail.setOrdersummary(ordersummary);
				orderdetail.setGreens(greens);
				
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
		String sql = "select count(greens.greensname)" 
				+" from orderdetail JOIN"
				+" (select orderid,ordertime from ordersummary where userid = ?) as t"
				+" on orderdetail.orderid = t.orderid"
				+" JOIN greens ON orderdetail.greensid = greens.greensid"
				+" JOIN restaurant ON greens.restid = restaurant.restid";
		
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
