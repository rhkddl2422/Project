package edu.multi.ticket;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
@Repository("dao")
public class BoardDAO { // db와 연결할 클래스 ...
//전체게시물조회메소드
 public ArrayList<BoardVO>  getList( ) //ArrayList<BoardVO> 변수명으로 getList선언
 {
	    ArrayList<BoardVO> list = new ArrayList<BoardVO>();
	 try {
		 //1. jdbc driver 로드(ojdbc6.jar)
		 Class.forName("oracle.jdbc.driver.OracleDriver");
		 //2. db 정보 부여하고 db 연결
		 Connection con = DriverManager.getConnection
		 ("jdbc:oracle:thin:@localhost:1521:xe" , "scott", "TIGER");
		 //3. sql 정의 
		 PreparedStatement pt = con.prepareStatement("select * from board order by time desc");
		 //4. 전송 sql 실행 결과 이용
		 ResultSet rs = pt.executeQuery(); //sql파일을 가져온다. 
		 while(rs.next()) { //rs.next를 할때 하나씩 불러와서 vo에 저장해준다.
			 //select  레코드 있을 때까지 반복
			 BoardVO vo = new BoardVO();
//			 vo.setSeq(rs.getInt("seq"));
//			 vo.setTitle(rs.getString("title"));
//			 vo.setContents(rs.getString("contents"));	
//			 vo.setWriter(rs.getString("writer"));	
//			 vo.setTime(rs.getString("time"));	
//			 vo.setViewcount(rs.getInt("viewcount"));	
			 
			 vo.setSeq(rs.getInt("seq"));
			 vo.setT_name(rs.getString("t_name"));
			 vo.setContents(rs.getString("contents"));
			 vo.setId(rs.getString("id"));
			 vo.setRegister(rs.getString("register"));
			 vo.setViewcount(rs.getInt("viewcount"));
			 vo.setDeadline(rs.getString("deadline"));
			 vo.setBookdate(rs.getString("bookdate"));
			 vo.setPrice(rs.getInt("price"));
			
			 	 
			 list.add(vo);
		 }
		 //5. db 연결해제
		 rs.close();
		 pt.close();
		 con.close();
	 }catch(Exception e) {
		 e.printStackTrace();
	 }
	 return list;
	 
 }
 
 //게시물1개저장메소드
 public int  insertBoard(BoardVO vo ){
	 int result = 0;
	 try {
		 //1. jdbc driver 로드(ojdbc6.jar)
		 Class.forName("oracle.jdbc.driver.OracleDriver");
		 //2. db 정보 부여하고 db 연결
		 Connection con = DriverManager.getConnection
		 ("jdbc:oracle:thin:@localhost:1521:xe" , "scott", "TIGER");
		 //3. sql 정의 
		 String insertsql = 
		 "insert into board values((SELECT MAX(SEQ)+1 FROM BOARD) , "
				 +"?, ?, SYSDATE, ?,0)"; //? 값을 pt.setString같은 값들을 넣어준다.
		 PreparedStatement pt = con.prepareStatement (insertsql);
		 //4. 전송 sql 실행 결과 이용
		 pt.setString(1, vo.getT_name());
		 pt.setString(3, vo.getContents());
		 pt.setString(2, vo.getId());		
		 pt.setString(4, vo.getBookdate());
		 pt.setInt(4, vo.getPrice());
		 pt.setString(5, vo.getAccount());
			/*
			 * price id T_name contents bookdate
			 */
		result = pt.executeUpdate();
		 //5. db 연결해제
		 pt.close();
		 con.close();
	 }catch(Exception e) {
		 e.printStackTrace();
	 }
	 return result;
 }
 
 
 //1개의 게시물 검색
 public int searchBoard(BoardVO vo ){
	 int result = 0;
	 try {
		 //1. jdbc driver 로드(ojdbc6.jar)
		 Class.forName("oracle.jdbc.driver.OracleDriver");
		 //2. db 정보 부여하고 db 연결
		 Connection con = DriverManager.getConnection
		 ("jdbc:oracle:thin:@localhost:1521:xe" , "scott", "TIGER");
		 //3. sql 정의 
		 
		
		 String searchsql = "select * from board";
				 
		 PreparedStatement pt = con.prepareStatement (searchsql);
		 //4. 전송 sql 실행 결과 이용
		 pt.setString(1, vo.getT_name()); //상품명
		 pt.setString(2, vo.getId()); //회원닉네임		
		 pt.setInt(4, vo.getPrice()); //가격
		 pt.setString(4, vo.getBookdate()); // 마감기간
		result = pt.executeUpdate();
		 //5. db 연결해제
		 pt.close();
		 con.close();
	 }catch(Exception e) {
		 e.printStackTrace();
	 }
	 return result;
 }
 
 public int updateBoard (BoardVO vo ) //수정
 {
	 int result = 0;
	 try {
	 Class.forName("oracle.jdbc.driver.OracleDriver");
	 
	 Connection con = DriverManager.getConnection
			 ("jdbc:oracle:thin:@localhost:1521:xe" , "scott", "TIGER");
	
	 String updatesql = "update board set T_name=?, contents=?, price=?, bookdate=?, account where seq=?"; 
	 
	 PreparedStatement pt = con.prepareStatement (updatesql);
	 //4. 전송 sql 실행 결과 이용
	 pt.setString(1, vo.getT_name()); //상품명	
	 pt.setString(2, vo.getContents()); //내용
	 pt.setInt(3, vo.getPrice()); //가격
	 pt.setString(4, vo.getBookdate());
	 pt.setString(5,vo.getAccount());// 계좌번호
	 
	 
	 result = pt.executeUpdate();
	 
	 pt.close();
	 con.close();
	 } catch (Exception e){
		 e.printStackTrace();
		}

	 
	 return result;
	 
 }
 

 public int deleteBoard (int seq ){
	 int result = 0;
	 try {
	 Class.forName("oracle.jdbc.driver.OracleDriver");
	 
	 Connection con = DriverManager.getConnection
			 ("jdbc:oracle:thin:@localhost:1521:xe" , "scott", "TIGER");
	
	 String deletesql = "delete Board where seq=?"; 
	 
	 PreparedStatement pt = con.prepareStatement (deletesql);
	 //4. 전송 sql 실행 결과 이용
	 
	 pt.setInt(1, seq);
	 
	 result = pt.executeUpdate();
	 pt.close();
	 con.close();
	 } catch (Exception e){
		 e.printStackTrace();
		}

	 
	 return result;
	 
 }
 
 public BoardVO getDetail(int seq) {
     BoardVO vo = new BoardVO();
     try {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection
              ("jdbc:oracle:thin:@localhost:1521:xe","scott","TIGER");
        
        String updatesql = "update board set viewcount = viewcount+1 where seq=?";
        PreparedStatement pt0 = con.prepareStatement(updatesql);
        pt0.setInt(1, seq);
        pt0.executeUpdate();
        
        String selectsql = "select * from board where seq=?";
        PreparedStatement pt = con.prepareStatement(selectsql);
        pt.setInt(1, seq);
        ResultSet rs = pt.executeQuery();
        
        if(rs.next()) {
           vo.setSeq(rs.getInt("seq"));
           vo.setT_name(rs.getString("title"));
           vo.setContents(rs.getString("contents"));
           vo.setDeadline(rs.getString("deadline"));
           vo.setBookdate(rs.getString("bookdate"));
           vo.setViewcount(rs.getInt("viewcount"));
           vo.setRegister(rs.getString("register"));
           vo.setPrice(rs.getInt("price"));
           vo.setAccount(rs.getString("account"));
        }
        
        rs.close();
        pt0.close();
        pt.close();
        con.close();
     } catch (Exception e) {
        e.printStackTrace();
     }
     return vo;
  }
 



 }
 




