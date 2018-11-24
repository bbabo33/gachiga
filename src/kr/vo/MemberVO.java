package kr.vo;

public class MemberVO {
	
   private String id;
   private String name;
   private String password;
   private String email;
   private String tel;
   private String addr;
   private String regDate;
   
   public MemberVO() {
      super();
      // TODO Auto-generated constructor stub
   }
   
   public MemberVO(String id, String password) {
	   this.id = id;
	   this.password = password;
   }
   
   public MemberVO(String id, String name, String password, String email, String tel, String addr,
		String regDate) {
	super();
	this.id = id;
	this.name = name;
	this.password = password;
	this.email = email;
	this.tel = tel;
	this.addr = addr;
	this.regDate = regDate;
}

public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getTel() {
      return tel;
   }

   public void setTel(String tel) {
      this.tel = tel;
   }

   public String getAddr() {
      return addr;
   }

   public void setAddr(String addr) {
      this.addr = addr;
   }

   public String getRegDate() {
      return regDate;
   }

   public void setRegDate(String regDate) {
      this.regDate = regDate;
   }
}