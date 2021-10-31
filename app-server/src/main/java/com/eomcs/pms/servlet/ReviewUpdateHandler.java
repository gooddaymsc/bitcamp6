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
public class ReviewUpdateHandler extends HttpServlet {
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
      System.out.println("0");
      String id = request.getParameter("writer");
      System.out.println("1-1");
      Review review = reviewDao.reviewIs((Integer)request.getAttribute("no"), id);
      System.out.println("1");
      if(review == null) {
        System.out.println("작성하신 리뷰가 없습니다.");
      }
      System.out.println("2");
      review.setScore(Float.parseFloat(request.getParameter("scores")));
      review.setComment(request.getParameter("content"));
      System.out.println("3");
      reviewDao.update(review);
      sqlSession.commit();
      System.out.println("4");
      response.sendRedirect("detail");

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
} 





