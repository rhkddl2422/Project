package edu.multi.member;
//입력 -vo(저장 암시)-db
public class BoardVO {//테이블 1개 레코드 
int seq; // 1개 컬럼 
String t_name; //상품명
String contents; //내용
String id; // 회원명 
String register; // 등록일
int viewcount;
 
String deadline ; //마감일 
String bookdate;//예약기간
int price;
String account;//계좌번호


@Override
public String toString() {
	return "BoardVO [seq=" + seq + ", t_name=" + t_name + ", contents=" + contents + ", id=" + id + ", register="
			+ register + ", viewcount=" + viewcount + ", deadline=" + deadline + ", bookdate=" + bookdate + ", price="
			+ price + ", account=" + account + "]";
}


public int getSeq() {
	return seq;
}


public void setSeq(int seq) {
	this.seq = seq;
}


public String getT_name() {
	return t_name;
}


public void setT_name(String t_name) {
	this.t_name = t_name;
}


public String getContents() {
	return contents;
}


public void setContents(String contents) {
	this.contents = contents;
}


public String getId() {
	return id;
}


public void setId(String id) {
	this.id = id;
}


public String getRegister() {
	return register;
}


public void setRegister(String register) {
	this.register = register;
}


public int getViewcount() {
	return viewcount;
}


public void setViewcount(int viewcount) {
	this.viewcount = viewcount;
}


public String getDeadline() {
	return deadline;
}


public void setDeadline(String deadline) {
	this.deadline = deadline;
}


public String getBookdate() {
	return bookdate;
}


public void setBookdate(String bookdate) {
	this.bookdate = bookdate;
}


public int getPrice() {
	return price;
}


public void setPrice(int price) {
	this.price = price;
}


public String getAccount() {
	return account;
}


public void setAccount(String account) {
	this.account = account;
}




}