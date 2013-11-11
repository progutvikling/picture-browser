package common.dal;

import java.util.ArrayList;


public interface IImageStore {
	public boolean insert(Image img);
	public ArrayList<Image> getLast(int numberOfRows);
	public boolean block(long id);
	public boolean unBlock(long id);
	public String getLastAsJson(int numberOfImagesToServe);
	
	/**
	 * Deletes a keyword from images table and returns true if success.
	 */
	public boolean deleteAllWithKeyword(String keyword);
}
