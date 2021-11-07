package com.eomcs.pms.servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.CartDao;
import com.eomcs.pms.domain.Cart;
import com.eomcs.pms.domain.Member;

@WebServlet("/cart/delete")
public class CartDeleteController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  CartDao cartDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    cartDao = (CartDao) 웹애플리케이션공용저장소.getAttribute("cartDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession(false);

    if (session.getAttribute("loginUser") == null) {
      response.sendRedirect("/drinker/login/menu");
      return;
    }

    try {      
      Member buyer = (Member) request.getSession(false).getAttribute("loginUser");
      String id = buyer.getId();

      int no = Integer.parseInt(request.getParameter("no"));
      Cart cart = cartDao.findByNo(no, id);

      if (cart == null) {
        throw new Exception("해당 번호의 장바구니가 없습니다.");
      }   
      cartDao.delete(cart);
      sqlSession.commit();
      response.sendRedirect("list");

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }   
  } 
}
