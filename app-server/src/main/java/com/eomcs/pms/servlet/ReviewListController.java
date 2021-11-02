package com.eomcs.pms.servlet;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import com.eomcs.pms.dao.ReviewDao;
import com.eomcs.pms.domain.Review;

@WebServlet("/product/review/list")
public class ReviewListController extends HttpServlet {
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
      int productNumber = (Integer)request.getAttribute("productNumber");
      Collection<Review> reviewList = reviewDao.findAll(productNumber);

      if (reviewList.equals(null)) {
        System.out.println("등록된 댓글이 없습니다.");
      }
      request.getRequestDispatcher("ProductDetail.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  } 
}




