package com.eomcs.pms.servlet;

import java.io.IOException;
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

@WebServlet("/booking/detail")
public class BookingDetailController extends HttpServlet {
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

      //      String id = request.getParameter("id");

      int No = Integer.parseInt(request.getParameter("no"));
      Booking booking = bookingDao.findByNo1(No, id);
      System.out.println("10000");

      if (booking == null) {
        System.out.println("20000");
        throw new Exception("해당 상품의 예약이 없습니다.");
      }
      System.out.println("30000");
      request.setAttribute("booking", booking);
      request.getRequestDispatcher("/booking/BookingDetail.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
