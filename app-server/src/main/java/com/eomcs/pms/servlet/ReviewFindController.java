package com.eomcs.pms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
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

@WebServlet("/product/review/find")
public class ReviewFindController extends HttpServlet {
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

    Member member = (Member) request.getSession(false).getAttribute("loginUser");
    try {
      Collection<Review> reviewList = reviewDao.myReview(member.getId());
      if (reviewList.equals(null)) {
        out.println("작성한 리뷰가 없습니다.");
      }
      request.setAttribute("reviewList", reviewList);
      request.setAttribute("pageTitle", "내리뷰");
      request.setAttribute("contentUrl", "/product/review/FindReviewList.jsp");
      request.getRequestDispatcher("/template2.jsp").forward(request, response);


    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}


