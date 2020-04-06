package testBoard;

public class TestDTO {
	private int num;
	private int Count;
	private String title;
	public int getCount() {
		return Count;
	}
	public void setCount(int count) {
		Count = count;
	}
	private String pdate;
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPdate() {
		return pdate;
	}
	public void setPdate(String pdate) {
		this.pdate = pdate;
	}
}
