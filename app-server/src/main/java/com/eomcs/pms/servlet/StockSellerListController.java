package com.eomcs.pms.servlet;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.eomcs.pms.dao.StockDao;
import com.eomcs.pms.domain.Stock;

//@WebServlet("/stock/sellerList")
public class StockSellerListController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  StockDao stockDao;


  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    stockDao = (StockDao) 웹애플리케이션공용저장소.getAttribute("stockDao");
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      int productNo = Integer.parseInt(request.getParameter("no"));
      Collection<Stock> stockSellerList = stockDao.findByProductNo(productNo);
      request.setAttribute("stockSellerList", stockSellerList);
      request.setAttribute("productNo", productNo);
      request.setAttribute("pageTitle", "재고판매자목록");
      request.setAttribute("contentUrl", "/stock/StockSellerList.jsp");
      request.getRequestDispatcher("/template1.jsp").forward(request, response);
    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }

  }
}

