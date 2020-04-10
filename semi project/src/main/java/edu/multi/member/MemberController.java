package edu.multi.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberController {
	@Autowired
	MemberDAO dao;
//회원가입
//로그인폼
	@RequestMapping("/session/login")
	public String loginform(){
		return "mybatis/login";
	}
//로그인폼 입력 - db member 테이블 존재 - 세션 저장	
	@RequestMapping("/session/loginprocess")
	public String loginprocess(HttpServletRequest request, String userid, String password){
		MemberVO vo = dao.selectMember(userid, password);
		HttpSession session = request.getSession();
		session.setAttribute("member", vo);	
		return "mybatis/loginprocess";
	}
	
	@RequestMapping("/session/mypage")
	public String getMypage() {
		return "mybatis/mypage";	
	}
	
	@RequestMapping("/session/logout")
	public String logout(HttpSession session) {
		//HttpSession객체 저장 정보 삭제
		//HttpSession session = request.getSession();
		session.removeAttribute("member");
		
		return "mybatis/logout";
	}	
	
//로그아웃
	
	
	
	//회원가입 화면
	@RequestMapping(value="/mybatis/insertmember", method=RequestMethod.GET)
	public String insertMember(){
		return "mybatis/insertmember";
	}
	@RequestMapping(value="/mybatis/insertmember", method=RequestMethod.POST)
	public String insertMember(MemberVO member){
		
		//dao.checkuser(member.getUserid());
		if(dao.checkuser(member.getUserid()) != null) {
			return "mybatis/inserterror";
		}
		dao.insertMember(member);
		return "redirect:/session/login";
	}
	
	
	@RequestMapping("/session/checkuser")
	public String checkuser(HttpSession session) {
		//HttpSession객체 저장 정보 삭제
		//HttpSession session = request.getSession();
		//session.Attribute("member");
		
		return "insertmember";
	}
}