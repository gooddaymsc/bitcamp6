package com.eomcs.pms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import com.eomcs.pms.dao.SellerDao;
import com.eomcs.pms.domain.Seller;

@WebServlet("/seller/list")
public class SellerListHandler extends HttpServlet {
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

    out.println("<html>");
    out.println("<head>");
    out.println("  <title>회원목록(판매자)</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>회원 목록(판매자)</h1>");
    out.println("<a href='form'>새회원</a><br>");
    out.println("<table border='1'>");
    out.println("<thead>");
    out.println("  <tr>");
    out.println("    <th>번호</th>");
    out.println("    <th>아이디</th>");
    out.println("    <th>가게명</th>");
    out.println("    <th>이름</th>");
    out.println("    <th>닉네임</th>");
    out.println("    <th>레벨</th>");
    out.println("    <th>등록일</th>");
    out.println("  </tr>");
    out.println("</thead>");
    out.println("<tbody>");

    try {
      Collection<Seller> sellerList = sellerDao.findAll();

      for (Seller seller : sellerList) {
        System.out.printf("<tr>"
            + "<td>%d</td>"
            + " <td><a href='detail?id=%1$d'>%s</a></td>"
            + " <td>%s</td>"
            + " <td>%s</td>"
            + " <td>%s</td>"
            + " <td>%d</td>"
            + " <td>%s</td>"
            + "</tr>\n", 
            seller.getMember().getNumber(), 
            seller.getMember().getId(),
            seller.getBusinessName(),
            seller.getMember().getName(), 
            seller.getMember().getNickname(),
            seller.getMember().getLevel(),
            seller.getMember().getRegisteredDate());
      }
      //      System.out.println();
      //      while (true) {
      //        String id = Prompt.inputString("선택할 아이디 : ");
      //        System.out.println();
      //        request.setAttribute("id", id);
      //        if (id.equals("0")) {return;}
      //        request.getRequestDispatcher("/seller/detail").forward(request);
      //        continue Loop;
      //      }
    } catch (Exception e) {
      throw new ServletException(e);
    }
    out.println("</tbody>");
    out.println("</table>");
    out.println("</body>");
    out.println("</html>");

  }
}


