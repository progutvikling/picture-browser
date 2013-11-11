package admin.gui;

import java.awt.Color;
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
import javax.swing.border.LineBorder;

import admin.bll.AdminLoginBLL;


public class LoginDialog extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = -535959533872064544L;
	JPanel frame, panel, buttons, panel2;
	JButton ok, avbryt;
	JLabel brukernavn, passord;
	JTextField brukernavninput;
	JPasswordField passordinput;
	private ResourceBundle rb;
	
	public LoginDialog(Frame parent){
		super(parent, true);
		
		Locale norwegian = new Locale("no_NO");
		this.rb = ResourceBundle.getBundle("Strings", norwegian);
		
		setTitle(rb.getString("login"));
		
		setSize(360, 150);
        setResizable(false);
        //Vist man "krysser ut dialogen" s�� avslutter programmet
        addWindowListener(new WindowAdapter() {
            @Override public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

		LoginAction loginAction = new LoginAction();

        //Legger til labels og tekstfields for logininputen.
		frame = new JPanel();
		panel = new JPanel(new GridBagLayout());
		panel2 = new JPanel(new GridBagLayout());
		GridBagConstraints cs = new GridBagConstraints();
		cs.fill = GridBagConstraints.HORIZONTAL;
		brukernavn = new JLabel(rb.getString("username") + ": ");
		cs.gridx = 0;
		cs.gridy = 0;
		cs.gridwidth = 1;
		panel.add(brukernavn, cs);
		brukernavninput = new JTextField(20);
		cs.gridx = 1;
		cs.gridy = 0;
		cs.gridwidth = 2;
		panel.add(brukernavninput, cs);
		passord = new JLabel(rb.getString("password") + ": ");
		cs.gridx = 0;
		cs.gridy = 1;
		cs.gridwidth = 1;
		panel2.add(passord, cs);
		passordinput = new JPasswordField(20);
		passordinput.addActionListener(loginAction);
		cs.gridx = 1;
		cs.gridy = 1;
		cs.gridwidth = 2;
		panel2.add(passordinput, cs);
		
		//Lagger knappene og logikken deres.
		ok = new JButton(rb.getString("login"));
		//Vist bruker trykker login, sjekker programmet om input var korrekt.
		ok.addActionListener(loginAction);
		avbryt = new JButton(rb.getString("cancel"));
		//Avslutter programmet
		avbryt.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.exit(-1);
			}
		});
		
		//Legger alle panel sammen og legger dei til i dialog vinduet.
		buttons = new JPanel();
		buttons.add(ok);
		buttons.add(avbryt);
		panel.setBorder(new LineBorder(Color.GRAY));
		panel2.setBorder(new LineBorder(Color.GRAY));
		frame.add(panel);
		frame.add(panel2);
		frame.add(buttons);
		add(frame);
        setLocationRelativeTo(parent);
	}

	public class LoginAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent evt) {
			String bruker = brukernavninput.getText();
			String pass = new String(passordinput.getPassword());
			AdminLoginBLL login = new AdminLoginBLL();
			if(login.checkusernameandpassword(pass, bruker)){
				dispose();
			}
			else{
				JOptionPane.showMessageDialog(frame, rb.getString("login_error"));
			}
		}
	}
}
