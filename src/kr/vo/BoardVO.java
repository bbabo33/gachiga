package kr.vo;

public class BoardVO {

	private int board_no;
	private String title;
	private String content;
	private String id;
	private int cnt;
	private String regDate;
	private String post_type;

	public BoardVO() {
		super();
	}

	public BoardVO(int board_no, String title, String content, String id, int cnt, String regDate, String post_type) {
		this.board_no = board_no;
		this.title = title;
		this.content = content;
		this.id = id;
		this.cnt = cnt;
		this.regDate = regDate;
		this.post_type = post_type;
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

	public String getPost_type() {
		return post_type;
	}

	public void setPost_type(String post_type) {
		this.post_type = post_type;
	}

}
