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
import com.eomcs.pms.dao.BuyerDao;
import com.eomcs.pms.dao.MemberDao;
import com.eomcs.pms.domain.Buyer;


@WebServlet("/member/list")
public class BuyerListHandler extends HttpServlet {
  private static final long serialVersionUID = 1L;
  BuyerDao buyerDao;
  MemberDao memberDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    buyerDao = (BuyerDao) 웹애플리케이션공용저장소.getAttribute("buyerDao");
  }

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("  <title>회원목록</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>회원 목록</h1>");
    out.println("<a href='form'>새회원</a><br>");
    out.println("<table border='1'>");
    out.println("<thead>");
    out.println("  <tr>");
    out.println("    <th>번호</th>");
    out.println("    <th>아이디</th>");
    out.println("    <th>이름</th>");
    out.println("    <th>닉네임</th>");
    out.println("    <th>레벨</th>");
    out.println("    <th>등록일</th>");
    out.println("  <tr>");
    out.println("</thread>");
    out.println("<tbody>");

    try {
      Collection<Buyer> buyerList =  buyerDao.findAll();


      //      <a href='detail?no=%1$d'>%s</a>
      for (Buyer buyer : buyerList) {
        out.printf("<tr>"
            + "<td>%d</td>"
            + " <td>%s</td>"
            + " <td>%s</td>"
            + " <td>%s</td>"
            + " <td>%d</td>"
            + " <td>%s</td>"
            + "</tr>\n", 
            buyer.getMember().getNumber(),
            buyer.getMember().getId(), 
            buyer.getMember().getName(),
            buyer.getMember().getNickname(),
            buyer.getMember().getLevel(),
            buyer.getMember().getRegisteredDate());
      }
    } catch (Exception e) {
      throw new ServletException(e);
    }
    out.println("</tbody>");
    out.println("</table>");
    out.println("</body>");
    out.println("</html>");

  }
}







