package client.bll;

public interface Refreshable {
	public void refresh();
	public void addRefreshListener(RefreshListener listener);
	public boolean removeRefreshListener(RefreshListener listener);
	public void setdelay(double delay);
}
