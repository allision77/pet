package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.User;


public class UserDAO {
	public User getByName(String name) throws Exception {
		User user = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/Pethospital",
					"root", "");
			ps = con.prepareStatement("select * from t_user where name=?");
			ps.setString(1, name);
			rs=ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setRole(rs.getString("role"));
				user.setName(rs.getString("name"));
				user.setPwd(rs.getString("pwd"));
				
				System.out.println("这里！！！！！！！！！！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("数据库访问出现异常:" + e);
		} finally {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		}
		return user;
	}
	
	public User getById(int	id) throws Exception {
		User user = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/Pethospital",
					"root", "");
			ps = con.prepareStatement("select * from t_user as u where u.id=?");
			ps.setInt(1, id);
			rs=ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setRole(rs.getString("role"));
				user.setName(rs.getString("name"));
				user.setPwd(rs.getString("pwd"));
				user.setTel(rs.getString("tel"));
				user.setAddress(rs.getString("address"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("数据库访问异常:" + e);
		} finally {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		}
		return user;
	}
	
	public List<User> searchCustomer(String customerName) throws Exception{
		List<User> users=new ArrayList<User>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/Pethospital",
					"root", "");
			ps = con.prepareStatement("select * from t_user as u where u.name like ? and u.role='customer'");
			ps.setString(1, "%"+customerName+"%");
			rs=ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setRole(rs.getString("role"));
				user.setName(rs.getString("name"));
				user.setPwd(rs.getString("pwd"));
				user.setTel(rs.getString("tel"));
				user.setAddress(rs.getString("address"));
				users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("数据库访问出现异常:" + e);
		} finally {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		}
		return users;
	}
	
	public void save(User user) throws Exception{
		Connection con = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/Pethospital",
					"root", "");
			ps = con.prepareStatement("insert into t_user value(null,?,?,?,?,?)");
			ps.setString(1, user.getRole());
			ps.setString(2, user.getName());
			ps.setString(3, user.getPwd());
			ps.setString(4, user.getTel());
			ps.setString(5, user.getAddress());
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("数据库访问出现异常:" + e);
		} finally {

			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		}
	}
	

}
