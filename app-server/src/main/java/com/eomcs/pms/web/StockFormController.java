package com.eomcs.pms.web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.eomcs.pms.dao.StockDao;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Stock;


@WebServlet("/stock/form")
public class StockFormController extends HttpServlet {
  private static final long serialVersionUID = 1L;
  StockDao stockDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    stockDao = (StockDao) 웹애플리케이션공용저장소.getAttribute("stockDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    // 출력을 담당할 뷰를 호출한다.
    HttpSession session = request.getSession(false);

    if (session.getAttribute("loginUser") == null) {
      out.printf("<script>alert('로그인 후 사용 가능합니다.'); location.href='../main/loginMenu'</script>");
      out.flush();
      return;
    }
    int productNo = Integer.parseInt(request.getParameter("productNumber"));
    Member member = (Member) request.getSession(false).getAttribute("loginUser");
    try {
      Stock stock = stockDao.findByNoId(productNo, member.getId());
      if (stock!= null) {
        out.printf("<script>alert('이미 재고에 등록되어있는 상품입니다.'); location.href='./list?id=%s'</script>", member.getId());
        out.flush();
      } else {
        request.setAttribute("productNo", productNo);
        request.setAttribute("pageTitle", "재고담기");
        request.setAttribute("contentUrl", "/stock/StockForm.jsp");
        request.getRequestDispatcher("/template2.jsp").forward(request, response);

      }
    } catch(Exception e){
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}







