package Hospitalmangement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctors {
	private Connection connection;

	public Doctors(Connection connection) {
		this.connection = connection;
	}

	public void viewDoctors() {
		String query = "select * from doctors"; 
		try {
			PreparedStatement p = connection.prepareStatement(query);
			ResultSet set = p.executeQuery();
			int i=0;
			while (set.next()) {
				++i;
				System.out.println("Doctor Number "+i);
				String name=set.getString(2);
				System.out.println("Name Of The Doctor is-:" + name);
				String sp = set.getString(3);
				System.out.println("specialization Of The Doctor is -:" + sp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean getdoctorById(int id) {
		String q = "select * from doctors where id=?";
		try {
			PreparedStatement ps = connection.prepareStatement(q);
			ps.setInt(1, id);
			ResultSet set = ps.executeQuery();
			if (set.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
