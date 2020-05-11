import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DateFormatter;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class GUI {

	private static final String userName = "root";
	private static final String password = "root";

	public static final int SCREEN_WIDTH = 1000;
	public static final int SCREEN_HEIGHT = 800;
	public static final int MENU_PANEL_HEIGHT = 210;
	public static final int INPUT_PANEL_HEIGHT = 200;
	public static final int RESULT_PANEL_HEIGHT = SCREEN_HEIGHT - MENU_PANEL_HEIGHT - INPUT_PANEL_HEIGHT;

	// Basic Components
	public static JFrame f = new JFrame();
	public static JPanel menuPanel = new JPanel();
	public static JPanel inputPanel = new JPanel();
	public static JPanel resultPanel = new JPanel();

	public static JPanel mainPanel = new JPanel();

	public static JButton[] btns = new JButton[12];
	public static JLabel[] labels = new JLabel[12];

	// Input Panel
	// Bus
	public static JLabel busIDLabel;
	public static JTextField busIDtf;
	public static JLabel busModelLabel;
	public static JTextField busModeltf;
	public static JLabel busYearLabel;
	public static JTextField busYeartf;


	// Driver
	public static JLabel dNameLabel;
	public static JTextField dNametf;
	public static JLabel dPhone;
	public static JTextField dPhonetf;

	// Trips
	public static JLabel startLocNameLabel;
	public static JLabel destNameLabel;
	public static JLabel tripDateLabel;
	public static JLabel tripNumLabel;
	public static JLabel schedSTimeLabel;
	public static JLabel stopNumLabel;

	public static JTextField startLocNametf;
	public static JTextField destNametf;
	public static JFormattedTextField tripDatetf;
	public static JTextField tripNumtf;
	public static JTextField schedStartTimetf;
	public static JTextField stopNumtf;


	//	public static JButton enterBtn;
	public static DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	public static DateFormatter df = new DateFormatter(format);
	public static boolean cmdChosen = false;
	public static int task = -1;

	public static JTable resultTable;
	public static DefaultTableModel model;
	public static JScrollPane sPane;
	public static JButton addBtn;
	public static JButton insertBtn;
	public static String curTable;

	public static Vector<Integer> colLock = new Vector<Integer>();


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		f.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setResizable(false);

		createComponent();
		wireComponent();


		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {

			@Override
			public boolean dispatchKeyEvent(KeyEvent e) {
				// TODO Auto-generated method stub
				synchronized(GUI.class) {
					switch(e.getID()) {
					case KeyEvent.KEY_PRESSED:
						if(e.getKeyCode() == KeyEvent.VK_ENTER) {
							if(cmdChosen) {
								switch(task) {
								case 1:			// display schedule
									displaySchedule();
									break;
								case 2:			// delete trip offering
									removeTripOffering();
									break;
								case 3:			// add trip offering
									addTripOffering();
									break;
								case 4:			// change driver in trip offering
									changeDriver();
									break;
								case 5:			// change bus in trip offering
									changeBus();
									break;
								case 6:			// display bus stops
									displayBusStops();
									break;
								case 7:			// display weekly schedule of a driver
									displayWeeklySchedule();
									break;
								case 8:			// Add a new driver
									addDriver();
									break;
								case 9:			// add a new bus
									addBus();
									break;
								case 10:			// delete a bus
									removeBus();
									break;
								case 11:			// insert actual trip info
									insertActualData();
									break;
								case 0:				// Exit

									break;
								case -1:

									break;
								}
							}
						}
						else if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
							if(cmdChosen) {
								task = -1;
								reset();
								cmdChosen = false;
							}
						}
						break;
					case KeyEvent.KEY_RELEASED:

						break;
					case KeyEvent.KEY_TYPED:

						break;
					}
				}

				return false;
			}
		});

		f.pack();
		f.setVisible(true);
	}

	public static void createComponent() {
		menuPanel.setBackground(Color.RED);
		inputPanel.setBackground(Color.GREEN);
		resultPanel.setBackground(Color.BLUE);

		menuPanel.setLayout(null);
		inputPanel.setLayout(null);
		resultPanel.setLayout(null);

		createMenuPanel();
		createInputPanel();
		createResultPanel();


		// Adding panels to main JFrame
		menuPanel.setBounds(0, 0, SCREEN_WIDTH, MENU_PANEL_HEIGHT);
		inputPanel.setBounds(0, MENU_PANEL_HEIGHT, SCREEN_WIDTH, INPUT_PANEL_HEIGHT);
		resultPanel.setBounds(0, MENU_PANEL_HEIGHT + INPUT_PANEL_HEIGHT, SCREEN_WIDTH, RESULT_PANEL_HEIGHT);		

		mainPanel.setLayout(null);
		mainPanel.add(inputPanel);
		mainPanel.add(menuPanel);
		mainPanel.add(resultPanel);

		reset();
		f.add(mainPanel);
	}

	public static void createMenuPanel() {
		for(int i=0; i<btns.length; i++) {
			// Create Buttons
			int val = i + 1;
			if(i == btns.length - 1)
				val = 0;
			btns[i] = new JButton(Integer.toString(val));
			btns[i].setFont(new Font("Serif", Font.BOLD, 14));

			// Set position of the buttons
			if(i > 5)
				btns[i].setBounds(400, (i - 6)*(35 + 0), 55, 30);
			else
				btns[i].setBounds(0, i*(35 + 0), 55, 30);


			// Create Labels
			labels[i] = new JLabel();
			labels[i].setFont(new Font("Serif", Font.BOLD, 14));
			if(i > 5)
				labels[i].setBounds(460, (i - 6)*(35 + 0), 300, 30);
			else
				labels[i].setBounds(60, i*(35 + 0), 300, 30);

			// Add the buttons and labels to the panel 
			menuPanel.add(btns[i]);
			menuPanel.add(labels[i]);
		}

		labels[0].setText("Display the schedule");
		labels[1].setText("Delete Trip Offering");
		labels[2].setText("Add Trip Offering");
		labels[3].setText("Change Driver");
		labels[4].setText("Change Bus");
		labels[5].setText("Display Bus Stops");
		labels[6].setText("Display weekly schedule of a driver");
		labels[7].setText("Add a driver");
		labels[8].setText("Add a bus");
		labels[9].setText("Delete a bus");
		labels[10].setText("Insert actual trip information");
		labels[11].setText("Exit");
	}

	public static void createInputPanel() {
		createBusInput();
		unshowBusInput();

		createDriverInput();
		unshowDriverInput();

		createTripInput();
		unshowTripInput();

		createTripOffInput();
		unshowTripOffInput();

		createStopInfoInput();
		unshowStopInfoInput();
	}

	public static void createResultPanel() {
		model = new DefaultTableModel(5,4) {
			@Override
			public boolean isCellEditable(int row, int column) {
				if(colLock.size() != 0) {
					for(int i=0; i<colLock.size(); i++) {
						if(column == colLock.elementAt(i)) {
							return false;
						}
					}
					return true;	
				}
				return true;	
			}
		};

		resultTable = new JTable(model);

		int tableWidth = SCREEN_WIDTH;
		int tableHeight = 300;

		int cellWidth = tableWidth / resultTable.getColumnCount();
		int cellHeight = tableHeight / resultTable.getRowCount();

		resultTable.setBounds(0, 0, tableWidth, tableHeight);
		resultTable.setRowHeight(cellHeight);

		for(int i=0; i<resultTable.getColumnCount(); i++) {
			resultTable.getColumnModel().getColumn(i).setPreferredWidth(cellWidth);
		}

		resultTable.getTableHeader().setPreferredSize(new Dimension(SCREEN_WIDTH, 45));
		resultTable.getTableHeader().setBackground(Color.CYAN);
		resultTable.getTableHeader().setReorderingAllowed(false);
		resultTable.setFont(new Font("Serif", Font.BOLD, 14));


		addBtn = new JButton("Add");
		addBtn.setFont(new Font("Serif", Font.BOLD, 14));
		addBtn.setBounds(SCREEN_WIDTH - 100, RESULT_PANEL_HEIGHT - 40, 100, 40);

		insertBtn = new JButton("Insert");
		insertBtn.setFont(new Font("Serif", Font.BOLD, 14));
		insertBtn.setBounds(SCREEN_WIDTH - 100, RESULT_PANEL_HEIGHT - 40, 100, 40);
		

		sPane = new JScrollPane(resultTable);
		sPane.setBounds(0, 0, SCREEN_WIDTH, RESULT_PANEL_HEIGHT - 40);
		resultPanel.add(sPane);
		resultPanel.add(addBtn);
		resultPanel.add(insertBtn);
	}

	public static void createBusInput(){
		busIDLabel = new JLabel("BusID: ");
		busModelLabel = new JLabel("Model: ");
		busYearLabel = new JLabel("Year: ");

		busIDtf = new JTextField();
		busModeltf = new JTextField();
		busYeartf = new JTextField();

		// Set position
		busIDLabel.setBounds(50, 10, 60, 40);
		busModelLabel.setBounds(50, 60, 60, 40);
		busYearLabel.setBounds(50, 110, 60, 40);

		busIDtf.setBounds(140, 10, 150, 40);
		busModeltf.setBounds(140, 60, 150, 40);
		busYeartf.setBounds(140, 110, 150, 40);

		inputPanel.add(busIDLabel);
		inputPanel.add(busModelLabel);
		inputPanel.add(busYearLabel);
		inputPanel.add(busIDtf);
		inputPanel.add(busModeltf);
		inputPanel.add(busYeartf);


		busIDLabel.setFont(new Font("Serif", Font.BOLD, 14));
		busModelLabel.setFont(new Font("Serif", Font.BOLD, 14));
		busYearLabel.setFont(new Font("Serif", Font.BOLD, 14));

		busIDtf.setFont(new Font("Serif", Font.BOLD, 14));
		busModeltf.setFont(new Font("Serif", Font.BOLD, 14));
		busYeartf.setFont(new Font("Serif", Font.BOLD, 14));
	}

	public static void showBusInput() {
		busIDLabel.setBounds(50, 10, 60, 40);
		busIDtf.setBounds(140, 10, 150, 40);
		
		busIDLabel.setVisible(true);
		busModelLabel.setVisible(true);
		busYearLabel.setVisible(true);

		busIDtf.setVisible(true);
		busModeltf.setVisible(true);
		busYeartf.setVisible(true);
	}

	public static void unshowBusInput() {
		busIDLabel.setVisible(false);
		busModelLabel.setVisible(false);
		busYearLabel.setVisible(false);

		busIDtf.setVisible(false);
		busModeltf.setVisible(false);
		busYeartf.setVisible(false);
	}


	public static void createDriverInput() {
		dNameLabel = new JLabel("Driver Name: ");
		dNametf = new JTextField();
		dPhone = new JLabel("Driver Phone: ");
		dPhonetf = new JTextField();

		dNameLabel.setFont(new Font("Serif", Font.BOLD, 14));
		dNametf.setFont(new Font("Serif", Font.BOLD, 14));
		dPhone.setFont(new Font("Serif", Font.BOLD, 14));
		dPhonetf.setFont(new Font("Serif", Font.BOLD, 14));

		dNameLabel.setBounds(50, 10, 120, 40);
		dNametf.setBounds(180, 10, 150, 40);
		dPhone.setBounds(50, 60, 120, 40);
		dPhonetf.setBounds(180, 60, 150, 40);


		inputPanel.add(dNameLabel);
		inputPanel.add(dNametf);
		inputPanel.add(dPhone);
		inputPanel.add(dPhonetf);
	}

	public static void showDriverInput() {
		dNameLabel.setBounds(50, 10, 120, 40);
		dNametf.setBounds(180, 10, 150, 40);
		
		dNameLabel.setVisible(true);
		dNametf.setVisible(true);

		dPhone.setVisible(true);
		dPhonetf.setVisible(true);
	}

	public static void unshowDriverInput() {
		dNameLabel.setVisible(false);
		dNametf.setVisible(false);

		dPhone.setVisible(false);
		dPhonetf.setVisible(false);
	}

	public static void createTripInput() {
		startLocNameLabel = new JLabel("Start Location Name: ");
		destNameLabel = new JLabel("Destination Name: ");
		tripDateLabel = new JLabel("Date(YYYY-MM-DD): ");

		startLocNametf = new JTextField();
		destNametf = new JTextField();
		tripDatetf = new JFormattedTextField(df);


		startLocNameLabel.setFont(new Font("Serif", Font.BOLD, 14));
		destNameLabel.setFont(new Font("Serif", Font.BOLD, 14));
		tripDateLabel.setFont(new Font("Serif", Font.BOLD, 14));

		startLocNametf.setFont(new Font("Serif", Font.BOLD, 14));
		destNametf.setFont(new Font("Serif", Font.BOLD, 14));
		tripDatetf.setFont(new Font("Serif", Font.BOLD, 14));


		startLocNameLabel.setBounds(50, 10, 180, 40);
		destNameLabel.setBounds(50, 60, 180, 40);
		tripDateLabel.setBounds(50, 110, 180, 40);

		startLocNametf.setBounds(260, 10, 150, 40);
		destNametf.setBounds(260, 60, 150, 40);
		tripDatetf.setBounds(260, 110, 150, 40);

		inputPanel.add(startLocNameLabel);
		inputPanel.add(destNameLabel);
		inputPanel.add(tripDateLabel);

		inputPanel.add(startLocNametf);
		inputPanel.add(destNametf);
		inputPanel.add(tripDatetf);


	}

	public static void showTripInput() {
		tripDateLabel.setBounds(50, 110, 180, 40);
		tripDatetf.setBounds(260, 110, 150, 40);

		startLocNameLabel.setVisible(true);
		startLocNametf.setVisible(true);
		destNameLabel.setVisible(true);
		destNametf.setVisible(true);
		tripDateLabel.setVisible(true);
		tripDatetf.setVisible(true);
	}

	public static void unshowTripInput() {
		startLocNameLabel.setVisible(false);
		startLocNametf.setVisible(false);
		destNameLabel.setVisible(false);
		destNametf.setVisible(false);
		tripDateLabel.setVisible(false);
		tripDatetf.setVisible(false);
	}

	public static void createTripOffInput() {
		tripNumLabel = new JLabel("Trip Number: ");
		schedSTimeLabel = new JLabel("Scheduled Start Time: ");

		tripNumtf = new JTextField();
		schedStartTimetf = new JTextField();

		tripNumLabel.setFont(new Font("Serif", Font.BOLD, 14));
		schedSTimeLabel.setFont(new Font("Serif", Font.BOLD, 14));
		tripNumtf.setFont(new Font("Serif", Font.BOLD, 14));
		schedStartTimetf.setFont(new Font("Serif", Font.BOLD, 14));

		tripNumLabel.setBounds(50, 10, 200, 40);
		tripDateLabel.setBounds(50, 60, 200, 40);
		schedSTimeLabel.setBounds(50, 110, 200, 40);

		tripNumtf.setBounds(280, 10, 150, 40);
		tripDatetf.setBounds(280, 60, 150, 40);
		schedStartTimetf.setBounds(280, 110, 150, 40);

		inputPanel.add(tripNumLabel);
		inputPanel.add(tripNumtf);
		inputPanel.add(schedSTimeLabel);
		inputPanel.add(schedStartTimetf);	
	}

	public static void showTripOffInput() {
		tripDateLabel.setBounds(50, 60, 200, 40);
		tripDatetf.setBounds(280, 60, 150, 40);

		tripNumLabel.setBounds(50, 10, 200, 40);
		tripNumtf.setBounds(280, 10, 150, 40);

		tripNumLabel.setVisible(true);
		tripNumtf.setVisible(true);
		tripDateLabel.setVisible(true);
		tripDatetf.setVisible(true);
		schedSTimeLabel.setVisible(true);
		schedStartTimetf.setVisible(true);
	}

	public static void unshowTripOffInput() {
		tripNumLabel.setVisible(false);
		tripNumtf.setVisible(false);
		tripDateLabel.setVisible(false);
		tripDatetf.setVisible(false);
		schedSTimeLabel.setVisible(false);
		schedStartTimetf.setVisible(false);
	}

	public static void createStopInfoInput() {
		tripNumLabel.setBounds(50, 10, 120, 40);
		tripNumtf.setBounds(180, 10, 150, 40);
	}

	public static void showStopInfoInput() {
		tripNumLabel.setBounds(50, 10, 120, 40);
		tripNumtf.setBounds(180, 10, 150, 40);

		tripNumLabel.setVisible(true);
		tripNumtf.setVisible(true);
	}

	public static void unshowStopInfoInput() {
		tripNumLabel.setVisible(false);
		tripNumtf.setVisible(false);
	}

	public static void showDisplayWeekly() {
		dNameLabel.setBounds(50, 10, 200, 40);
		dNametf.setBounds(280, 10, 150, 40);
		tripDateLabel.setBounds(50, 60, 200, 40);
		tripDatetf.setBounds(280, 60, 150, 40);
		
		dNameLabel.setVisible(true);
		dNametf.setVisible(true);
		tripDateLabel.setVisible(true);
		tripDatetf.setVisible(true);
	}
	
	public static void unshowDisplayWeekly() {
		dNameLabel.setVisible(false);
		dNametf.setVisible(false);
		tripDateLabel.setVisible(false);
		tripDatetf.setVisible(false);
	}
	
	public static void showActStopInfo() {
		tripNumLabel.setBounds(50, 10, 200, 40);
		tripNumtf.setBounds(280, 10, 150, 40);

		tripDateLabel.setBounds(50, 60, 200, 40);
		tripDatetf.setBounds(280, 60, 150, 40);

		schedSTimeLabel.setBounds(50, 110, 200, 40);
		schedStartTimetf.setBounds(280, 110, 150, 40);

		tripNumLabel.setVisible(true);
		tripNumtf.setVisible(true);
		tripDateLabel.setVisible(true);
		tripDatetf.setVisible(true);
		schedSTimeLabel.setVisible(true);
		schedStartTimetf.setVisible(true);
	}

	public static void unshowActStopInfo() {
		tripNumLabel.setVisible(false);
		tripNumtf.setVisible(false);
		tripDateLabel.setVisible(false);
		tripDatetf.setVisible(false);
		schedSTimeLabel.setVisible(false);
		schedStartTimetf.setVisible(false);
	}

	public static void reset() {
		model = new DefaultTableModel(5,4) {
			@Override
			public boolean isCellEditable(int row, int column) {
				if(colLock.size() != 0) {
					for(int i=0; i<colLock.size(); i++) {
						if(column == colLock.elementAt(i)) {
							return false;
						}
					}
					return true;	
				}
				return true;	
			}
		};
		model.setRowCount(0);
		colLock.clear();
		
		unshowBusInput();
		unshowDriverInput();
		unshowTripInput();
		unshowTripOffInput();
		unshowStopInfoInput();
		unshowActStopInfo();
		unshowDisplayWeekly();

		addBtn.setVisible(false);
		insertBtn.setVisible(false);
		dNameLabel.setText("Driver Name: ");
		busIDLabel.setText("BusID: ");
		
		// clear all values inside JTextFields
		busIDtf.setText("");
		busModeltf.setText("");
		busYeartf.setText("");

		dNametf.setText("");
		dPhonetf.setText("");

		startLocNametf.setText("");
		destNametf.setText("");
		tripDatetf.setText("");
		tripNumtf.setText("");
		schedStartTimetf.setText("");
	}

	public static void wireComponent() {
		btns[0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Display the schedule");
				cmdChosen = true;
				task = 1;

				reset();
				showTripInput();
			}
		});

		btns[1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Delete Trip Offering");
				cmdChosen = true;
				task = 2;

				setActiveTable("TripOffering");

				reset();
				showTripOffInput();
			}
		});

		btns[2].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Add Trip Offering");
				cmdChosen = true;
				task=3;

				setActiveTable("TripOffering");
				model.setRowCount(5);
				reset();
				addBtn.setVisible(true);
			}
		});

		btns[3].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Change Driver for TripOffering");
				cmdChosen = true;
				task = 4;

				setActiveTable("TripOffering");

				reset();
				showTripOffInput();

				dNameLabel.setText("New Driver Name: ");
				dNameLabel.setBounds(50, 160, 200, 40);
				dNametf.setBounds(280, 160, 150, 40);
				
				dNameLabel.setVisible(true);
				dNametf.setVisible(true);
			}
		});

		btns[4].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Change Bus for TripOffering");
				cmdChosen = true;
				task = 5;

				setActiveTable("TripOffering");
				reset();
				showTripOffInput();
				
				busIDLabel.setText("New BusID: ");
				busIDLabel.setBounds(50, 160, 200, 40);
				busIDtf.setBounds(280, 160, 150, 40);
				
				busIDLabel.setVisible(true);
				busIDtf.setVisible(true);
			}
		});

		btns[5].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Display Stop info of given trip");
				cmdChosen = true;
				task = 6;

				reset();
				showStopInfoInput();
			}
		});
		
		btns[6].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Display Weekly Schedule of a driver");
				cmdChosen = true;
				task = 7;

				reset();
				showDisplayWeekly();
			}
		});

		btns[7].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Add a driver");
				cmdChosen = true;
				task = 8;
				reset();
				showDriverInput();
			}
		});
		btns[8].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Add a bus");
				cmdChosen = true;
				task = 9;
				reset();
				showBusInput();
			}
		});

		btns[9].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Delete a bus");
				cmdChosen = true;
				task = 10;

				reset();
				showBusInput();
				busModelLabel.setVisible(false);
				busModeltf.setVisible(false);
				busYearLabel.setVisible(false);
				busYeartf.setVisible(false);
			}
		});


		btns[10].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Insert actual trip info");
				cmdChosen = true;
				task = 11;
				setActiveTable("ActualTripStopInfo");
				reset();
				showActStopInfo();
				
				insertBtn.setVisible(true);
			}
		});

		btns[11].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Exit");
				cmdChosen = true;
				task = 0;

				reset();
			}
		});
		
		insertBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String tripNum = (String) resultTable.getModel().getValueAt(0, 0);
				String date = (String) resultTable.getModel().getValueAt(0, 1);
				String scheduledStartTime = (String) resultTable.getModel().getValueAt(0, 2);
				String stopNum = (String) resultTable.getModel().getValueAt(0, 3);
				String scheduledArrTime =(String) resultTable.getModel().getValueAt(0, 4);
				String actualStartTime = (String) resultTable.getModel().getValueAt(0, 5);
				String actualArrTime = (String) resultTable.getModel().getValueAt(0, 6);
				String numPassIn = (String) resultTable.getModel().getValueAt(0, 7);
				String numPassOut = (String) resultTable.getModel().getValueAt(0, 8);
				
				if(stopNum.isEmpty() || scheduledArrTime.isEmpty() || actualStartTime.isEmpty()  || 
						actualArrTime.isEmpty()  || numPassIn.isEmpty() || numPassOut.isEmpty()) {
					
				}
				else {
					String query = "INSERT INTO ActualTripStopInfo values "
							+ "(" + tripNum + " , '"
								  		+ date + "' , "
								  		+ "TIME('" + scheduledStartTime + "') , "
								  		+ stopNum + " , "
								  		+ "TIME('" + scheduledArrTime + "') , "
								  		+ "TIME('" + actualStartTime + "') , "
								  		+ "TIME('" + actualArrTime + "') , "
								  		+ numPassIn + " , "
								  		+ numPassOut + ");";
					
					try {
						executeUpdate(query);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				colLock.clear();
			}
		});
		

		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Vector<Vector> tableData = model.getDataVector();
				int colCount = tableData.elementAt(0).size();

				String sqlCmd = "INSERT INTO TripOffering VALUES ";

				for(int i=0; i<tableData.size(); i++) {
					boolean isValid = true;
					String inputVals = "";

					if(i != 0) {
						inputVals += ", (";
					}
					else
						inputVals = "(";

					for(int j=0; j<colCount; j++) {
						if(tableData.elementAt(i).elementAt(j) == null) {
							// there is no value in cell(i,j)
							isValid = false;
							break;
						}
						else {
							if(j != 0 && j !=colCount-1)
								inputVals += "'" + tableData.elementAt(i).elementAt(j) + "'";
							else
								inputVals += tableData.elementAt(i).elementAt(j);
							if(j != colCount - 1) {
								inputVals += ", ";
							}
						}
					}
					inputVals += ")";

					if(isValid) {
						sqlCmd += inputVals;
					}
				}
				sqlCmd += ";";

				try {
					executeUpdate(sqlCmd);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	private static int displaySchedule() {
		String startLocName = startLocNametf.getText();
		String destName = destNametf.getText();
		String tripDate = tripDatetf.getText();

		if(!startLocName.isEmpty() && !destName.isEmpty() && !tripDate.isEmpty()) {
			String sqlCmd = "SELECT T.TripNumber, T.StartLocationName, T.DestinationName, O.Date, "
					+ "O.ScheduledStartTime, O.ScheduledArrivalTime, O.DriverName, O.BusID "
					+ "FROM Trip T, TripOffering O "
					+ "WHERE T.TripNumber = O.TripNumber AND "
					+ "T.StartLocationName LIKE '" + startLocName + "' AND "
					+ "T.DestinationName LIKE '" + destName + "' AND "
					+ "O.Date LIKE '" + tripDate + "';";
			try {
				ResultSet rs = executeQuery(sqlCmd);
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				System.out.println("Check mysql Manual for syntax");
				ex.printStackTrace();
				return -1;
			}
		}
		return 0;
	}

	private static int removeTripOffering() {
		String tripNum = tripNumtf.getText();
		String tripDate = tripDatetf.getText();
		String scheduledStartTime = schedStartTimetf.getText();

		if(!tripNum.isEmpty() && !tripDate.isEmpty() && !scheduledStartTime.isEmpty()) {
			String sqlCmd="DELETE FROM TripOffering WHERE TripNumber=" + 
					tripNum + " AND " + "Date='" + tripDate + 
					"' AND ScheduledStartTime='" + scheduledStartTime+"';";
			try {
				executeUpdate(sqlCmd);
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				System.out.println("Check mysql Manual for syntax");
				ex.printStackTrace();
				return -1;
			}
		}
		return 0;
	}

	private static int addTripOffering() {
		return 0;
	}

	private static int addDriver() {
		String name = dNametf.getText();
		String phone = dPhonetf.getText();

		if(!name.isEmpty() && !phone.isEmpty()) {
			String sqlCmd = "INSERT INTO Driver VALUES(\'" + name + "\',\'" + phone + "\');";		
			try {
				executeUpdate(sqlCmd);
				System.out.println("Added successfully!");
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				System.out.println("Check mysql Manual for syntax");
				ex.printStackTrace();
				return -1;
			}	
		}
		return 0;
	}

	private static int addBus() {
		String busID = busIDtf.getText();
		String busModel = busModeltf.getText();
		String busYear = busYeartf.getText();

		if(!busID.isEmpty() && !busModel.isEmpty() && !busYear.isEmpty()) {
			String sqlCmd = "INSERT INTO Bus VALUES(" + busID + ",\'" + busModel + "\',\'" + busYear + "\');";		
			try {
				executeUpdate(sqlCmd);
				System.out.println("Added successfully!");
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				System.out.println("Check mysql Manual for syntax");
				ex.printStackTrace();
				return -1;
			}	
		}
		return 0;
	}

	private static int removeBus() {
		String busID = busIDtf.getText();
		try {
			String sqlCmd = "DELETE FROM Bus WHERE BusID = " + busID + ";";
			int result = executeUpdate(sqlCmd);
			if(result == 0) {
				System.out.println("There is no bus with ID = " + busID + "to delete");
			}
			else {
				System.out.println("Deleted successfully!");
			}

		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			System.out.println("Check mysql Manual for syntax");
			ex.printStackTrace();
			return -1;
		}
		return 0;
	}

	private static int changeDriver(){
		String tripNum = tripNumtf.getText();
		String tripDate = tripDatetf.getText();
		String scheduledStartTime = schedStartTimetf.getText();
		String dName = dNametf.getText();
		
		if(!tripNum.isEmpty() && !tripDate.isEmpty() && !scheduledStartTime.isEmpty() && !dName.isEmpty()) {			
			String query = "UPDATE TripOffering"
					+ " SET DriverName= '" + dName
					+ "' WHERE TripNumber=" + tripNum
					+ " AND Date='" + tripDate
					+ "' AND ScheduledStartTime='" + scheduledStartTime + "';";
			try {
				executeUpdate(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return -1;
			}	
		}

		return 0;
	}

	private static int changeBus() {
		String tripNum = tripNumtf.getText();
		String tripDate = tripDatetf.getText();
		String scheduledStartTime = schedStartTimetf.getText();
		String busID = busIDtf.getText();
		
		if(!tripNum.isEmpty() && !tripDate.isEmpty() && !scheduledStartTime.isEmpty() && !busID.isEmpty()) {			
			String query = "UPDATE TripOffering"
					+ " SET BUSID= '" + busID
					+ "' WHERE TripNumber=" + tripNum
					+ " AND Date='" + tripDate
					+ "' AND ScheduledStartTime='" + scheduledStartTime + "';";
			try {
				executeUpdate(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return -1;
			}	
		}
		
		return 0;
	}
	
	private static int displayBusStops(){
		String tripNum = tripNumtf.getText();

		if(!tripNum.isEmpty()) {
			String sqlCmd ="SELECT * FROM TripStopInfo WHERE TripNumber="
					+ tripNum + ";";
			try {
				executeQuery(sqlCmd);
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				System.out.println("Check mysql Manual for syntax");
				ex.printStackTrace();
				return -1;
			}	
		}
		return 0;
	}
	
	private static String getNextDate(String curDate) {
		String result="";
		
		try {
			Calendar today = Calendar.getInstance();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(curDate);
            today.setTime(date);
            today.add(Calendar.DAY_OF_YEAR, 1);
            result = format.format(today.getTime());
		} catch (Exception e) {
			return result;
		}
		return result;
	}
	
	private static int displayWeeklySchedule(){
		String dName = dNametf.getText();
		String startDate = tripDatetf.getText();
		
		
		if(!dName.isEmpty() && !startDate.isEmpty()) {
			String sequences = "'" + startDate + "' , ";
			String curDate = startDate;
			int i=1;
			while(i < 7) {
				String nextDate = getNextDate(curDate);
				
				sequences += "'" + nextDate + "'";
				if(i != 6) {
					sequences += " , ";
				}
				i++;
				curDate = nextDate;
			}
			
			System.out.println("Sequences: " + sequences);
			
			
			String query = "SELECT *"
					+ " FROM TripOffering"
					+ " WHERE DriverName='" + dName
					+ "' AND Date IN(" + sequences + ");";
			try {
				executeQuery(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return -1;
			}
		}
		return 0;
	}
	
	private static int insertActualData() {
		String tripNum = tripNumtf.getText();
		String tripDate = tripDatetf.getText();
		String startTime = schedStartTimetf.getText();

		if(!tripNum.isEmpty() && !tripDate.isEmpty() && !startTime.isEmpty()) {
			try {
				String query = "SELECT T1.ScheduledArrivalTime, T2.StopNumber "
						+ "FROM TripOffering T1, TripStopInfo T2 "
						+ "WHERE T1.TripNumber=" + tripNum
						+ " AND T1.TripNumber = T2.TripNumber "
						+ " AND T1.Date LIKE '" + tripDate + "'"
						+ " AND T1.ScheduledStartTime LIKE '" + startTime + "'";
				
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost/CS4350_Lab4?" 
						+ "useLegacyDatetimeCode=false&serverTimezone=America/Los_Angeles&" + "user=" + userName + "&password=" + password);

				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);

				// Get Scheduled Time and Stop Number
				String arrivalTime = "";
				String stopNum = "";
				while(rs.next()) {
					arrivalTime = rs.getString(1);
					stopNum = rs.getString(2);
				}

				rs = stmt.executeQuery("SELECT * FROM ActualTripStopInfo;");			
				ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();

				// Get Column Names
				int colCount = rsmd.getColumnCount();
				String header[] = new String[colCount];
				for(int i=0; i<colCount; i++) {
					header[i] = rsmd.getColumnName(i+1);
				}

				model.setColumnIdentifiers(header);
				resultTable.setModel(model);
				model.setRowCount(0);
				model.insertRow(0, new Object[] {tripNum, tripDate, startTime, stopNum, arrivalTime, "", "", "", ""});

				
				colLock.add(0);
				colLock.add(1);
				colLock.add(2);
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
		}
		else {
			return -1;
		}
	}


	private static ResultSet executeQuery(String sqlCmd) throws SQLException {
		ResultSet rs = null;
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost/CS4350_Lab4?" 
						+ "useLegacyDatetimeCode=false&serverTimezone=America/Los_Angeles&" + "user=" + userName + "&password=" + password);

				Statement stmt = con.createStatement();
				rs = stmt.executeQuery(sqlCmd);

				tableDisplay(rs);

				stmt.close();
				con.close();
			}catch (ClassNotFoundException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	private static int executeUpdate(String query) throws SQLException {
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost/CS4350_Lab4?" 
						+ "user=" + userName + "&password=" + password +"&serverTimezone=UTC");

				Statement stmt = con.createStatement();
				int result = stmt.executeUpdate(query);
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

	private static void setActiveTable(String tableName) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/CS4350_Lab4?" 
					+ "user=" + userName + "&password=" + password +"&serverTimezone=UTC");

			Statement stmt = con.createStatement();
			String query = "SELECT * FROM " + tableName + ";";
			ResultSet rs = stmt.executeQuery(query);

			ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();

			// Get Column Names
			int colCount = rsmd.getColumnCount();
			String header[] = new String[colCount];
			for(int i=0; i<colCount; i++) {
				header[i] = rsmd.getColumnName(i+1);
			}

			model.setColumnIdentifiers(header);
			model.setRowCount(0);
			resultTable.setModel(model);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void tableDisplay(ResultSet rs) {
		try {
			ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();

			// Get Column Names
			int colCount = rsmd.getColumnCount();
			String header[] = new String[colCount];
			for(int i=0; i<colCount; i++) {
				header[i] = rsmd.getColumnName(i+1);
			}

			model.setColumnIdentifiers(header);
			resultTable.setModel(model);
			model.setRowCount(0);

			while(rs.next()) {
				Object[] rowData = new Object[colCount];
				for(int i=0; i<rowData.length; i++) {
					rowData[i] = rs.getObject(i+1);
				}
				model.insertRow(model.getRowCount(), rowData);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
