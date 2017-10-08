package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Speciality;

public class SpecialityDAO {
	public List<Speciality> getAll() throws Exception{
		List<Speciality> specs=new ArrayList<Speciality>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/Pethospital",
					"root", "");
			ps = con.prepareStatement("select * from t_speciality");
			rs=ps.executeQuery();
			while(rs.next()){
				Speciality s=new Speciality();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				specs.add(s);
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
		return specs;
	}
}
