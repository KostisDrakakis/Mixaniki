package mixaniki;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Panel extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFrame thisFrame;
	/**
	 * Create the frame.
	 */
	public Panel() {
		setTitle("Iatrikos Fakelos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 217, 188);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		thisFrame =this;
		JButton btnAddNewFolder = new JButton("Add New Patient");
		btnAddNewFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				AddPatient frame = new AddPatient();
				frame.setVisible(true);
				thisFrame.setVisible(false);
			}
		});
		btnAddNewFolder.setBounds(24, 31, 145, 23);
		contentPane.add(btnAddNewFolder);
		
		JButton btnNewButton = new JButton("Show Patient");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				ShowPatient frame = new ShowPatient();
				frame.setVisible(true);
				thisFrame.setVisible(false);
			}
		});
		btnNewButton.setBounds(24, 65, 145, 23);
		contentPane.add(btnNewButton);
		
		JButton btnEditPatient = new JButton("Edit Patient");
		btnEditPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditPatient frame = new EditPatient();
				frame.setVisible(true);
				thisFrame.setVisible(false);
			}
		});
		btnEditPatient.setBounds(24, 99, 145, 23);
		contentPane.add(btnEditPatient);
		
		
		
		if (Main.loggedInUser.equals("Nos") || Main.loggedInUser.equals("Gia"))
		{
			btnAddNewFolder.setEnabled(false);
		}
		
		if (Main.loggedInUser.equals("Nos"))
		{
			btnEditPatient.setEnabled(false);
		}
	}

}
