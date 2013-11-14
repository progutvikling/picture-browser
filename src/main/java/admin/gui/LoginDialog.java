package admin.gui;

import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.*;
import admin.bll.AdminLoginBLL;

/**
 * This class is responsible for authenticating
 * users that want to log in to the administration
 * application.
 */

public class LoginDialog extends JDialog{

	private static final long serialVersionUID = -535959533872064544L;
	
	private JPanel mainPanel, pnlUsername, actions, pnlPassword;
	private JButton ok, cancel;
	private JLabel lblUsername, lblPassword;
	private JTextField username;
	private JPasswordField password;
	private ResourceBundle rb;
	private GridBagConstraints cs;
	private LoginAction loginAction;

	public LoginDialog(Frame parent){
		super(parent, true);
		initialize();	
		setLocationRelativeTo(parent);
	}

	private void initialize() {
		loginAction = new LoginAction();
		initializeResourceBundles();
		initializeUI();
	}

	private void initializeUI() {
		setTitle(rb.getString("login"));
		setSize(360, 150);
		setResizable(false);

		terminateWhenClosed();
		
		initializePanels();

		initializeGridBagConstraints();
		
		addUsername();
		addPassword();

		addOkButton();
		addCancelButton();
		
		addPanelsToMainPanel();

		add(mainPanel);
	}
	
	/**
	 * Set language to norwegian
	 */
	private void initializeResourceBundles() {
		Locale norwegian = new Locale("no_NO");
		rb = ResourceBundle.getBundle("Strings", norwegian);
	}
	
	/**
	 * If the dialog is closed, the application is terminated
	 */
	private void terminateWhenClosed() {
		addWindowListener(new WindowAdapter() {
			@Override public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	private void initializePanels() {
		actions = new JPanel();
		mainPanel = new JPanel();
		pnlUsername = new JPanel(new GridBagLayout());
		pnlPassword = new JPanel(new GridBagLayout());
	}
	
	private void initializeGridBagConstraints() {
		cs = new GridBagConstraints();
		cs.fill = GridBagConstraints.HORIZONTAL;
	}
	
	/**
	 * Initialize and add user name label and 
	 * text field to the user name panel
	 */
	private void addUsername() {
		lblUsername = new JLabel(rb.getString("username") + ": ");
		
		cs.gridx = 0;
		cs.gridy = 0;
		cs.gridwidth = 1;
		
		pnlUsername.add(lblUsername, cs);
		
		username = new JTextField(20);
		
		cs.gridx = 1;
		cs.gridy = 0;
		cs.gridwidth = 2;
		
		pnlUsername.add(username, cs);
	}
	
	/**
	 * Initialize and add password label and text
	 * field to the password panel 
	 */
	private void addPassword() {
		lblPassword = new JLabel(rb.getString("password") + ": ");
		
		cs.gridx = 0;
		cs.gridy = 1;
		cs.gridwidth = 1;
		
		pnlPassword.add(lblPassword, cs);
		
		password = new JPasswordField(20);
		password.addActionListener(loginAction);
		
		cs.gridx = 1;
		cs.gridy = 1;
		cs.gridwidth = 2;
		
		pnlPassword.add(password, cs);
	}
	
	/**
	 * Initialize and add the "Ok" button to
	 * the actions panel
	 */
	private void addOkButton() {
		ok = new JButton(rb.getString("login"));
		ok.addActionListener(loginAction);
		actions.add(ok);
	}
	
	/**
	 * Initialize and add the "Cancel" button
	 * to the actions panel
	 */
	private void addCancelButton() {
		cancel = new JButton(rb.getString("cancel"));
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(-1);
			}
		});

		actions.add(cancel);
	}

	private void addPanelsToMainPanel() {
		mainPanel.add(pnlUsername);
		mainPanel.add(pnlPassword);
		mainPanel.add(actions);
	}

	/**
	 * Authenticate user name and password.
	 * Display an error message if authentication fails.
	 */
	public class LoginAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent evt) {
			String user = username.getText();
			String pwd = new String(password.getPassword());
			AdminLoginBLL login = new AdminLoginBLL();
			if(login.checkusernameandpassword(user, pwd)){
				dispose();
			}
			else{
				JOptionPane.showMessageDialog(mainPanel, rb.getString("login_error"));
			}
		}
	}
}
