package com.eomcs.pms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.CartDao;
import com.eomcs.pms.domain.Cart;

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

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<html>");
    out.println("<head>");
    out.println("  <title>장바구니삭제</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>장바구니삭제결과</h1>");

    try {
      int no = Integer.parseInt(request.getParameter("no"));
      String id = request.getParameter("f-buyerId");

      Cart cart = cartDao.findByNo(no, id);

      if (cart == null) {
        out.println("해당 번호의 장바구니가 없습니다.<br>");

      } else {
        cartDao.delete(cart);
        sqlSession.commit();

        out.println("장바구니를 삭제하였습니다.<br>");
      }      

      out.println("<a href='list'>[목록]<a><br>");

    } catch (Exception e) {
      out.println("장바구니 삭제 오류!");
      e.printStackTrace();
    }

    out.println("</body>");
    out.println("</html>");

    response.sendRedirect("list");
  }

}
