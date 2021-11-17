package com.eomcs.pms.web;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.eomcs.pms.dao.BuyerDao;
import com.eomcs.pms.domain.Buyer;

@WebServlet("/buyer/passwordDetail")
public class BuyerPasswordDetailController extends HttpServlet {
  private static final long serialVersionUID = 1L;
  BuyerDao buyerDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    buyerDao = (BuyerDao) 웹애플리케이션공용저장소.getAttribute("buyerDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {

      String id = request.getParameter("id");
      Buyer buyer = buyerDao.findById(id);

      //      if (buyer == null) {
      //        throw new Exception("해당 번호의 회원이 없습니다.");
      //      }
      request.setAttribute("buyer", buyer);

      request.setAttribute("pageTitle", "회원(구매자)비밀번호 변경");
      request.setAttribute("contentUrl", "/buyer/BuyerPasswordDetail.jsp");
      request.getRequestDispatcher("/template1.jsp").forward(request, response);
      //    request.getRequestDispatcher(page).forward(request, response);
    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}

