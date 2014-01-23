package mixaniki;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddPatient extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField1;
	private JTextField textField2;
	private JTextField textField3;
	private JTextField textField4;
	private JButton btnNewButton;
	private JButton btnClose;
	private JFrame thisFrame;
	

	/**
	 * Create the frame.
	 */
	public AddPatient() {
		thisFrame=this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(239, 34, 164, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(24, 34, 156, 14);
		contentPane.add(lblName);

		JLabel lblAge = new JLabel("Age");
		lblAge.setBounds(24, 65, 156, 14);
		contentPane.add(lblAge);

		textField1 = new JTextField();
		textField1.setColumns(10);
		textField1.setBounds(239, 65, 164, 20);
		contentPane.add(textField1);

		JLabel lblAdresss = new JLabel("Adress");
		lblAdresss.setBounds(24, 96, 156, 14);
		contentPane.add(lblAdresss);

		textField2 = new JTextField();
		textField2.setColumns(10);
		textField2.setBounds(239, 96, 164, 20);
		contentPane.add(textField2);

		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(24, 127, 156, 14);
		contentPane.add(lblPhone);

		textField3 = new JTextField();
		textField3.setColumns(10);
		textField3.setBounds(239, 127, 164, 20);
		contentPane.add(textField3);

		JLabel lblAmka = new JLabel("AMKA");
		lblAmka.setBounds(23, 155, 157, 14);
		contentPane.add(lblAmka);

		textField4 = new JTextField();
		textField4.setColumns(10);
		textField4.setBounds(238, 155, 165, 20);
		contentPane.add(textField4);

		btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection();				
			}
		});
		btnNewButton.setBounds(163, 211, 89, 23);
		contentPane.add(btnNewButton);
		
		btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Panel frame = new Panel();
				frame.setVisible(true);
				thisFrame.setVisible(false);
			}
		});
		btnClose.setBounds(274, 211, 89, 23);
		contentPane.add(btnClose);
	}

	public final void connection() {
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
			String query="INSERT INTO `Patients` (`name`, `age`, `address`, `phone`, `amka`) VALUES ('"+textField.getText()+"', '"+textField1.getText()+"', '"+textField2.getText()+"', '"+textField3.getText()+"', '"+textField4.getText()+"');";
			stmt.executeUpdate(query);
			
			
			textField.setText("");
			textField1.setText("");
			textField2.setText("");
			textField3.setText("");
			textField4.setText("");
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
