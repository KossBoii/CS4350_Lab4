import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.sql.Statement;


public class main {

	private final String userName = "root";
	private final String password = "password";
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// "root" is both the username and password for mysql-server
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/CS4350_Lab4", "root", "root");
			
			
		}
		catch(Exception e){
			System.out.println(e);
		}
		
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
	}

}
