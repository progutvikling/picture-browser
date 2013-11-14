package client.bll;

public interface Refresher {
	public void refresh();
	public void addRefreshListener(RefreshListener listener);
	public boolean removeRefreshListener(RefreshListener listener);
}
