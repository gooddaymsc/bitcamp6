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
import com.eomcs.pms.dao.SellerDao;
import com.eomcs.pms.domain.Seller;

@WebServlet("/seller/update")
public class SellerUpdateController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  SqlSession sqlSession;
  SellerDao sellerDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    sellerDao = (SellerDao) 웹애플리케이션공용저장소.getAttribute("sellerDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      String id = request.getParameter("id");
      Seller seller = sellerDao.findById(id);
      if (seller == null) {
        throw new Exception("해당 번호의 회원이 없습니다.");
      } 
      seller.getMember().setNickname(request.getParameter("nickname"));
      seller.getMember().setEmail(request.getParameter("email"));
      seller.getMember().setPassword(request.getParameter("password"));
      seller.getMember().setPhoto(request.getParameter("photo"));
      seller.getMember().setPhoneNumber(request.getParameter("phoneNumber"));
      seller.setBusinessName(request.getParameter("businessName"));
      seller.setBusinessNumber(request.getParameter("businessNumber"));
      seller.setBusinessAddress(request.getParameter("businessAddress"));
      seller.setBusinessPlaceNumber(request.getParameter("businessPlaceNumber"));  
      seller.setBusinessOpeningTime(request.getParameter("businessOpeningTime"));
      seller.setBusinessClosingTime(request.getParameter("businessClosingTime"));


      sellerDao.update(seller.getMember());
      sellerDao.updateSeller(seller);
      sqlSession.commit();
      response.sendRedirect("../main/login");

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
