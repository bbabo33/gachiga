package kr.vo;
 public class CarVO {
	
	private String id;
	private String carName;
	private String carNo;
	private String smoke;
	private int rideNo;
	private String direct;  //보험(대인배상)
	
	public CarVO() {
		super();
		// TODO Auto-generated constructor stub
	}
 	public CarVO(String id, String carName, String carNo, String smoke, int rideNo, String direct) {
		super();
		this.id = id;
		this.carName = carName;
		this.carNo = carNo;
		this.smoke = smoke;
		this.rideNo = rideNo;
		this.direct = direct;
	}
 	public String getId() {
		return id;
	}
 	public void setId(String id) {
		this.id = id;
	}
 	public String getCarName() {
		return carName;
	}
 	public void setCarName(String carName) {
		this.carName = carName;
	}
 	public String getCarNo() {
		return carNo;
	}
 	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
 	public String getSmoke() {
		return smoke;
	}
 	public void setSmoke(String smoke) {
		this.smoke = smoke;
	}
 	public int getrideNo() {
		return rideNo;
	}
 	public void setrideNo(int rideNo) {
		this.rideNo = rideNo;
	}
 	public String getDirect() {
		return direct;
	}
 	public void setDirect(String direct) {
		this.direct = direct;
	}
 	@Override
	public String toString() {
		return "CarVO [id=" + id + ", carName=" + carName + ", carNo=" + carNo + ", smoke=" + smoke + ", rideNo="
				+ rideNo + ", direct=" + direct + "]";
 	}	
 }