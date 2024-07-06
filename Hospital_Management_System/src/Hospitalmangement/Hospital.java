package Hospitalmangement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Hospital {
	private static final String url ="jdbc:mysql://localhost:3306/hospitalmanagementsystem";
	private static final String username = "root";
	private static final String password = "9321855291";

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Scanner scanner = new Scanner(System.in);
		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			patient p = new patient(connection, scanner);
			Doctors doctor = new Doctors(connection);
			while (true) {
				System.out.println("HOSPITAL MANAGEMENT SYSTEM");
				System.out.println("1. add Patient");
				System.out.println("2. view Patient");
				System.out.println("3. view Doctors");
				System.out.println("4. book Appointment");
				System.out.println("5. View Appointment");
				System.out.println("EXIT");
				System.out.println("Enter Your Choice");
				int choice = scanner.nextInt();
				switch (choice) {
				case 1:
					p.addpatient();
					System.out.println();
					break;
				case 2:
					p.viewpatients();
					System.out.println();
					break;
				case 3:
					doctor.viewDoctors();
					System.out.println();
					break;
				case 4:
					Appointment.bookappointement(p, doctor, connection, scanner);
					break;
				case 5:
					Appointment. viewappointment(connection, scanner);
	            case 6:
					return;
				default:
					System.out.println("Enter valid choice");
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
