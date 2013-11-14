package admin.bll;

import java.util.List;

import javax.swing.JPanel;

import admin.gui.BlockingPicturesPanel;
import admin.gui.IBlockingPicturesPanelHandler;

import common.dal.IImageStore;
import common.dal.Image;

public class BlockingPicturesController implements IBlockingPicturesPanelHandler {

	IImageStore store;
	
	public BlockingPicturesController(IImageStore store) {
		this.store = store;
	}
	
	@Override
	public List<Image> getImages() {
		return store.getLast(100);
	}

	@Override
	public void blockImage(Image img) {
		store.block(img.getID());

	}

	@Override
	public void unblockImage(Image img) {
		store.unBlock(img.getID());
	}
	
	
	public JPanel getView() {
		return new BlockingPicturesPanel(this);
	}

}
