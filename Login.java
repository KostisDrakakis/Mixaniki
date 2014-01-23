package mixaniki;

import javax.swing.JFrame;
import javax.swing.JButton;


import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Login {

	private JFrame frmIatrikosFakelos;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextArea textArea;
	

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {		
		setFrame(new JFrame());
		getFrame().setBounds(100, 100, 450, 256);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmIatrikosFakelos.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Arrays.equals( "123456789".toCharArray(), passwordField.getPassword() ) )
				{
					Main.loggedInUser=textField.getText();
					
					switch (Main.loggedInUser)
					{
						case "Gram": case "Gia" : case "Nos" : 		
							try {
								Panel frame = new Panel();
								frame.setVisible(true);
								frmIatrikosFakelos.setVisible(false);
							} catch (Exception e) {
								e.printStackTrace();
							}
							break;
						default:
							textArea.setText("Wrong user");
					}
					return;
				}
				textArea.setText("Wrong password");
			}
		});
		btnNewButton.setBounds(10, 142, 227, 23);
		frmIatrikosFakelos.getContentPane().add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(131, 24, 106, 20);
		textField.setText("Gram");
		frmIatrikosFakelos.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(131, 58, 106, 20);
		passwordField.setText("123456789");
		frmIatrikosFakelos.getContentPane().add(passwordField);
		
		JLabel lblNewLabel = new JLabel("Password");
		lblNewLabel.setBounds(10, 61, 80, 14);
		frmIatrikosFakelos.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel1 = new JLabel("UserName");
		lblNewLabel1.setBounds(10, 27, 80, 14);
		frmIatrikosFakelos.getContentPane().add(lblNewLabel1);
		
		JTextArea txtrGrammateiaGramGiatros = new JTextArea();
		txtrGrammateiaGramGiatros.setEditable(false);
		txtrGrammateiaGramGiatros.setText("Grammateia: Gram\r\nGiatros: Gia\r\nNosokomos: Nos\r\nPassword:123456789");
		txtrGrammateiaGramGiatros.setBounds(266, 106, 168, 93);
		frmIatrikosFakelos.getContentPane().add(txtrGrammateiaGramGiatros);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(266, 22, 158, 73);
		frmIatrikosFakelos.getContentPane().add(textArea);
	}

	public JFrame getFrame() {
		return frmIatrikosFakelos;
	}

	public void setFrame(JFrame frame) {
		this.frmIatrikosFakelos = frame;
		frmIatrikosFakelos.setTitle("Iatrikos Fakelos");
	}
	
}
