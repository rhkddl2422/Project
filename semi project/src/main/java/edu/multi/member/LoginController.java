package edu.multi.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController {
	//폼 입력 화면 
@RequestMapping(value="/login",method = RequestMethod.GET)
	public String loginform() {  //1. 매핑 메소드 리턴타입 String : 뷰이름
		// 로그인폼 출력= html태그=jsp
		return "loginform";
	}
//<form action="/loginsuccess" method=get
//폼 입력 내용 전달받아서 처리 결과 화면
// 요청방식 post : 한글 깨져서
//@RequestMapping(name="/login",method = RequestMethod.POST)
//	public ModelAndView loginsuccess(HttpServletRequest request) {
//	//id pw 입력 전달 상태
//	String id = request.getParameter("id");
//	String pw = request.getParameter("pw");
//	
//	ModelAndView mv= new ModelAndView();
//// id spring 이고 pw spring 일 때만 로그인상태 : true 
//	if(id.equalsIgnoreCase("spring") && pw.equals("spring")) {
//		mv.addObject("loginresult", true);
//	}
//	else {
//		mv.addObject("loginresult",false);
//	}
//		mv.setViewName("loginsuccess");
//		return mv;
//	}

@RequestMapping(value="/login",method = RequestMethod.POST)
public ModelAndView loginsuccess
(@RequestParam(value="id", required = false,  defaultValue = "spring") 
String id2, String pw, int age) {  

//form input name="id" : 요청파라미터이름 id 전송한다
//요청파라미터이름과 컨트롤러 메소드 매개변수 이름 동일하면 자동 저장
// form 입력 name값 = 매개변수이름 동일 = db 컬럼명 동일 저장

ModelAndView mv= new ModelAndView();
//id spring 이고 pw spring 일 때만 로그인상태 : true 
if(id2.equalsIgnoreCase("spring") && pw.equals("spring")) {
	mv.addObject("loginresult", true);
}
else {
	mv.addObject("loginresult",false);
}
	mv.setViewName("loginsuccess");
	return mv;
}
}
