package com.eomcs.pms.servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.SellerDao;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Seller;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
@WebServlet("/seller/add")
public class SellerAddController extends HttpServlet {
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
      Seller seller = new Seller();
      Member member = new Member();
      member.setId(request.getParameter("id"));
      member.setAuthority(4);
      member.setName(request.getParameter("name"));
      member.setNickname(request.getParameter("nickname"));
      member.setEmail(request.getParameter("email"));
      member.setBirthday(Date.valueOf(request.getParameter("birthday")));
      member.setPassword(request.getParameter("password"));
      member.setPhoneNumber(request.getParameter("phoneNumber"));
      //      member.setPhoto(request.getParameter("photo"));

      Part photoPart = request.getPart("photo");
      if (photoPart.getSize() > 0) {
        String filename = UUID.randomUUID().toString();
        photoPart.write(getServletContext().getRealPath("/upload/seller") + "/" + filename);
        member.setPhoto(filename);

        Thumbnails.of(getServletContext().getRealPath("/upload/seller") + "/" + filename)
        .size(100, 100)
        .outputFormat("jpg")
        .crop(Positions.CENTER)
        .toFiles(new Rename() {
          @Override
          public String apply(String name, ThumbnailParameter param) {
            return name + "_100x100";
          }
        });
      }

      seller.setBusinessName(request.getParameter("businessName"));
      seller.setBusinessNumber(request.getParameter("businessNumber"));
      seller.setBusinessAddress(request.getParameter("businessAddress"));
      seller.setBusinessPlaceNumber(request.getParameter("businessPlaceNumber"));  
      seller.setBusinessOpeningTime(request.getParameter("businessOpeningTime"));
      seller.setBusinessClosingTime(request.getParameter("businessClosingTime"));
      seller.setMember(member);
      sellerDao.insert(seller.getMember());
      sellerDao.insertSeller(seller);
      sqlSession.commit();
      request.setAttribute("pageTitle", "회원가입(판매자)");
      request.setAttribute("contentUrl", "/seller/SellerAdd.jsp");
      request.getRequestDispatcher("/template2.jsp").forward(request, response);
      response.setHeader("Refresh", "1;url=../main/loginMenu");

    } catch(Exception e){
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);   

    }
  }
}

