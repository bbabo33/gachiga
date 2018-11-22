package kr.vo;

public class CarpoolVO {
	private int no;
	private String writer_id;
	private String start_date;
	private String start_place;
	private String start_place_name;
	private String start_time;
	private String end_place;
	private String end_place_name;
	private String smoke;
	private int money;
	private int user_cnt;
	private String post_type;
	private String add_info;
	private String reg_date;
	private int apply_cnt;

	public CarpoolVO() {
		super();
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getId() {
		return writer_id;
	}
	public void setId(String id) {
		this.writer_id = id;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getStart_place() {
		return start_place;
	}
	public void setStart_place(String start_place) {
		this.start_place = start_place;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_place() {
		return end_place;
	}
	public void setEnd_place(String end_place) {
		this.end_place = end_place;
	}
	public String getSmoke() {
		return smoke;
	}
	public void setSmoke(String smoke) {
		this.smoke = smoke;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getUser_cnt() {
		return user_cnt;
	}
	public void setUser_cnt(int user_cnt) {
		this.user_cnt = user_cnt;
	}
	public String getPost_type() {
		return post_type;
	}
	public void setPost_type(String post_type) {
		this.post_type = post_type;
	}
	public String getAdd_info() {
		return add_info;
	}
	public void setAdd_info(String add_info) {
		this.add_info = add_info;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
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
	public String getEnd_place_name() {
		return end_place_name;
	}
	public void setEnd_place_name(String end_place_name) {
		this.end_place_name = end_place_name;
	}
	public int getApply_cnt() {
		return apply_cnt;
	}
	public void setApply_cnt(int apply_cnt) {
		this.apply_cnt = apply_cnt;
	}
	
}
