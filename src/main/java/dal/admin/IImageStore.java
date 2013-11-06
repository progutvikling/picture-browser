package dal.admin;

import java.util.ArrayList;


public interface IImageStore {
	public boolean insert(Image img);
	public ArrayList<Image> getLast(int numberOfRows);
	public boolean block(int id);
	public boolean unBlock(int id);

}
