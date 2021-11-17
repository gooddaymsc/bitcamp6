package com.eomcs.pms.web;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.BookingDao;
import com.eomcs.pms.domain.Booking;

@WebServlet("/booking/update")
public class BookingUpdateController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  SqlSession sqlSession;
  BookingDao bookingDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    bookingDao = (BookingDao) 웹애플리케이션공용저장소.getAttribute("bookingDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      String nowLoginId =  request.getParameter("id2");
      int No = Integer.parseInt(request.getParameter("bookingNumber"));
      Booking booking = bookingDao.findByNo1(No, nowLoginId);

      //      String reservationTime = bookingHelper.checkTime("픽업시간 변경 (기존 : " + booking.getBookingTime()+ ")", booking.getCart().getStock());
      booking.setBookingTime(request.getParameter("bookingTime"));
      booking.setBookingDate(Date.valueOf(request.getParameter("bookingDate")));
      bookingDao.update(booking);
      sqlSession.commit();
      response.sendRedirect("list");

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}






