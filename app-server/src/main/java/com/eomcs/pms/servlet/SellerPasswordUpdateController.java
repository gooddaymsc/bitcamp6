package com.eomcs.pms.servlet;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.SellerDao;
import com.eomcs.pms.domain.Seller;

@WebServlet("/seller/passwordUpdate")
public class SellerPasswordUpdateController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  SqlSession sqlSession;
  SellerDao sellerDao;

  @Override
  public void init() {
    ServletContext 웹애플리케이션공용저장소 = getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    sellerDao = (SellerDao) 웹애플리케이션공용저장소.getAttribute("sellerDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {

      String id = request.getParameter("id");
      Seller seller = sellerDao.findById(id);

      //      if (seller == null) {
      //        throw new Exception("해당 번호의 회원이 없습니다.");
      //      } 

      seller.getMember().setPassword(request.getParameter("password"));

      sellerDao.update(seller.getMember());
      sellerDao.updateSeller(seller);
      sqlSession.commit();

      request.setAttribute("pageTitle", "회원(판매자)비밀번호 변경");
      request.setAttribute("contentUrl", "/main/Menu.jsp");
      request.getRequestDispatcher("/template2.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
