package com.eomcs.pms.servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import com.eomcs.pms.dao.BookingDao;
import com.eomcs.pms.domain.Booking;

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
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    try {
      String nowLoginId = "1";

      int No = Integer.parseInt(request.getParameter("no"));
      Booking booking = bookingDao.findByNo1(No, nowLoginId);

      if (booking == null) {
        throw new Exception("해당 상품의 예약이 없습니다.");
      }
      request.setAttribute("booking", booking);
      request.getRequestDispatcher("/booking/BookingDetail.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
