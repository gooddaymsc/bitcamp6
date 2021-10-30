package com.eomcs.pms.servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import com.eomcs.pms.dao.SellerDao;
import com.eomcs.pms.domain.Seller;

@WebServlet("/seller/detail")
public class SellerDetailController extends HttpServlet {
  private static final long serialVersionUID = 1L;
  SellerDao sellerDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sellerDao = (SellerDao) 웹애플리케이션공용저장소.getAttribute("sellerDao");
  }

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    try {
      String id = request.getParameter("id");
      Seller seller = sellerDao.findById(id);
      if (seller == null) {
        throw new Exception("해당 번호의 회원이 없습니다.");
      }
      request.setAttribute("seller", seller);
      request.getRequestDispatcher("/seller/SellerDetail.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}

