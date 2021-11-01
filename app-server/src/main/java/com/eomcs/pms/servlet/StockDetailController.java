package com.eomcs.pms.servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import com.eomcs.pms.dao.StockDao;
import com.eomcs.pms.domain.Stock;

@WebServlet("/stock/detail")
public class StockDetailController  extends HttpServlet {
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
      int no = Integer.parseInt(request.getParameter("no"));
      Stock stock = stockDao.findByNo(no);

      if (stock == null) {
        throw new Exception("해당 번호의 상품이 없습니다.");

      }
      request.setAttribute("stock", stock);
      request.getRequestDispatcher("/stock/StockDetail.jsp").forward(request, response);

    } catch (Exception e) {
      throw new ServletException(e);
    }
  }

}






