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
import com.eomcs.pms.dao.ProductDao;
import com.eomcs.pms.dao.ReviewDao;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.Review;

@WebServlet("/product/detail")
public class ProductDetailController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  ProductDao productDao;
  ReviewDao reviewDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    productDao = (ProductDao) 웹애플리케이션공용저장소.getAttribute("productDao");
    reviewDao = (ReviewDao) 웹애플리케이션공용저장소.getAttribute("reviewDao");

  }

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    try {
      int no = Integer.parseInt(request.getParameter("no"));
      Product product = productDao.findByNo(no);
      Collection<Review> reviewList = reviewDao.findAll(no);

      if (reviewList.equals(null)) {
        System.out.println("등록된 댓글이 없습니다.");
      }

      if (product == null) {
        throw new Exception("해당 번호의 상품이 없습니다.");

      }
      request.setAttribute("product", product);
      request.setAttribute("pageTitle", "상품정보수정");
      request.setAttribute("contentUrl", "/product/ProductDetail.jsp");
      request.getRequestDispatcher("/template2.jsp").forward(request, response);

    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}




