package admin;

import java.util.ArrayList;

import javax.swing.JPanel;

import server.bll.ImageServer;
import admin.bll.ManageConfigsController;
import admin.bll.ManageKeywordsController;
import admin.gui.MainWindow;

/**
 * This class sets up the main window with all its tabs.
 * Add lines to this class when adding more admin panels!
 */
public class MainController {
	public static void main(String[] args) {
		
		ImageServer server = new ImageServer();
		server.start();
		
		ManageKeywordsController manageKeywords = new ManageKeywordsController();
		ManageConfigsController manageConfigs = new ManageConfigsController();
		// BlockingPictures blockingpictures = new BlockingPictures();
		
		ArrayList<JPanel> panels = new ArrayList<JPanel>();
		panels.add(manageKeywords.getView());
		panels.add(manageConfigs.getView());
		// panels.add(blockingpictures);
		
		MainWindow wnd = new MainWindow(panels);
		wnd.setVisible(true);
		wnd.setDefaultCloseOperation(MainWindow.EXIT_ON_CLOSE);
	}
}
