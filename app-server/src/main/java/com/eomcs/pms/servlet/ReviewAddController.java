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
import com.eomcs.pms.dao.ProductDao;
import com.eomcs.pms.dao.ReviewDao;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.Review;

@WebServlet("/product/review/add")
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

    try {
      Review review = new Review();
      Product product =  productDao.findByNo(Integer.parseInt(request.getParameter("productNumber")));
      //    if (reviewDao.reviewIs(product.getProductNumber(),"admin") != null) {
      //      out.println("이미 등록한 리뷰가 있습니다.<br>");
      //      request.getRequestDispatcher("/product/ProductDetail.jsp").forward(request, response);
      //    }
      review.setComment(request.getParameter("comment"));
      review.setScore(Float.parseFloat(request.getParameter("score"))); //개인별 평점
      product.setReviewerNum(product.getReviewerNum()+1);
      review.setProductNo(product.getProductNumber());
      review.setReviewProduct(product.getProductName());

      // 로그인 정보가 없으므로 일단 관리자로 기입.
      Member member = new Member();
      member.setId("admin");
      member.setNumber(1);
      review.setMember(member);

      reviewDao.insert(review);
      product.setRate(reviewDao.avg(review));
      productDao.updateRate(product);
      sqlSession.commit();
      response.sendRedirect("../detail?no="+review.getProductNo());

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);   
    }
  } 
} 







