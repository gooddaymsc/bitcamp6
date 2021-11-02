package com.eomcs.pms.servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import com.eomcs.pms.dao.ReviewDao;
import com.eomcs.pms.domain.Review;

@WebServlet("/product/review/detail")
public class ReviewDetailController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  ReviewDao reviewDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    reviewDao = (ReviewDao) 웹애플리케이션공용저장소.getAttribute("reviewDao");

  }

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    try {
      int no = Integer.parseInt(request.getParameter("no"));
      Review review = reviewDao.findByNo(no);

      if (review.equals(null)) {
        throw new Exception("해당 번호의 리뷰가 없습니다.");
      }

      request.setAttribute("review", review);
      request.getRequestDispatcher("/review/ReviewDetail.jsp").forward(request, response);

    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}




