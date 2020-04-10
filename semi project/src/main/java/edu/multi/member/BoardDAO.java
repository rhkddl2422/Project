package edu.multi.member;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.ModelAndView;

@Repository("dao")
public class BoardDAO { // db와 연결할 클래스 ...
//전체게시물조회메소드
	@Autowired
	SqlSession session;

	public ArrayList<BoardVO> getList() // ArrayList<BoardVO> 변수명으로로 getList선언
	{
		return null;
	}

	public List<BoardVO> getallticket() {
		System.out.println(2);
		List<BoardVO> list = session.selectList("allticket");
		System.out.println(list.size());
		return list;
	}
	
		
	public BoardVO getOneticket(int seq) {
		BoardVO vo = session.selectOne("oneticket", seq);
		return vo;
	} 
	 
	
	 
	/*
	 * public List<EmpVO> getIdEmp(){ List<EmpVO> list =
	 * session.selectList("idemp"); return list; }
	 */

	public void newticket(BoardVO vo) {
		System.out.println(session);
		session.insert("newticket", vo);//newticket
		// <insert id="newemp" parameterType="emp" > inserrt... </insert>
	}
	
	

	public void updateticket(BoardVO vo) {
		System.out.println(session);
		session.update("updateticket", vo);
		
	}

	public int deleteticket(int id) {
		int row = session.delete("deleteticket", id);
		return row;
	}

}
