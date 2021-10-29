package com.eomcs.pms.servlet;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import com.eomcs.pms.dao.BuyerDao;
import com.eomcs.pms.domain.Buyer;


@WebServlet("/buyer/list")
public class BuyerListController extends HttpServlet {
  private static final long serialVersionUID = 1L;
  BuyerDao buyerDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    buyerDao = (BuyerDao) 웹애플리케이션공용저장소.getAttribute("buyerDao");
  }

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {
    try {
      Collection<Buyer> buyerList =  buyerDao.findAll();

      request.setAttribute("buyerList", buyerList);

      RequestDispatcher 요청배달자 = request.getRequestDispatcher("/buyer/BuyerList.jsp");
      요청배달자.forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);

      RequestDispatcher 요청배달자 = request.getRequestDispatcher("/Error.jsp");
      요청배달자.forward(request, response);
    }
  }
}







