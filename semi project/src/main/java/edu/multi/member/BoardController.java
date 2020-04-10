package edu.multi.member;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardController{
	@Autowired 	
	BoardDAO dao;
	


	@RequestMapping("/")
	public String main(){
		//employees 테이블 모든 레코드 조회
		//List<EmpVO>--MODEL
		//view 결정.
		
		return "mybatis/main";
	} //구현완료
	

	
	@RequestMapping("/boardlist")
	public ModelAndView list( )
	{
		
		System.out.println(1);
		List<BoardVO> list = dao.getallticket();
//		System.out.println(list);
		ModelAndView mv = new ModelAndView();
		mv.addObject("list",list);
		mv.setViewName("mybatis/boardlist");
		return mv;
	}
	
	
	
	
	@RequestMapping("/mybatis/boarddetail")
	public ModelAndView getDetailTicket(int seq ){
		BoardVO vo = dao.getOneticket(seq);
		ModelAndView mv = new ModelAndView();
		mv.addObject("ticket", vo);
		return mv;
	}
	
	@RequestMapping("/mybatis/orderresult")
	public String main2()
	{
		System.out.println("업데이트");
		return "mybatis/orderresult";
		
	}
	
	//1개 게시물 글쓰기 폼 화면
	@RequestMapping("/newticketform")
	public String newticketform() {
		
		return "mybatis/boardinsertform";
		
		
	}
	

	

	
	@RequestMapping("/newticket")
	public String newticket(BoardVO vo) {
		System.out.println(vo);
		dao.newticket(vo);
		return "/mybatis/boardinsertresult";
		
		
	}
	

	
	
	/*
	 * @RequestMapping("/updateticket") public ModelAndView updateticket(BoardVO vo)
	 * { ModelAndView mv = new ModelAndView(); dao.updateticket(vo);
	 * mv.addObject("ticket", vo); mv.setViewName("redirect:./"); return mv;
	 * 
	 * }
	 */
	
	@RequestMapping("/mybatis/updateform")
	public ModelAndView updateform(int seq) {
		BoardVO vo = dao.getOneticket(seq);
		ModelAndView mv = new ModelAndView();
		mv.addObject("ticket", vo);
		mv.setViewName("mybatis/boardiupdateform");
		return mv;
	}	
		
		
   @RequestMapping("/mybatis/updateticket")
    public String updateticket(BoardVO vo) {
	   	try {
			dao.updateticket(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/mybatis/boardetail?seq="+vo.seq;
	}

	
	
	@RequestMapping("/mybatis/deleteticket")
	public String deleteticket(int seq) {
	
		try {
			dao.deleteticket(seq);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/boardlist";

		
	}
	
	

	
	@RequestMapping("/mybatis/boardetail")
	public ModelAndView getDetailEmp(int seq){
		BoardVO vo = dao.getOneticket(seq);
		ModelAndView mv = new ModelAndView();
		mv.addObject("ticket", vo);
		return mv;
	}
	
	

}


