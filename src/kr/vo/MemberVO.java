package kr.vo;

public class MemberVO {
	
   private String id;
   private String name;
   private String password;
   private String email;
   private String birth;
   private String tel;
   private int age;
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
   
   public MemberVO(String id, String name, String password, String email, String birth, String tel, int age,
         String addr, String regDate) {
      super();
      this.id = id;
      this.name = name;
      this.password = password;
      this.email = email;
      this.birth = birth;
      this.tel = tel;
      this.age = age;
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

   public String getBirth() {
      return birth;
   }

   public void setBirth(String birth) {
      this.birth = birth;
   }

   public String getTel() {
      return tel;
   }

   public void setTel(String tel) {
      this.tel = tel;
   }

   public int getAge() {
      return age;
   }

   public void setAge(int age) {
      this.age = age;
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

   @Override
   public String toString() {
      return "MemberVO [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", birth="
            + birth + ", tel=" + tel + ", age=" + age + ", addr=" + addr + ", regDate=" + regDate + "]";
   }
}