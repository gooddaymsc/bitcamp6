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
import com.eomcs.pms.domain.Product;

@WebServlet("/product/listBrandy")
public class ProductListBrandyController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  ProductDao productDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    productDao = (ProductDao) 웹애플리케이션공용저장소.getAttribute("productDao");
  }

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    try {
      Collection<Product> productList = productDao.findBrandyAll();
      request.setAttribute("productList", productList);
      request.setAttribute("pageTitle", "브랜디/꼬냑목록");
      request.setAttribute("contentUrl", "/product/ProductListBrandy.jsp");
      request.getRequestDispatcher("/template2.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
