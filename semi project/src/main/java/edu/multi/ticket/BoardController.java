package edu.multi.ticket;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardController{
	@Autowired 
	//1.<beans:bean id="dao" class="xxx.BoardDAO" 
	//2. @Repository("dao") class BoardDAO
	BoardDAO dao;

	@RequestMapping("/boardlist")
	public ModelAndView handleRequest
	(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
        ModelAndView mv = new ModelAndView();
        // board테이블 모든 게시물(model) :ArrayList<BoardVO>
        ArrayList<BoardVO> list = dao.getList();
       mv.addObject("list", list);
       mv.setViewName("boardlist");
        return mv;
	}
	
	//1개 게시물 글쓰기 폼 화면
	@RequestMapping(value="/boardinsert", method = RequestMethod.GET)
    public String insertBoardForm() {
    	return "boardinsertform";
    }
	//글쓴 내용 전달받아서 board 테이블 저장 
	@RequestMapping(value="/boardinsert", method = RequestMethod.POST)	
    public String insertBoardResult(@ModelAttribute("vo") BoardVO vo) {
    		//System.out.println(vo.getContents());
		    int result = dao.insertBoard(vo);//제목,내용,작성자,암호입력 저장 상태 객체
		   if(result == 1) {
			   System.out.println("정상 insert");
		   }
		   else {
			   System.out.println("비정상 insert");
		   }
		    return "redirect:/boardlist";
        }
	

	
	
	   public String BoardResult(@ModelAttribute("vo") BoardVO vo) {
   		//System.out.println(vo.getContents());
		    int result = dao.insertBoard(vo);//제목,내용,작성자,암호입력 저장 상태 객체
		   if(result == 1) {
			   System.out.println("정상 insert");
		   }
		   else {
			   System.out.println("비정상 insert");
		   }
		    return "redirect:/boardlist";
       }
	   
	  
	
	
	
	
	
	@RequestMapping("/boardupdate")
	public String updateBoardResult(@ModelAttribute("vo") BoardVO vo) {
		//System.out.println(vo.getContents());
	    int result = dao.updateBoard(vo);//제목,내용,작성자,암호입력 저장 상태 객체
	   if(result == 1) {
		   System.out.println("정상 ubdate");
	   }
	   else {
		   System.out.println("비정상 update");
	   }
	    return "redirect:/boardlist";
    }
	//1개 게시물 수정
	
	@RequestMapping("/boarddelete")
	public String deleteBoardResult(int seq) {
		//System.out.println(vo.getContents());
	    int result = dao.deleteBoard(seq);//제목,내용,작성자,암호입력 저장 상태 객체
	   if(result == 1) {
		   System.out.println("정상 delete");
	   }
	   else {
		   System.out.println("비정상 delete");
	   }
	   return "redirect:/boardlist";
    }
	
	
	// 번호 입력받아 해당 게시물 조회
	
	
			
	

}


