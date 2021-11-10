package com.eomcs.pms.servlet;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.eomcs.pms.dao.CartDao;
import com.eomcs.pms.dao.SellerDao;
import com.eomcs.pms.domain.Cart;
import com.eomcs.pms.domain.CartList;
import com.eomcs.pms.domain.Member;

@WebServlet("/cart/list")
public class CartListController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  CartDao cartDao;
  SellerDao sellerDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    cartDao = (CartDao) 웹애플리케이션공용저장소.getAttribute("cartDao");
    sellerDao = (SellerDao) 웹애플리케이션공용저장소.getAttribute("sellerDao");
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    HttpSession session = request.getSession(false);

    if (session.getAttribute("loginUser") == null) {
      response.sendRedirect("/drinker/login/menu");
      return;
    }

    try {
      Member buyer = (Member) request.getSession(false).getAttribute("loginUser");
      String id = buyer.getId();
      CartList cartList = new CartList();
      Collection<Cart> list = cartDao.findAll(id);

      cartList.setId(id);
      cartList.setPrivacyCart((List<Cart>) list);

      request.setAttribute("id", id);
      request.setAttribute("cartList", cartList.getPrivacyCart());
      request.setAttribute("pageTitle", "장바구니목록");
      request.setAttribute("contentUrl", "/cart/CartList.jsp");
      request.getRequestDispatcher("/template1.jsp").forward(request, response);
      request.getRequestDispatcher("CartList.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}

