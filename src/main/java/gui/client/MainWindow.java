package gui.client;

import java.awt.BorderLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow extends JFrame {

	private static GraphicsDevice device = GraphicsEnvironment
			.getLocalGraphicsEnvironment().getScreenDevices()[0];
	private static final long serialVersionUID = -4902720969305740099L;

	public MainWindow(JPanel panel) {
		this.setTitle("Picture Browser");
		this.setLayout(new BorderLayout());
		this.add(panel, BorderLayout.CENTER);
	}

	public void startFullscreen ()
	{
		if (!device.isFullScreenSupported ())
		{
			throw new UnsupportedOperationException ("Fullscreen mode is unsupported.");
		}

		this.addKeyListener(new ExitListener());

		try {
			device.setFullScreenWindow (this);
		} 
		catch (Exception e) {
			device.setFullScreenWindow(null);
			System.out.println("could not enable fullscreen exclusive mode");
		}
	}

	public void endFullscreenMode ()
	{
		device.setFullScreenWindow (null);
		System.exit(0);
	}

	private class ExitListener implements KeyListener
	{
		public void keyTyped (KeyEvent e)
		{
			endFullscreenMode ();
		}

		public void keyPressed (KeyEvent e)  {}
		public void keyReleased (KeyEvent e) {}
	}
}
