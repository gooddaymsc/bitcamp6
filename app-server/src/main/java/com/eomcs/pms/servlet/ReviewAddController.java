package com.eomcs.pms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.ProductDao;
import com.eomcs.pms.dao.ReviewDao;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.Review;

//@WebServlet("/product/review/add")
public class ReviewAddController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  SqlSession sqlSession;
  ReviewDao reviewDao;
  ProductDao productDao;
  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    reviewDao = (ReviewDao) 웹애플리케이션공용저장소.getAttribute("reviewDao");
    productDao = (ProductDao) 웹애플리케이션공용저장소.getAttribute("productDao");

  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();

    if (session.getAttribute("loginUser") == null) {
      out.printf("<script>alert('로그인 후 사용 가능합니다.'); location.href='../main/loginMenu'</script>");
      out.flush();
      return;
    }

    try {
      Member member = (Member) request.getSession(false).getAttribute("loginUser");

      Review review = new Review();
      Product product =  productDao.findByNo(Integer.parseInt(request.getParameter("productNumber")));
      review.setComment(request.getParameter("comment"));
      review.setScore(Float.parseFloat(request.getParameter("score"))); //개인별 평점
      product.setReviewerNum(product.getReviewerNum()+1);
      review.setProductNo(product.getProductNumber());
      review.setReviewProduct(product.getProductName());

      review.setMember(member);

      reviewDao.insert(review);
      product.setRate(reviewDao.avg(review));
      productDao.updateRate(product);
      sqlSession.commit();
      response.sendRedirect("../show?no="+review.getProductNo());

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);   
    }
  } 
} 







