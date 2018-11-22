package kr.vo;

public class CommentVO {

	private int no;
	private String writer;
	private int post_no;
	private String content;
	private String RegDate;

	public CommentVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommentVO(int no, String writer, int post_no, String content, String RegDate) {
		super();
		this.no = no;
		this.writer = writer;
		this.post_no = post_no;
		this.content = content;
		this.RegDate = RegDate;
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

	public String getRegDate() {
		return RegDate;
	}

	public void setRegDate(String regDate) {
		RegDate = regDate;
	}

	@Override
	public String toString() {
		return "CommentVO [no=" + no + ", writer=" + writer + ", post_no=" + post_no + ", content=" + content
				+ ", RegDate=" + RegDate + "]";
	}
}
