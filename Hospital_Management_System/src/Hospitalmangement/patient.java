package Hospitalmangement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class patient {
	private Connection connection;
	private Scanner scanner;

	public patient(Connection connection, Scanner scanner) {
		this.connection = connection;
		this.scanner = scanner;
	}

	public void addpatient() {
		System.out.println("Enter Patient Name!");
		String name = scanner.next();
		System.out.println("Enter Patient Age");
		int age = scanner.nextInt();
		System.out.println("Enter Patient Gender");
		String gender = scanner.next();

		try {
			String sql = "insert into patients(name,age,gender) values(?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, age);
			ps.setString(3, gender);

			int affectedrows = ps.executeUpdate();
			if (affectedrows > 0) {
				System.out.println("patient data insert successfully.");
			} else
				System.out.println("Something Went wrong,");
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void viewpatients() {
		String query = "select * from patients";
		try {
			PreparedStatement p = connection.prepareStatement(query);
			ResultSet set = p.executeQuery();
			System.out.println("Patients-:");
			while (set.next()) {
				System.out.println("below are details of the patients....");
				int i = set.getInt("id");
				System.out.println("id Number is -:" + i);
				String n = set.getString("name");
				System.out.println("name is -:" + n);
				int a = set.getInt("age");
				System.out.println("age is -:" + a);
				String g = set.getString("gender");
				System.out.println("gender is -:" + g);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean getPatientById(int id) {
		String q = "select * from patients where id=?";
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
