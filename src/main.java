import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.*;

/*
	Project Name: Pomona Transit System
	Author: Long Truong (Tony)
	CS 4350 - Database Systems
	Dr. Salam Salloum 
*/

public class main {
	public static Scanner in = new Scanner(System.in);
	private static final String userName = "root";
	private static final String password = "root";
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int choice = -1;
		while(choice != 0) {
			showMenu();
			System.out.println(">>> Your Choice: ");
			choice = in.nextInt();
			
			switch(choice) {
			case 1:
				showSchedule();
				break;
			case 2:
				
				break;
			case 3:
				
				break;
			case 4:
				
				break;
			case 5:
				
				break;
			case 6:
				
				break;
			case 7:
				addDriver();
				break;
			case 8:
				addBus();
				break;
			case 9:
				deleteBus();
				break;
			case 10:
				
				break;
			case 0:
				break;
			default:
				System.out.println("Out of range input. Please read the menu again and insert and integer from 0 to 10 ONLY!!");
				break;
			}
		}
		
		System.out.println("Program ends");
	}
	
	private static void showMenu() {
		System.out.println("[1] : Display the schedule");
		System.out.println("[2] : Delete Trip Offering");
		System.out.println("[3] : Change Driver");
		System.out.println("[4] : Change Bus");
		System.out.println("[5] : Display Bus Stops");
		System.out.println("[6] : Display weekly schedule of a driver");
		System.out.println("[7] : Add a driver");
		System.out.println("[8] : Add a bus");
		System.out.println("[9] : Delete a bus");
		System.out.println("[10]: Insert actual trip information");
		System.out.println("[0] : Exit");
		System.out.println("--------------------------------------------------------");
	}

	private static void showSchedule() {
		
	}
	
	private static void editSchedule() {
		
	}
	
	private static void showStops() {
		
	}
	
	private static void showDriverWeekSchedule() {
		
	}
	
	private static void addDriver() {
		String dName;	// driver's name
		String dPhone;	// driver's telephone number
		
		System.out.println("Driver Name: ");
		dName = in.next();
		
		System.out.println("Driver Telephone Number: ");
		dPhone = in.next();
		
		String sqlCmd = "INSERT INTO Driver VALUES(\'" + dName + "\',\'" + dPhone + "\');";
		try {
			execute(sqlCmd);
			System.out.println("Added successfully!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Check mysql Manual for syntax");
			e.printStackTrace();
		}
	}
	
	private static void addBus() {
		int busID;
		String busModel;
		String year;
		
		System.out.println("BusID: ");
		busID = in.nextInt();
		
		System.out.println("Bus Model: ");
		busModel = in.next();
		
		System.out.println("Year: ");
		year = in.next();
		
		String sqlCmd = "INSERT INTO Bus VALUES(" + Integer.toString(busID) + ",\'" + busModel + "\',\'" + year + "\');";
		
		try {
			execute(sqlCmd);
			System.out.println("Added successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Check mysql Manual for syntax");
			e.printStackTrace();
		}
	}
	
	private static void deleteBus() {
		int busID;
		
		System.out.println("BusID to be deleted: ");
		busID = in.nextInt();
		
		String sqlCmd = "DELETE FROM Bus WHERE BusID = " + Integer.toString(busID) + ";";
		
		try {
			int result = execute(sqlCmd);
			if(result == 0) {
				System.out.println("There is no bus with ID = " + busID + "to delete");
			}
			else {
				System.out.println("Deleted successfully!");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Check mysql Manual for syntax");
			e.printStackTrace();
		}
	}
	
	private static int execute(String sqlCmd) throws SQLException {
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost/CS4350_Lab4?" 
				+ "user=" + userName + "&password=" + password);
				
				Statement stmt = con.createStatement();
				int result = stmt.executeUpdate(sqlCmd);
				stmt.close();
				con.close();
				return result;
			}catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			return -1;	// error
		}
		return -1;		// error
	}
	
}
