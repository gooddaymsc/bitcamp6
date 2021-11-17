package com.eomcs.pms.web;

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
      request.setAttribute("rankingWine", productDao.rankingType("와인"));
      request.setAttribute("rankingWhiskey", productDao.rankingType("위스키"));
      request.setAttribute("rankingBrandy", productDao.rankingType("브랜디/꼬냑"));
      request.setAttribute("rankingVodka", productDao.rankingType("리큐르/보드카"));
      request.setAttribute("rankingTrad", productDao.rankingType("전통주"));

      request.setAttribute("pageTitle", "메인");
      request.setAttribute("contentUrl", "/main/Menu.jsp");
      request.getRequestDispatcher("/template0.jsp").forward(request, response);
    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("Error.jsp").forward(request, response);
    }
  }
}