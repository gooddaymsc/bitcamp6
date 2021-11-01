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
import com.eomcs.pms.domain.Seller;
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
    Stock stock = new Stock(); 
    int no = Integer.parseInt(request.getParameter("productNumber"));

    try {
      Product product = productDao.findByNo(no);
      stock.setProduct(product);

      stock.setPrice(Integer.parseInt(request.getParameter("price")));
      stock.setStocks(Integer.parseInt(request.getParameter("stocks")));

      Seller seller = sellerDao.findById("s2");
      stock.setSeller(seller);

      stockDao.insert(stock);
      sqlSession.commit();

      response.setHeader("Refresh", "1;url=list");
      request.getRequestDispatcher("stock/StockAdd.jsp").forward(request, response);
    } catch(Exception e){
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);   
    }
  }

}