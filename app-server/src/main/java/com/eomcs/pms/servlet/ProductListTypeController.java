package com.eomcs.pms.servlet;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.eomcs.pms.dao.ProductDao;
import com.eomcs.pms.domain.Product;

@WebServlet("/product/listType")
public class ProductListTypeController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  ProductDao productDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    productDao = (ProductDao) 웹애플리케이션공용저장소.getAttribute("productDao");
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {

      String type = request.getParameter("type");
      Collection<Product> productList = productDao.findTypeAll(type);

      //      Thumbnails.of(getServletContext().getRealPath("/upload/product") + "/" + filename)
      //      .size(100, 100)
      //      .outputFormat("jpg")
      //      .crop(Positions.CENTER)
      //      .toFiles(new Rename() {
      //        @Override
      //        public String apply(String name, ThumbnailParameter param) {
      //          return name + "_100x100";
      //        }
      //      });

      request.setAttribute("type", type);
      request.setAttribute("productList", productList);
      request.setAttribute("pageTitle", "주류목록");
      request.setAttribute("contentUrl", "/product/ProductListType.jsp");
      request.getRequestDispatcher("/template2.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
