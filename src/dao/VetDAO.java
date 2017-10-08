package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Speciality;
import entity.Vet;

public class VetDAO {
	public void save(Vet vet) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/Pethospital",
					"root", "");
			con.setAutoCommit(false);
			String sql = "insert into t_vet value(null,?)";
			ps = con.prepareStatement(sql,
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, vet.getName());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				vet.setId(rs.getInt(1));
			}
			sql = "insert into t_vet_speciality values";
			boolean first = true;
			for (Speciality spec : vet.getSpecs()) {
				if (first) {
					sql += "(" + vet.getId() + "," + spec.getId() + ")";
					first = false;
				} else {
					sql += ",(" + vet.getId() + "," + spec.getId() + ")";
				}
			}
			ps.executeUpdate(sql);
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (con != null)
				con.rollback();
			throw new Exception("数据库访问出现异常:" + e);
		} finally {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		}
	}

	public List<Vet> search(String vetName, String specName) throws Exception {
		List<Vet> vets = new ArrayList<Vet>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/Pethospital",
					"root", "");
			con.setAutoCommit(false);
			ps = con.prepareStatement("SELECT distinct  t_vet.* FROM    t_vet_speciality    "
					+ "INNER JOIN t_speciality ON (t_vet_speciality.specId = t_speciality.id)   "
					+ "INNER JOIN t_vet  ON (t_vet_speciality.vetId = t_vet.id) "
					+ "where t_vet.name like  ? and t_speciality.name like ?");
			ps.setString(1, "%"+vetName+"%");
			ps.setString(2, "%"+specName+"%");
			rs=ps.executeQuery();
			while(rs.next()){
				Vet v=new Vet();
				v.setId(rs.getInt("id"));
				v.setName(rs.getString("name"));
				vets.add(v);
			}
			
			for(Vet v:vets){
				rs=ps.executeQuery("SELECT t_speciality.* FROM    t_vet_speciality    "
						+ "INNER JOIN t_speciality ON (t_vet_speciality.specId = t_speciality.id)   "
						+ "INNER JOIN t_vet  ON (t_vet_speciality.vetId = t_vet.id) "
						+ "where t_vet.id =  "+v.getId());
				
				while(rs.next()){
					Speciality spec=new Speciality();
					spec.setId(rs.getInt("id"));
					spec.setName(rs.getString("name"));
					v.getSpecs().add(spec);
				}
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
		return vets;
	}

	public List<Vet> getAll() throws Exception {
		List<Vet> vets = new ArrayList<Vet>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/Pethospital",
					"root", "");
			ps=con.prepareStatement("select * from t_vet ");
			rs=ps.executeQuery();
			while(rs.next()){
				Vet v=new Vet();
				v.setId(rs.getInt("id"));
				v.setName(rs.getString("name"));
				vets.add(v);
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
		return vets;
	} 
}
