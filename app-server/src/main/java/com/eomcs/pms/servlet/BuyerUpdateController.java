package com.eomcs.pms.servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.BuyerDao;
import com.eomcs.pms.domain.Buyer;

@WebServlet("/buyer/update")
public class BuyerUpdateController extends HttpServlet {
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
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      String id = request.getParameter("id");
      Buyer buyer = buyerDao.findById(id);

      buyer.getMember().setNickname(request.getParameter("nickname"));
      buyer.getMember().setPassword(request.getParameter("password"));
      buyer.getMember().setPhoto(request.getParameter("photo"));
      buyer.getMember().setPhoneNumber(request.getParameter("phoneNumber"));
      buyer.setZipcode(request.getParameter("zipcode"));
      buyer.setAddress(request.getParameter("address"));
      buyer.setDetailAddress(request.getParameter("detailAddress"));

      buyerDao.update(buyer);
      sqlSession.commit();
      response.sendRedirect("../main/Menu.jsp");

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }

  //  else {
  //      System.out.println("[회원 변경]\n");
  //      String id = (String) request.getAttribute("id");
  //
  //      Buyer buyer = buyerDao.findById(id);


  // 닉네임, 레벨, 판매자/구매자(회원) 변경 가능
  //      int level =BuyerValidation.checkLevel(String.format("등급(변경 전 : %d) : ", buyer.getMember().getLevel())); 
  //      String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
  //      if (input.equalsIgnoreCase("y")) {
  //        buyer.getMember().setLevel(level);
  //        buyerDao.update(buyer);
  //        sqlSession.commit();
  //        System.out.println("회원정보를 변경했습니다.\n");
  //        return;
  //      }
  //      System.out.println("회원정보 변경을 취소하였습니다.\n");
  //}

}







