package com.eomcs.pms.servlet;

import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.SellerDao;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Seller;

@WebServlet("/seller/add")
public class SellerAddController extends HttpServlet {
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

    Seller seller = new Seller();
    Member member = new Member();
    member.setId(request.getParameter("id"));
    member.setAuthority(4);
    member.setName(request.getParameter("name"));
    member.setNickname(request.getParameter("nickname"));
    member.setEmail(request.getParameter("email"));
    member.setBirthday(Date.valueOf(request.getParameter("birthday")));
    member.setPassword(request.getParameter("password"));
    member.setPhoto(request.getParameter("photo"));
    member.setPhoneNumber(request.getParameter("phoneNumber"));
    seller.setBusinessName(request.getParameter("businessName"));
    seller.setBusinessNumber(request.getParameter("businessNumber"));
    seller.setBusinessAddress(request.getParameter("businessAddress"));
    seller.setBusinessPlaceNumber(request.getParameter("businessPlaceNumber"));  
    seller.setBusinessOpeningTime(request.getParameter("businessOpeningTime"));
    seller.setBusinessClosingTime(request.getParameter("businessClosingTime"));
    seller.setMember(member);
    try {
      sellerDao.insert(seller.getMember());
      sellerDao.insertSeller(seller);
      sqlSession.commit();
      response.setHeader("Refresh", "1;url=list");
      request.getRequestDispatcher("SellerAdd.jsp").forward(request, response);

    } catch(Exception e){
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);   

    }
  }
}

