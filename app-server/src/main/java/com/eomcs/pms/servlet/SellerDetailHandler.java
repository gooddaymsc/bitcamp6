package com.eomcs.pms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
public class SellerDetailHandler extends HttpServlet {
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

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("  <title>회원상세</title>");
    out.println("  <style>");
    out.println("  label {");
    out.println("    margin-right: 5px;");
    out.println("    text-align: right;");
    out.println("    display: inline-block;");
    out.println("    width: 60px;");
    out.println("  }");
    out.println("  </style>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>회원 상세</h1>");

    String id = request.getParameter("id");

    out.println("[개인정보 상세보기]");

    try {
      Seller seller = sellerDao.findById(id);

      if (seller == null) {
        out.println("해당 번호의 회원이 없습니다.");

      } else {
        out.println("<form action='update'>");
        out.printf("<label for='f-no'>번호</label> <input id='f-no' type='text' name='no' value='%d' readonly><br>\n", seller.getMember().getNumber());
        out.printf("<label for='f-name'>이름</label> <input id='f-name' type='text' name='name' value='%s'><br>\n", seller.getMember().getName());
        out.printf("<label for='f-nickname'>닉네임</label> <input id='f-nickname' type='text' name='nickname' value='%s'><br>\n", seller.getMember().getName());
        out.printf("<label for='f-email'>이메일</label> <input id='f-email' type='email' name='email' value='%s'><br>\n", seller.getMember().getEmail());
        out.printf("<label for='f-password'>암호</label> <input id='f-password' type='password' name='password'><br>\n");
        out.printf("<label for='f-photo'>사진</label> <input id='f-photo' type='text' name='photo' value='%s'><br>\n", seller.getMember().getPhoto());
        out.printf("<label for='f-tel'>전화</label> <input id='f-tel' type='tel' name='tel' value='%s'><br>\n", seller.getMember().getPhoneNumber());
        out.printf("<label for='f-level'>등급</label> <input id='f-level' type='text' name='level' value='%d'><br>\n", seller.getMember().getName());
        out.printf("<label for='f-birthday'>생일</label> <input id='f-birthday' type='text' name='birthday' value='%s'><br>\n", seller.getMember().getName());
        out.printf("<label for='f-registeredDate'>등록일</label> <span id='f-registeredDate'>%s</span><br>", seller.getMember().getRegisteredDate());
        System.out.printf("가게명 : %s\n", seller.getBusinessName());
        System.out.printf("사업자번호 : %s\n", seller.getBusinessNumber());
        System.out.printf("사업장주소 : %s\n", seller.getBusinessAddress());
        System.out.printf("사업장번호 : %s\n", seller.getBusinessPlaceNumber());
        System.out.printf("오픈시간: %s\n", seller.getBusinessOpeningTime());
        System.out.printf("마감시간: %s\n", seller.getBusinessClosingTime());
        System.out.printf("권한 : %d\n", seller.getMember().getAuthority());
        out.println();
        out.println("<button>변경</button>");
        out.printf(" <a href='delete?no=%d'>[삭제]</a>", seller.getMember().getNumber());
        out.println(" <a href='list'>[목록]</a><br>");
        out.println("</form>");
      }
    } catch (Exception e) {
      throw new ServletException(e);
    }

    out.println("</body>");
    out.println("</html>");
  }
  //    } else {
  //      System.out.println("[판매자 상세보기] || 이전(0)");
  //      String id = (String)request.getAttribute("id");
  //
  //      Seller seller = sellerDao.findById(id);
  //
  //      if (seller == null) {
  //        System.out.println("해당 아이디를 갖는 회원이 없습니다.\n");
  //        return;
  //      }
  //      System.out.printf("이름 : %s\n", seller.getMember().getName());
  //      System.out.printf("닉네임 : %s\n", seller.getMember().getNickname());
  //      System.out.printf("등급 : %s\n", seller.getMember().getLevel());
  //      System.out.printf("이메일 : %s\n", seller.getMember().getEmail());
  //      System.out.printf("사진 : %s\n", seller.getMember().getPhoto());
  //      System.out.printf("전화 : %s\n", seller.getMember().getPhoneNumber());
  //      System.out.printf("가게명 : %s\n", seller.getBusinessName());
  //      System.out.printf("사업자번호 : %s\n", seller.getBusinessNumber());
  //      System.out.printf("사업장주소 : %s\n", seller.getBusinessAddress());
  //      System.out.printf("사업장번호 : %s\n", seller.getBusinessPlaceNumber());
  //      System.out.printf("오픈시간: %s\n", seller.getBusinessOpeningTime());
  //      System.out.printf("마감시간: %s\n", seller.getBusinessClosingTime());
  //
  //      request.setAttribute("id", seller.getMember().getId());
  //
  //      while(true) {
  //        System.out.println("\n등급변경(U) / 회원탈퇴(D)");
  //        String choose = Prompt.inputString("선택 > ");
  //        System.out.println();
  //        switch (choose) {
  //          case "u":
  //          case "U": request.getRequestDispatcher("/seller/update").forward(request); return;
  //          case "d":
  //          case "D": request.getRequestDispatcher("/seller/delete").forward(request); return;
  //          case "0": return;
  //        }
  //      }
  //    }
}

