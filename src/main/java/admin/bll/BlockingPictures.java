package admin.bll;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingWorker;

import common.dal.IImageStore;
import common.dal.Image;
import common.dal.StoreFactory;

public class BlockingPictures extends JPanel implements MouseListener {

	public IImageStore storeblocking = StoreFactory.getImageStore();
	/**
	 * 
	 */
	private static final long serialVersionUID = 6747143681976968595L;

	private int kol = 4;
	private IImageStore imagestore;
	private JPanel picPanel;
	private loadInBackground loadImage;
	private int imageLength;
	private int storedLabel = 0;
	private JLabel temp[];
	private List<Image> lImagesFromLoad;

	public BlockingPictures(IImageStore imagestore) {
		this.setName("Block");
		this.imagestore = imagestore;
		lImagesFromLoad = imagestore.getLast(100);
		picPanel = new JPanel();
		loadImage = new loadInBackground(imagestore, this);
		loadImage.execute();

		/**
		 * Burde ikkje gjør dette sidde eg benytter getLast() også i
		 * loadInBavkgroun klassen.Dobbelt med bilder. Fant ingen god måte å få
		 * antall bilder, alltid hentet før klassen var ferdig med å laste
		 * bilder...
		 * 
		 */
		imageLength = lImagesFromLoad.size();
		temp = new JLabel[imageLength];
		
		
		JScrollPane scrollpane = new JScrollPane(picPanel);
		scrollpane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollpane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollpane.getVerticalScrollBar().setUnitIncrement(16);
		scrollpane.setBounds(getVisibleRect());
		this.setLayout(new BorderLayout());
		this.add(scrollpane, BorderLayout.CENTER);
		setVisible(true);

	}

	public void setLabel(List<JLabel> l) {
		setPanel();
		JLabel imageIconLabel = new JLabel();
		imageIconLabel = l.get(0);
		temp[storedLabel] = imageIconLabel;
		temp[storedLabel].addMouseListener(createMouseListener());
		picPanel.add(temp[storedLabel]);
		storedLabel++;
		System.out.println("storedlable: " + storedLabel);
	}

	public void setPanel() {
		lImagesFromLoad = loadImage.getImage();
		// imageSize = lImagesFromLoad.size();
		// System.out.println(imageSize);
		picPanel.setLayout(new GridLayout((imageLength / kol) + 1, kol, 5, 5));
	}

	public MouseAdapter createMouseListener() {

		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JLabel coverLabel = (JLabel) e.getSource();
				for (int i = 0; i < storedLabel; i++) {
					if (e.getSource().equals(temp[i])) {
						if (!coverLabel.isEnabled()) {
							coverLabel.setEnabled(true);
							storeblocking.unBlock(lImagesFromLoad.get(i)
									.getID());
							System.out.println("unblock "
									+ lImagesFromLoad.get(i).getID());

						} else {
							coverLabel.setEnabled(false);
							// System.out.println("----------------------image id: "+lImagesFromLoad.get(i).getID());
							storeblocking.block(lImagesFromLoad.get(i).getID());
							System.out.println("block "
									+ lImagesFromLoad.get(i).getID());

						}
					}

				}

			}
		};

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
