package kr.vo;

public class BoardVO {
	private int board_no;
	private String title;
	private String id;
	private String content;
	private int cnt;
	private String regDate;

	public BoardVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getBoard_no() {
		return board_no;
	}

	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public BoardVO(int board_no, String title, String id, String content, int cnt, String regDate) {
		super();
		this.board_no = board_no;
		this.title = title;
		this.id = id;
		this.content = content;
		this.cnt = cnt;
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "BoardVO [board_no=" + board_no + ", title=" + title + ", id=" + id + ", content=" + content + ", cnt="
				+ cnt + ", regDate=" + regDate + "]";
	}
}