package kr.vo;

public class ApplyVO {

	private String id;
	private int post_no;
	private int user_cnt;
	private String status;
	private String writer_id;
	private String start_place_name;
	private String start_time;
	private String start_date;
	private String end_place_name;
	private String type;

	public ApplyVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ApplyVO(String id, int post_no, int user_cnt, String status, String writer_id, String start_place_name,
			String start_time, String start_date, String end_place_name, String type) {
		super();
		this.id = id;
		this.post_no = post_no;
		this.user_cnt = user_cnt;
		this.status = status;
		this.writer_id = writer_id;
		this.start_place_name = start_place_name;
		this.start_time = start_time;
		this.start_date = start_date;
		this.end_place_name = end_place_name;
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPost_no() {
		return post_no;
	}

	public void setPost_no(int post_no) {
		this.post_no = post_no;
	}

	public int getUser_cnt() {
		return user_cnt;
	}

	public void setUser_cnt(int user_cnt) {
		this.user_cnt = user_cnt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getWriter_id() {
		return writer_id;
	}

	public void setWriter_id(String writer_id) {
		this.writer_id = writer_id;
	}

	public String getStart_place_name() {
		return start_place_name;
	}

	public void setStart_place_name(String start_place_name) {
		this.start_place_name = start_place_name;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_place_name() {
		return end_place_name;
	}

	public void setEnd_place_name(String end_place_name) {
		this.end_place_name = end_place_name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

}