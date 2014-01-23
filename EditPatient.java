package mixaniki;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;

public class EditPatient extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JList<String> list;
	private JButton btnShowInfo;
	private JLabel lblName;
	private JLabel lblAge;
	private JLabel lblAdress;
	private JLabel lblPhone;
	private JLabel lblAmka;
	private String selectedPatient;
	private String selectedPatientId;
	private JTextField textField1;
	private JTextField textField2;
	private JTextField textField3;
	private JTextField textField4;
	private JTextField textField5;
	private JButton btnNewButton;
	private JButton btnNewButton1;
	private JButton btnClose;
	private JFrame thisFrame;
	
	/**
	 * Create the frame.
	 */
	public EditPatient() {
		setTitle("Iatrikos Fakelos");
		thisFrame =this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 491, 302);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		list = new JList<String>();
		list.setBounds(10, 37, 162, 197);
		contentPane.add(list);
		
		textField = new JTextField();
		textField.setBounds(10, 6, 162, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connectionSearch();
			}
		});
		btnSearch.setBounds(182, 5, 89, 23);
		contentPane.add(btnSearch);
		
		btnShowInfo = new JButton("Show Info");
		btnShowInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedPatient = list.getSelectedValue();
				connectionInfo();				
			}
		});
		btnShowInfo.setBounds(182, 37, 135, 23);
		contentPane.add(btnShowInfo);
		
		lblName = new JLabel("Name");
		lblName.setBounds(182, 83, 89, 14);
		contentPane.add(lblName);
		
		lblAge = new JLabel("Age");
		lblAge.setBounds(182, 108, 89, 14);
		contentPane.add(lblAge);
		
		lblAdress = new JLabel("Adress");
		lblAdress.setBounds(182, 135, 89, 14);
		contentPane.add(lblAdress);
		
		lblPhone = new JLabel("Phone");
		lblPhone.setBounds(182, 160, 89, 14);
		contentPane.add(lblPhone);
		
		lblAmka = new JLabel("AMKA");
		lblAmka.setBounds(182, 188, 89, 14);
		contentPane.add(lblAmka);
		
		textField1 = new JTextField();
		textField1.setBounds(281, 80, 117, 20);
		contentPane.add(textField1);
		textField1.setColumns(10);
		
		textField2 = new JTextField();
		textField2.setColumns(10);
		textField2.setBounds(281, 105, 117, 20);
		contentPane.add(textField2);
		
		textField3 = new JTextField();
		textField3.setColumns(10);
		textField3.setBounds(281, 132, 117, 20);
		contentPane.add(textField3);
		
		textField4 = new JTextField();
		textField4.setColumns(10);
		textField4.setBounds(281, 157, 117, 20);
		contentPane.add(textField4);
		
		textField5 = new JTextField();
		textField5.setColumns(10);
		textField5.setBounds(281, 185, 117, 20);
		contentPane.add(textField5);
		
		btnNewButton = new JButton("Delete Patient");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connectionDelete();
				connection();
			}
		});
		btnNewButton.setBounds(182, 229, 135, 23);
		contentPane.add(btnNewButton);
		
		btnNewButton1 = new JButton("Update Patient");
		btnNewButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connectionInsert();
				connection();
			}
		});
		btnNewButton1.setBounds(335, 229, 130, 23);
		contentPane.add(btnNewButton1);
	
		btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Panel frame = new Panel();
				frame.setVisible(true);
				thisFrame.setVisible(false);
			}
		});
		btnClose.setBounds(376, 34, 89, 23);
		contentPane.add(btnClose);
		
		connection();
		
		if(Main.loggedInUser.equals("Nos"))
		{
			textField1.setEnabled(false);
			textField2.setEnabled(false);
			btnNewButton.setEnabled(false);
		}
	}
	
	public final void connection(){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
	try
	{
	
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		String connectionUrl = "jdbc:mysql://localhost:3306/mixaniki";
		String connectionUser = "root";
		String connectionPassword = "123456789";
		conn = DriverManager.getConnection(connectionUrl, connectionUser,
		connectionPassword);
		stmt = conn.createStatement();
		rs = stmt.executeQuery("SELECT * FROM Patients");
		
		
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		while (rs.next())
		{
			
		
		String onoma = rs.getString("name");
		
		listModel.addElement(onoma);	

		}
		list.setModel(listModel);	
		
	}
	catch (Exception e)
	{
	e.printStackTrace();
	}
	finally
	{
	
		try { if (rs != null) rs.close(); } catch (SQLException e) {
			e.printStackTrace(); }
		
		try { if (stmt != null) stmt.close(); } catch (SQLException e) {
			e.printStackTrace(); }
		
		try { if (conn != null) conn.close(); } catch (SQLException e) {
			e.printStackTrace(); }
	}
	
	
	
		
	}
	
	
	public void connectionInfo(){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
	try
	{
	
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		String connectionUrl = "jdbc:mysql://localhost:3306/mixaniki";
		String connectionUser = "root";
		String connectionPassword = "123456789";
		conn = DriverManager.getConnection(connectionUrl, connectionUser,
		connectionPassword);
		stmt = conn.createStatement();
		rs = stmt.executeQuery("SELECT * FROM Patients WHERE name = '"+selectedPatient+"';");
				
		while (rs.next())
		{
			
		selectedPatientId= rs.getString("id");
		String onoma = rs.getString("name");
		String age = rs.getString("age");
		String adress = rs.getString("address");
		String phone = rs.getString("phone");
		String amka = rs.getString("amka");		
		
		textField3.setText(adress); 
		textField1.setText(onoma); 
		textField2.setText(age); 
		textField5.setText(amka); 
		textField4.setText(phone); 
		}
		
	}
	catch (Exception e)
	{
	e.printStackTrace();
	}
	finally
	{
	
		try { if (rs != null) rs.close(); } catch (SQLException e) {
			e.printStackTrace(); }
		
		try { if (stmt != null) stmt.close(); } catch (SQLException e) {
			e.printStackTrace(); }
		
		try { if (conn != null) conn.close(); } catch (SQLException e) {
			e.printStackTrace(); }
	}
	
	
	
		
	}
	
	public void connectionSearch(){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
	try
	{
	
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		String connectionUrl = "jdbc:mysql://localhost:3306/mixaniki";
		String connectionUser = "root";
		String connectionPassword = "123456789";
		conn = DriverManager.getConnection(connectionUrl, connectionUser,
		connectionPassword);
		stmt = conn.createStatement();
		rs = stmt.executeQuery("SELECT * FROM Patients WHERE name LIKE '%"+textField.getText()+"%';");
		
		
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		while (rs.next())
		{
			
		
		String onoma = rs.getString("name");
		
		listModel.addElement(onoma);			
		}
		list.setModel(listModel);
		
	}
	catch (Exception e)
	{
	e.printStackTrace();
	}
	finally
	{
	
		try { if (rs != null) rs.close(); } catch (SQLException e) {
			e.printStackTrace(); }
		
		try { if (stmt != null) stmt.close(); } catch (SQLException e) {
			e.printStackTrace(); }
		
		try { if (conn != null) conn.close(); } catch (SQLException e) {
			e.printStackTrace(); }
	}
	
	
	
		
	}
	
	public void connectionInsert() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String connectionUrl = "jdbc:mysql://localhost:3306/mixaniki";
			String connectionUser = "root";
			String connectionPassword = "123456789";
			conn = DriverManager.getConnection(connectionUrl, connectionUser,
					connectionPassword);
			stmt = conn.createStatement();			
			String query="UPDATE `Patients` SET `name`='"+textField1.getText()+"', `age`='"+textField2.getText()+"', `address`='"+textField3.getText()+"', `phone`='"+textField4.getText()+"', `amka`='"+textField5.getText()+"' WHERE `id`='"+selectedPatientId+"';";
			stmt.executeUpdate(query);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void connectionDelete() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String connectionUrl = "jdbc:mysql://localhost:3306/mixaniki";
			String connectionUser = "root";
			String connectionPassword = "123456789";
			conn = DriverManager.getConnection(connectionUrl, connectionUser,
					connectionPassword);
			stmt = conn.createStatement();			
			String query="DELETE FROM `Patients` WHERE `id`='"+selectedPatientId+"';";
			stmt.executeUpdate(query);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
