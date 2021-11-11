package com.eomcs.pms.servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.eomcs.pms.dao.SellerDao;
import com.eomcs.pms.domain.Seller;

@WebServlet("/seller/passwordDetail")
public class SellerPasswordDetailController extends HttpServlet {
  private static final long serialVersionUID = 1L;
  SellerDao sellerDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sellerDao = (SellerDao) 웹애플리케이션공용저장소.getAttribute("sellerDao");
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {

      String id = request.getParameter("id");
      Seller seller = sellerDao.findById(id);

      //      if (seller == null) {
      //        throw new Exception("해당 번호의 회원이 없습니다.");
      //      }
      request.setAttribute("seller", seller);

      request.setAttribute("pageTitle", "회원(판매자)비밀번호 변경");
      request.setAttribute("contentUrl", "/seller/SellerPasswordDetail.jsp");
      request.getRequestDispatcher("/template1.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
    //    request.getRequestDispatcher(page).forward(request, response);
  }
}

