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
import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.ProductDao;
import com.eomcs.pms.domain.Product;

@WebServlet("/main/menu")
public class MainMenuController extends HttpServlet {
  private static final long serialVersionUID = 1L;
  SqlSession sqlSession;
  ProductDao productDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext servletContext = config.getServletContext();
    sqlSession = (SqlSession) servletContext.getAttribute("sqlSession");
    productDao = (ProductDao) servletContext.getAttribute("productDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      //      request.setAttribute("pageTitle", "메인");
      //      request.setAttribute("contentUrl", "/main/Menu.jsp");
      //      request.getRequestDispatcher("/template1.jsp").forward(request, response);
      Collection<Product> rankingWine = productDao.rankingWine();
      Collection<Product> rankingWhiskey = productDao.rankingWhiskey();
      Collection<Product> rankingBrandy = productDao.rankingBrandy();
      Collection<Product> rankingVodka = productDao.rankingVodka();
      Collection<Product> rankingTrad = productDao.rankingTrad();

      request.setAttribute("rankingWine", rankingWine);
      request.setAttribute("rankingWhiskey", rankingWhiskey);
      request.setAttribute("rankingBrandy", rankingBrandy);
      request.setAttribute("rankingVodka", rankingVodka);
      request.setAttribute("rankingTrad", rankingTrad);

      request.setAttribute("pageTitle", "메인");
      request.setAttribute("contentUrl", "/main/Menu.jsp");
      request.getRequestDispatcher("/template1.jsp").forward(request, response);
    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("Error.jsp").forward(request, response);
    }
  }
}