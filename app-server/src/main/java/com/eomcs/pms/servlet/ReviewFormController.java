package com.eomcs.pms.servlet;

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
import com.eomcs.pms.dao.ReviewDao;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Review;


@WebServlet("/product/review/form")
public class ReviewFormController extends HttpServlet {
  private static final long serialVersionUID = 1L;
  ReviewDao reviewDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    reviewDao = (ReviewDao) 웹애플리케이션공용저장소.getAttribute("reviewDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    // 출력을 담당할 뷰를 호출한다.
    HttpSession session = request.getSession(false);

    if (session.getAttribute("loginUser") == null) {
      response.sendRedirect("/drinker/login/menu");
      return;
    }
    // 출력을 담당할 뷰를 호출한다.
    int productNumber = Integer.parseInt(request.getParameter("no"));
    Member member = (Member) request.getSession(false).getAttribute("loginUser");
    try {
      Review review = reviewDao.reviewIs(productNumber, member.getId());
      if (review != null) {
        out.printf("<script>alert('이미 등록된 리뷰가 있습니다.'); location.href='../detail?no=%d'</script>", productNumber);
        out.flush();
      } else {
        request.setAttribute("productNumber", productNumber);
        request.setAttribute("pageTitle", "새리뷰");
        request.setAttribute("contentUrl", "/review/ReviewForm.jsp");
        request.getRequestDispatcher("/template2.jsp").forward(request, response);
      }
    } catch(Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}







