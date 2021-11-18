package com.eomcs.pms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.SellerDao;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Seller;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

//@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
//@WebServlet("/seller/update")
public class SellerUpdateController extends HttpServlet {
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
    HttpSession session = request.getSession(false);
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();

    if (session.getAttribute("loginUser") == null) {
      out.printf("<script>alert('로그인 후 사용 가능합니다.'); location.href='../main/loginMenu'</script>");
      out.flush();
      return;
    }
    try {
      Member member = (Member) request.getSession(false).getAttribute("loginUser");

      String id = request.getParameter("id");
      Seller seller = sellerDao.findById(id);

      if (seller == null) {
        throw new Exception("해당 번호의 회원이 없습니다.");
      } 
      if (member.getId().equals(seller.getMember().getId())) {
        seller.getMember().setNickname(request.getParameter("nickname"));
        seller.getMember().setEmail(request.getParameter("email"));
        //        seller.getMember().setPassword(request.getParameter("password"));
        seller.getMember().setPhoneNumber(request.getParameter("phoneNumber"));
        //        seller.getMember().setPhoto(request.getParameter("photo"));

        Part photoPart = request.getPart("photo");
        if (photoPart.getSize() > 0) {
          String filename = UUID.randomUUID().toString();
          photoPart.write(getServletContext().getRealPath("/upload/seller") + "/" + filename);
          seller.getMember().setPhoto(filename);

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

      } else if (member.getAuthority() == 8) {
        seller.getMember().setLevel(Integer.parseInt(request.getParameter("level")));
      }
      sellerDao.update(seller.getMember());
      sellerDao.updateSeller(seller);
      sqlSession.commit();
      request.setAttribute("pageTitle", "개인정보변경");
      request.setAttribute("contentUrl", "/main/Menu.jsp");
      request.getRequestDispatcher("/template2.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
