package com.eomcs.pms.servlet;

import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.BookingDao;
import com.eomcs.pms.dao.CartDao;
import com.eomcs.pms.dao.StockDao;
import com.eomcs.pms.domain.Booking;
import com.eomcs.pms.domain.Cart;

@WebServlet("/booking/add")
public class BookingAddController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  SqlSession sqlSession;
  CartDao cartDao;
  StockDao stockDao;
  BookingDao bookingDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    cartDao = (CartDao) 웹애플리케이션공용저장소.getAttribute("cartDao");
    stockDao = (StockDao) 웹애플리케이션공용저장소.getAttribute("stockDao");
    bookingDao = (BookingDao) 웹애플리케이션공용저장소.getAttribute("bookingDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    HttpSession session = request.getSession(false);

    if (session.getAttribute("loginUser") == null) {
      response.sendRedirect("/drinker/login/menu");
      return;
    }

    try {
      System.out.println("100");
      Booking booking = new Booking();
      int cartNo = Integer.parseInt(request.getParameter("cartNumber"));
      String id = request.getParameter("id");
      Cart cart = cartDao.findByNo(cartNo, id);
      int rest = cart.getStock().getStocks() - cart.getCartStocks();
      if (rest<0) {
        System.out.println("200");
        throw new Exception("재고가 부족합니다.");
      }

      booking.setCart(cart);
      booking.setBookingDate(Date.valueOf(request.getParameter("bookingDate")));
      //        booking.setBookingTime(bookingHelper.checkTime("픽업시간(예 10:30) : ", cart.getStock()));
      booking.setBookingTime(request.getParameter("bookingTime"));

      booking.setBookingStocks(cart.getCartStocks());
      booking.setBookingPrice(cart.getCartPrice());
      System.out.println("2");

      booking.setPaymentType(Integer.parseInt(request.getParameter("paymentType")));
      booking.setPaymentStatus(BookingValidation.paymentStatus(booking));
      // 판매자 재고에서 예약(결제)한 상품 재고 수 빼기 > 서버에서 구현해야함.
      cart.getStock().setStocks(cart.getStock().getStocks() - cart.getCartStocks());

      // 구매자 장바구니에서 예약(결제)한 상품 빼기
      stockDao.update(cart.getStock());
      bookingDao.insert(booking);
      bookingDao.insertList(booking);
      cartDao.delete(cart);
      sqlSession.commit();
      response.setHeader("Refresh", "1;url=list?id="+id);
      System.out.println("3");
      request.getRequestDispatcher("BookingAdd.jsp").forward(request, response);

    } catch(Exception e){
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);   
    }
  }
}






