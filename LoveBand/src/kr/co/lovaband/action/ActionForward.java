package kr.co.lovaband.action;

public class ActionForward {
	private String path;
	// true -> sendRedirect 처리 , false -> forward 처리
	private boolean isRedirect;
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
}