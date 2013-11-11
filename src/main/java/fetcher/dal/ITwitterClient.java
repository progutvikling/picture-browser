package fetcher.dal;

public interface ITwitterClient {
	public String search(String keyword, long maxId);
}
