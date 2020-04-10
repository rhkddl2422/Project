package edu.multi.ticket;
//입력 -vo(저장 암시)-db
public class BoardVO {//테이블 1개 레코드 
int seq; // 1개 컬럼 
String T_name; //상품명
String contents; //내용
String id; // 회원명 
String register; // 등록일
int viewcount; //조회수
String deadline ; //마감일 
String bookdate;//예약기간
int price;
String account;//계좌번호

public String getAccount() {
	return account;
}
public void setAccount(String account) {
	this.account = account;
}
public int getSeq() {
	return seq;
}
public void setSeq(int seq) {
	this.seq = seq;
}
public String getT_name() {
	return T_name;
}
public void setT_name(String t_name) {
	T_name = t_name;
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





}
