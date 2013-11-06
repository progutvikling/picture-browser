package gui.client;

import java.awt.BorderLayout;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Window;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class MainWindow extends JFrame {

	private static GraphicsDevice device;
	private JPanel panel;
	private static final long serialVersionUID = -4902720969305740099L;

	public MainWindow(JPanel panel) {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		device = ge.getDefaultScreenDevice();
		this.panel = panel;
		this.setTitle("Picture Browser");
		this.setLayout(new BorderLayout());
		this.add(panel, BorderLayout.CENTER);
		this.setKeyBinding();
	}
	
	private void setKeyBinding() {
		panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE,0),"escapeDown");
		panel.getActionMap().put("escapeDown",new AbstractAction() {
		    private static final long serialVersionUID = 1l;
		    @Override public void actionPerformed(ActionEvent e) {
		        endFullScreenMode();
		    }
		});
	}
	
	public void setFullScreenMode(DisplayMode dm){
		this.setUndecorated(true);
		this.setResizable(false);
		device.setFullScreenWindow(this);
		if(device.isDisplayChangeSupported() && dm!=null){
			try{
				device.setDisplayMode(dm);
			}
			catch(Exception ex){}
		}

	}
	
	public void endFullScreenMode(){
		Window w = device.getFullScreenWindow();
		if(w != null){
			w.dispose();
		}
		device.setFullScreenWindow(null);
	}
}
