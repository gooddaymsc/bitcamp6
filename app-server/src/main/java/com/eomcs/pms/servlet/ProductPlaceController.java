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
import com.eomcs.pms.dao.StockDao;
import com.eomcs.pms.domain.Stock;

@WebServlet("/product/place")
public class ProductPlaceController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  StockDao stockDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    stockDao = (StockDao) 웹애플리케이션공용저장소.getAttribute("stockDao");

  }

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    try {
      String input = request.getParameter("place");
      System.out.println(input);

      Collection<Stock> stockSellerList = (Collection<Stock>)request.getParameter("stockSellerList");
      request.setAttribute("productList", productList);
      request.getRequestDispatcher("ProductList.jsp").forward(request, response);
    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}

