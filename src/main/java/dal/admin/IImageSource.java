package dal.admin;

import java.util.ArrayList;

public interface IImageSource {
	public ArrayList<Image> getByKeyword(String keyword);
}
