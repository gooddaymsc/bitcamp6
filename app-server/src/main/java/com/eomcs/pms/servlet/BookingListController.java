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
import javax.servlet.http.HttpSession;
import com.eomcs.pms.dao.BookingDao;
import com.eomcs.pms.domain.Booking;
import com.eomcs.pms.domain.Member;

@WebServlet("/booking/list")
public class BookingListController extends HttpServlet {
  private static final long serialVersionUID = 1L;
  BookingDao bookingDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    bookingDao = (BookingDao) 웹애플리케이션공용저장소.getAttribute("bookingDao");
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    HttpSession session = request.getSession(false);

    if (session.getAttribute("loginUser") == null) {
      response.sendRedirect("/drinker/login/menu");
      return;
    }

    try {
      Member buyer = (Member) request.getSession(false).getAttribute("loginUser");
      String id = buyer.getId();
      Collection<Booking> bookingList = bookingDao.findAll1(id);
      request.setAttribute("bookingList", bookingList);
      request.getRequestDispatcher("BookingList.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
