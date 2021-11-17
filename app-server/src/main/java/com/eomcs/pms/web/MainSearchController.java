package com.eomcs.pms.web;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import com.eomcs.pms.dao.BoardDao;
import com.eomcs.pms.dao.ProductDao;
import com.eomcs.pms.dao.SellerDao;
import com.eomcs.pms.domain.Board;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.Seller;

@WebServlet("/main/search")
public class MainSearchController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  ProductDao productDao;
  BoardDao boardDao;
  SellerDao sellerDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    productDao = (ProductDao) 웹애플리케이션공용저장소.getAttribute("productDao");
    boardDao = (BoardDao) 웹애플리케이션공용저장소.getAttribute("boardDao");
    sellerDao = (SellerDao) 웹애플리케이션공용저장소.getAttribute("sellerDao");

  }

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    try {
      String input = "%"+request.getParameter("search")+"%";
      Collection<Product> productList = productDao.search(input);
      Collection<Seller> sellerList = sellerDao.search(input);
      Collection<Board> boardList = boardDao.search(input);

      request.setAttribute("sellerList", sellerList);
      request.setAttribute("boardList", boardList);
      request.setAttribute("productList", productList);
      request.setAttribute("pageTitle", "전체검색");
      request.setAttribute("contentUrl", "/main/Search.jsp");
      request.getRequestDispatcher("/template2.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}

