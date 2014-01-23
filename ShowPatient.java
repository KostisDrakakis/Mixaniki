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

public class ShowPatient extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JList<String> list;
	private JList<String> listLeft;
	private JList<String> listRight;
	private JButton btnShowInfo;
	private JLabel lblName;
	private JLabel lblAge;
	private JLabel lblAdress;
	private JLabel lblPhone;
	private JLabel lblAmka;
	private JLabel labelAmka;
	private JLabel labelPhone;
	private JLabel labelAdress;
	private JLabel labelAge;
	private JLabel labelName;
	private JButton btnBack;
	private String selectedPatient;
	private String selectedPatientId;
	private JTextField textField1;
	private JLabel lblInsertNew;
	private JLabel lblType;
	private JTextField textField2;
	private JLabel lblComment;
	private JButton btnNewButton;
	private JLabel label;
	private JLabel label1;
	private JFrame thisFrame;
	
	/**
	 * Create the frame.
	 */
	public ShowPatient() {
		thisFrame =this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 747, 302);
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
				connectionHistory();
				
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
		
		labelAmka = new JLabel("");
		labelAmka.setBounds(291, 188, 89, 14);
		contentPane.add(labelAmka);
		
		labelPhone = new JLabel("");
		labelPhone.setBounds(291, 160, 89, 14);
		contentPane.add(labelPhone);
		
		labelAdress = new JLabel("");
		labelAdress.setBounds(291, 135, 89, 14);
		contentPane.add(labelAdress);
		
		labelAge = new JLabel("");
		labelAge.setBounds(291, 108, 89, 14);
		contentPane.add(labelAge);
		
		labelName = new JLabel("");
		labelName.setBounds(291, 83, 89, 14);
		contentPane.add(labelName);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Panel frame = new Panel();
				frame.setVisible(true);
				thisFrame.setVisible(false);
			}
		});
		btnBack.setBounds(247, 227, 89, 23);
		contentPane.add(btnBack);
		
		listLeft = new JList<String>();
		listLeft.setBounds(371, 37, 63, 176);
		contentPane.add(listLeft);
		
		listRight = new JList<String>();
		listRight.setBounds(444, 37, 63, 176);
		contentPane.add(listRight);
		
		textField1 = new JTextField();
		textField1.setBounds(624, 57, 86, 20);
		contentPane.add(textField1);
		textField1.setColumns(10);
		
		lblInsertNew = new JLabel("Insert New");
		lblInsertNew.setBounds(542, 38, 145, 14);
		contentPane.add(lblInsertNew);
		
		lblType = new JLabel("Type");
		lblType.setBounds(542, 60, 86, 14);
		contentPane.add(lblType);
		
		textField2 = new JTextField();
		textField2.setColumns(10);
		textField2.setBounds(624, 83, 86, 20);
		contentPane.add(textField2);
		
		lblComment = new JLabel("Comment");
		lblComment.setBounds(542, 86, 86, 14);
		contentPane.add(lblComment);
		
		btnNewButton = new JButton("Insert");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connectionInsert();
				connectionHistory();
			}
		});
		btnNewButton.setBounds(557, 131, 89, 23);
		contentPane.add(btnNewButton);
		
		label = new JLabel("Type");
		label.setBounds(371, 9, 86, 14);
		contentPane.add(label);
		
		label1 = new JLabel("Comment");
		label1.setBounds(444, 9, 86, 14);
		contentPane.add(label1);
		
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
	
	
	public final void connectionInfo(){
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
		
		labelAdress.setText(adress); 
		labelName.setText(onoma); 
		labelAge.setText(age); 
		labelAmka.setText(amka); 
		labelPhone.setText(phone); 
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
	
	public final void connectionSearch(){
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
	
	public final void connectionHistory(){
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
		rs = stmt.executeQuery("SELECT * FROM Folders WHERE id = '"+selectedPatientId+"';");
		
		
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		DefaultListModel<String> listModel1 = new DefaultListModel<String>();
		while (rs.next())
		{
			
		
		String type = rs.getString("type");		
		listModel.addElement(type);		
		
		String comment = rs.getString("comment");		
		listModel1.addElement(comment);		
		}
		listLeft.setModel(listModel);
		listRight.setModel(listModel1);
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
	public final void connectionInsert() {
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
			String query="INSERT INTO `Folders` (`type`, `comment`,`patientid`) VALUES ('"+textField1.getText()+"', '"+textField2.getText()+"', '"+selectedPatientId+"');";
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
