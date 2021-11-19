package com.eomcs.pms.web;

import java.io.PrintWriter;
import java.sql.Date;
import java.util.Collection;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.eomcs.pms.dao.BookingDao;
import com.eomcs.pms.dao.CartDao;
import com.eomcs.pms.dao.StockDao;
import com.eomcs.pms.domain.Booking;
import com.eomcs.pms.domain.Cart;
import com.eomcs.pms.domain.CartList;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Stock;
import com.eomcs.pms.servlet.BookingValidation;

@Controller
public class BookingController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired StockDao stockDao;
  @Autowired CartDao cartDao;
  @Autowired BookingDao bookingDao;
  @Autowired ServletContext sc;

  @GetMapping("/booking/form")
  public ModelAndView form(int no, String id) {
    ModelAndView mv = new ModelAndView();
    mv.addObject("cartNo", no);
    mv.addObject("id", id);
    mv.addObject("pageTitle", "새예약");
    mv.addObject("contentUrl", "booking/BookingForm.jsp");
    mv.setViewName("template2");
    return mv;
  }

  @PostMapping("/booking/add")
  public ModelAndView add(int cartNumber, String id, Booking booking, Stock stock, HttpServletRequest request, HttpServletResponse response) throws Exception {

    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    HttpSession session = request.getSession(false);

    if (session.getAttribute("loginUser") == null) {
      out.printf("<script>alert('로그인 후 사용 가능합니다.'); location.href='../main/loginMenu'</script>");
      out.flush();
    }

    Cart cart = cartDao.findByNo(cartNumber, id);
    int rest = cart.getStock().getStocks() - cart.getCartStocks();
    if (rest<0) {
      throw new Exception("재고가 부족합니다.");
    }

    booking.setCart(cart);

    booking.setBookingStocks(cart.getCartStocks());
    booking.setBookingPrice(cart.getCartPrice());

    booking.setPaymentStatus(BookingValidation.paymentStatus(booking));

    cart.getStock().setStocks(cart.getStock().getStocks() - cart.getCartStocks());

    // 구매자 장바구니에서 예약(결제)한 상품 빼기
    stockDao.update(cart.getStock());
    bookingDao.insert(booking);
    bookingDao.insertList(booking);
    cartDao.delete(cart);

    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:list?id="+id);
    return mv;
  }

  @GetMapping("/booking/list")
  public ModelAndView list(CartList cartList, HttpServletRequest request, HttpServletResponse response) throws Exception {

    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    HttpSession session = request.getSession(false);

    if (session.getAttribute("loginUser") == null) {
      out.printf("<script>alert('로그인 후 사용 가능합니다.'); location.href='../main/loginMenu'</script>");
      out.flush();
    }

    Member member = (Member) request.getSession(false).getAttribute("loginUser");
    Collection<Booking> bookingList = null;
    if (member.getAuthority()==2) {
      bookingList = bookingDao.findAll1(member.getId());

      ModelAndView mv = new ModelAndView();
      mv.addObject("bookingList", bookingList);
      mv.addObject("pageTitle", "예약목록");
      mv.addObject("contentUrl", "booking/BookingBuyerList.jsp");
      mv.setViewName("template2");
      return mv;

    } else {
      bookingList = bookingDao.findAll2(member.getId());

      ModelAndView mv = new ModelAndView();
      mv.addObject("bookingList", bookingList);
      mv.addObject("pageTitle", "예약목록");
      mv.addObject("contentUrl", "booking/BookingSellerList.jsp");
      mv.setViewName("template2");
      return mv;
    }
  }

  @GetMapping("/booking/detail")
  public ModelAndView detail(int no, HttpServletRequest request, HttpServletResponse response) throws Exception {

    Member member = (Member) request.getSession(false).getAttribute("loginUser");
    String id = member.getId();

    Booking booking = null;
    if (member.getAuthority()==2) {
      booking = bookingDao.findByNo1(no, id);
    } else {
      booking = bookingDao.findByNo2(no, id);
    }
    if (booking == null) {
      throw new Exception("해당 상품의 예약이 없습니다.");
    }

    ModelAndView mv = new ModelAndView();
    mv.addObject("booking", booking);
    mv.addObject("pageTitle", "예약상세보기");
    mv.addObject("contentUrl", "booking/BookingDetail.jsp");
    mv.setViewName("template1");
    return mv;
  }

  @PostMapping("/booking/update")
  public ModelAndView update(String id2, Booking booking, HttpServletRequest request) throws Exception {

    String nowLoginId =  id2;
    int no = booking.getBookingNumber();
    Booking oldBooking = bookingDao.findByNo1(no, nowLoginId);

    oldBooking.setBookingTime(request.getParameter("bookingTime"));
    oldBooking.setBookingDate(Date.valueOf(request.getParameter("bookingDate")));

    bookingDao.update(oldBooking);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:list");
    return mv;
  }

  @GetMapping("/booking/delete")
  public ModelAndView delete(int no) throws Exception {

    bookingDao.delete(no);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:list");
    return mv;
  }
}
