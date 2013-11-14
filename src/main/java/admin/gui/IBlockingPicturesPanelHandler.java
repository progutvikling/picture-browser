package admin.gui;

import java.util.List;

import common.dal.Image;

public interface IBlockingPicturesPanelHandler {
	public List<Image> getImages();
	public void blockImage(Image img);
	public void unblockImage(Image img);
}
