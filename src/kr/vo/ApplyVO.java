package kr.vo;

public class ApplyVO {
   
   private String id;
   private int post_no;
   private int user_cnt;
   private String status;
   private String writer_id;
   
   public ApplyVO() {
      super();
   }

   public ApplyVO(String id, int post_no, int user_cnt, String status, String writer_id) {
      super();
      this.id = id;
      this.post_no = post_no;
      this.user_cnt = user_cnt;
      this.status = status;
      this.writer_id = writer_id;
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
   
   

}