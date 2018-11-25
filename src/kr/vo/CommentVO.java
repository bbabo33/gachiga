package kr.vo;

public class CommentVO {

	private int no;
	private String writer;
	private int post_no;
	private String content;
	private String reg_date;
	private String reg_date2;

	public CommentVO() {
		super();
	}

	public CommentVO(int no, String writer, int post_no, String content, String reg_date) {
		super();
		this.no = no;
		this.writer = writer;
		this.post_no = post_no;
		this.content = content;
		this.reg_date = reg_date;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getPost_no() {
		return post_no;
	}

	public void setPost_no(int post_no) {
		this.post_no = post_no;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public String getReg_date2() {
		return reg_date2;
	}

	public void setReg_date2(String reg_date2) {
		this.reg_date2 = reg_date2;
	}

}
