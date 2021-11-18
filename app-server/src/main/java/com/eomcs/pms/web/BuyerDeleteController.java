package com.eomcs.pms.web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.BuyerDao;
import com.eomcs.pms.domain.Buyer;
import com.eomcs.pms.domain.Member;

//@WebServlet("/buyer/delete")
public class BuyerDeleteController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  SqlSession sqlSession;
  BuyerDao buyerDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    buyerDao = (BuyerDao) 웹애플리케이션공용저장소.getAttribute("buyerDao");
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();

    if (session.getAttribute("loginUser") == null) {
      out.printf("<script>alert('로그인 후 사용 가능합니다.'); location.href='../main/loginMenu'</script>");
      out.flush();
      return;
    }

    try {  
      Member member = (Member) request.getSession(false).getAttribute("loginUser");

      String id = request.getParameter("id");
      Buyer buyer = buyerDao.findById(id);


      if (buyer == null) {
        throw new Exception("해당 번호의 회원이 없습니다.");
      }   
      buyerDao.delete(buyer.getMember().getNumber());
      sqlSession.commit();

      if (member.getId().equals(id)) {

        response.setHeader("Refresh", "1;url=../main/logout");
        request.setAttribute("pageTitle", "회원탈퇴");
        request.setAttribute("contentUrl", "/buyer/BuyerDelete.jsp");
        request.getRequestDispatcher("/template2.jsp").forward(request, response);

      }
    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }   
  } 
  // 현재로그인 상태 초기화
  //    ClientApp.loginMember = new Member();
  //}

  //  else {
  //    System.out.println("[회원 탈퇴]");
  //    String id = (String) request.getAttribute("id");
  //
  //    String input = Prompt.inputString("정말 탈퇴시키겠습니까?(y/N) ");
  //    if (input.equalsIgnoreCase("y")) {
  //
  //      //        deleteMemberList.add(buyer);
  //      //        memberPrompt.removeMemberById(buyerId);
  //      //        cartPrompt.removeCartListById(buyerId);
  //      //        bookingPrompt.removeBookingListById(buyerId);
  //      //        messagePrompt.removeMessageListById(buyerId);
  //      buyerDao.delete(id);
  //      sqlSession.commit();
  //      System.out.println("회원을 탈퇴시켰습니다.\n");
  //      return;
  //    }
  //    System.out.println("회원탈퇴를 취소하였습니다.\n");
  //  }

}






