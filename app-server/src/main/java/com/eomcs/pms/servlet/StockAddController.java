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
import com.eomcs.pms.dao.SellerDao;
import com.eomcs.pms.dao.StockDao;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.Stock;

@WebServlet("/stock/add")
public class StockAddController  extends HttpServlet {
  private static final long serialVersionUID = 1L;

  SqlSession sqlSession;
  StockDao stockDao;
  SellerDao sellerDao;
  ProductDao productDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    productDao = (ProductDao) 웹애플리케이션공용저장소.getAttribute("productDao");
    stockDao = (StockDao) 웹애플리케이션공용저장소.getAttribute("stockDao");
    sellerDao = (SellerDao) 웹애플리케이션공용저장소.getAttribute("sellerDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      String productName = (String) request.getAttribute("productName");
      Product product = productDao.findByProduct(productName);
      Stock stock = new Stock(); 
      stock.setProduct(product);
      System.out.println("1");
      stock.setPrice(Integer.parseInt(request.getParameter("price")));
      System.out.println("2");
      stock.setStocks(Integer.parseInt(request.getParameter("stocks")));
      System.out.println("3");
      //    Seller seller = sellerDao.findById(nowLoginId);
      //    stock.setSeller(seller);
      stockDao.insert(stock);
      System.out.println("4");
      sqlSession.commit();
      response.setHeader("Refresh", "1;url=list");
      request.getRequestDispatcher("stock/StockAdd.jsp").forward(request, response);
    } catch(Exception e){
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);   
    }
  }

}