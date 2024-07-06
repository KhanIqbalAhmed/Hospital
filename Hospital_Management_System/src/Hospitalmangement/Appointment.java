package Hospitalmangement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Appointment {
	public static void bookappointement(patient pat, Doctors doctor, Connection connection, Scanner scanner) {
		System.out.println("Enter patient id:");
		int pid = scanner.nextInt();
		System.out.println("Enter doctor id:");
		int did = scanner.nextInt();
		System.out.println("Enter appointment date(yyyy-mm-dd)");
		String date = scanner.next();
		if (pat.getPatientById(pid) && doctor.getdoctorById(did)) {
			if (checkDoctoravailability(did, date, connection)) {
				String appointmentquery = "insert into appointment(patient_id,doctor_id,appointment_date)values(?,?,?)";
				try {
					PreparedStatement ps = connection.prepareStatement(appointmentquery);
					ps.setInt(1, pid);
					ps.setInt(2, did);
					ps.setString(3, date);
					int affectedrow = ps.executeUpdate();
					if (affectedrow > 0) {
						System.out.println("appointment booked");
					} else
						System.out.println("fail to book appointment");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("doctor is not available at this date");
			}
		} else {
			System.out.println("Either doctor or Patient does't exist");
		}

	}

	public static boolean checkDoctoravailability(int did, String date, Connection con) {
		String query = "select count(*)from appointment where doctor_id =? and appointment_date=?";
		try {

			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, did);
			ps.setString(2, date);
			ResultSet set = ps.executeQuery();
			if (set.next()) {
				int count = set.getInt(1);
				if (count == 0) {
					return true;
				}
				return false;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	public static void viewappointment(Connection connection, Scanner scanner) throws SQLException {
		try {
			String query = "select appointment_date from appointment where patient_id=?";
			PreparedStatement ps = connection.prepareStatement(query);
			System.out.println("Enter Patient id ");
			int p = scanner.nextInt();
			ps.setInt(1, p);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				// Print the appointment date if found
				System.out.println("Your appointment date is: " + rs.getDate("appointment_date"));
			} else {
				// Print a message if not found
				System.out.println("No appointment found for patient ID " + p);
			}

		} catch (SQLException e) {
			System.err.println("Error: " + e.getMessage());
		} finally {
			// Close the Scanner to prevent resource leaks
			scanner.close();
		}
	}

}
