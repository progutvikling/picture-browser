package dal.admin;

import java.util.List;

public interface IImageSource {
	public List<Image> getByKeyword(String keyword, int count);
}
