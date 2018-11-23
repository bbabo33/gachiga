package kr.vo;

public class BoardVO {
	
	private int board_no;
	private String title;
	private String content;
	private String id;
	private int cnt;
	private String regDate;

	public BoardVO() {
		super();
	}

	public BoardVO(int board_no, String title, String content, String id, int cnt, String regDate) {

		this.board_no = board_no;
		this.title = title;
		
		this.content = content;
		
		this.id = id;
		this.cnt = cnt;
		this.regDate = regDate;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "BoardVO [board_no=" + board_no + ", title=" + title + ", content="
				+ content + ", id=" + id + ", cnt=" + cnt + ", regDate=" + regDate + "]";
	}
	
	

}
