package com.eomcs.pms.web;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.StockDao;
import com.eomcs.pms.domain.Stock;

//@WebServlet("/stock/update")
public class StockUpdateController extends HttpServlet {
  private static final long serialVersionUID = 1L;
  SqlSession sqlSession;
  StockDao stockDao;


  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    stockDao = (StockDao) 웹애플리케이션공용저장소.getAttribute("stockDao");
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      int no = Integer.parseInt(request.getParameter("no"));
      Stock stock = stockDao.findByNo(no);

      stock.setStocks(Integer.parseInt(request.getParameter("stocks")));
      stock.setPrice(Integer.parseInt(request.getParameter("price")));

      stockDao.update(stock);
      sqlSession.commit();
      response.sendRedirect("list?id="+stock.getSeller().getMember().getId());

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}






