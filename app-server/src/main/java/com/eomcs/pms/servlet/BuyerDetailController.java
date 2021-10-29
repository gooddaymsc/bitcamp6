package com.eomcs.pms.servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import com.eomcs.pms.dao.BuyerDao;
import com.eomcs.pms.domain.Buyer;

@WebServlet("/buyer/detail")
public class BuyerDetailController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  BuyerDao buyerDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    buyerDao = (BuyerDao) 웹애플리케이션공용저장소.getAttribute("buyerDao");
  }

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    try {
      String id = request.getParameter("id");
      Buyer buyer = buyerDao.findById(id);

      if (buyer == null) {
        throw new Exception("해당 번호의 회원이 없습니다.");
      }

      request.setAttribute("buyer", buyer);
      request.getRequestDispatcher("/buyer/BuyerDetail.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      e.getStackTrace();
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}







