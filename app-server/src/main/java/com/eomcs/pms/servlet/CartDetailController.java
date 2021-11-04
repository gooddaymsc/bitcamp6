package com.eomcs.pms.servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import com.eomcs.pms.dao.CartDao;
import com.eomcs.pms.domain.Cart;

@WebServlet("/cart/detail")
public class CartDetailController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  CartDao cartDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    cartDao = (CartDao) 웹애플리케이션공용저장소.getAttribute("cartDao");
  }

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    try {
      int cartNumber = Integer.parseInt(request.getParameter("cartNumber"));
      String id = request.getParameter("id");
      Cart cart = cartDao.findByNo(cartNumber, id);

      if (cart == null) {
        throw new Exception("해당 번호의 장바구니가 없습니다.");

      }
      request.setAttribute("cart", cart);
      request.getRequestDispatcher("/cart/CartDetail.jsp").forward(request, response);

    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}
