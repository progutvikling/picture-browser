package fetcher.dal;

import java.util.List;

import common.dal.Image;

public interface IImageSource {
	public List<Image> getByKeyword(String keyword, int count);
}
