package com.eomcs.pms.servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.ReviewDao;
import com.eomcs.pms.domain.Review;

@WebServlet("/product/review/update")
public class ReviewUpdateController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  SqlSession sqlSession;
  ReviewDao reviewDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    reviewDao = (ReviewDao) 웹애플리케이션공용저장소.getAttribute("reviewDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      int no = Integer.parseInt(request.getParameter("reviewNo"));
      Review review = reviewDao.findByNo(no);

      if (review.equals(null)) {
        throw new Exception("해당 번호의 리뷰가 없습니다.");
      }

      review.setScore(Float.parseFloat(request.getParameter("score")));
      review.setComment(request.getParameter("comment"));

      reviewDao.update(review);
      sqlSession.commit();
      response.sendRedirect("../detail?no="+review.getProductNo());
    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
} 





