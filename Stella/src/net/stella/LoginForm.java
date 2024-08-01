package net.stella;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class LoginForm extends JFrame {
	private JobDAO jobDAO;
	private JTextField usernameField;
	private JPasswordField passwordField;
	
	public LoginForm() {
		jobDAO = new JobDAO();
		
		setTitle("Login");
		setSize(300,200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(3, 2));
		
		add(new JLabel("Username:"));
		usernameField = new JTextField();
		add(usernameField);
		
		add(new JLabel("Password:"));
		passwordField = new JPasswordField();
		add(passwordField);

		JButton loginButton = new JButton("Login");
		add(new JLabel()); // Empty Cell
		add(loginButton);
		
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText();
				String password = new String(passwordField.getPassword());
				Estimator estimator = jobDAO.getEstimator(username, password);
				if (estimator != null) {
					new StellaUI(estimator).setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Invalid username or password");
				}
			}
		});
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new LoginForm().setVisible(true);
		});
	}

}
