import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Scanner;


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
				
				break;
			case 9:
				
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
		execute(sqlCmd);
	}
	
	private static void addBus() {
		
	}
	
	private static void deleteBus() {
		
	}
	
	private static void execute(String sqlCmd) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/CS4350_Lab4?" + "user=" + userName + "&password=" + password);
			
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sqlCmd);
			stmt.close();
			con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
