package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import entity.Pet;

public class PetDAO {
	public List<Pet> getPetsByOwnerId(int ownerId) throws Exception{
		List<Pet> pets=new ArrayList<Pet>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/Pethospital",
					"root", "");
			ps = con.prepareStatement("select * from t_pet as p where p.ownerId=?");
			ps.setInt(1, ownerId);
			rs=ps.executeQuery();
			while (rs.next()) {
				Pet pet=new Pet();
				pet.setId(rs.getInt("id"));
				pet.setName(rs.getString("name"));
				pet.setBirthdate(rs.getString("birthdate"));
				pet.setOwnerId(ownerId);
				pet.setPhoto(rs.getString("photo"));
				pets.add(pet);
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
		return pets;
	}
	
	public void save(Pet pet) throws Exception{
		Connection con = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/Pethospital",
					"root", "");
			ps = con.prepareStatement("insert into t_pet value(null,?,?,?,?)");
			ps.setString(1, pet.getName());
			ps.setString(2, pet.getBirthdate());
			ps.setString(3, pet.getPhoto());
			ps.setInt(4, pet.getOwnerId());
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
	
	public void delete(int petId) throws Exception{
		Connection con = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/Pethospital",
					"root", "");
			ps = con.prepareStatement("delete from t_pet where id=?");
			ps.setInt(1, petId);
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
