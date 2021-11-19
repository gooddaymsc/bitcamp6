package com.eomcs.pms.web;

import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;
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
import com.eomcs.pms.dao.CartDao;
import com.eomcs.pms.dao.StockDao;
import com.eomcs.pms.domain.Cart;
import com.eomcs.pms.domain.CartList;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Stock;

@Controller
public class CartController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired StockDao stockDao;
  @Autowired CartDao cartDao;
  @Autowired ServletContext sc;

  @GetMapping("/cart/form")
  public ModelAndView form(int no) {
    ModelAndView mv = new ModelAndView();
    mv.addObject("stockNo", no);
    mv.addObject("pageTitle", "장바구니담기");
    mv.addObject("contentUrl", "cart/CartForm.jsp");
    mv.setViewName("template2");
    return mv;
  }

  @PostMapping("/cart/add")
  public ModelAndView add(Cart cart, Stock stock, HttpServletRequest request, HttpServletResponse response) throws Exception {

    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    HttpSession session = request.getSession(false);

    if (session.getAttribute("loginUser") == null) {
      out.printf("<script>alert('로그인 후 사용 가능합니다.'); location.href='../main/loginMenu'</script>");
      out.flush();
    }

    Member buyer = (Member) request.getSession(false).getAttribute("loginUser");

    Stock oldStock = stockDao.findByNo(stock.getStockNumber());
    cart.setStock(oldStock);
    cart.setCartStocks(stock.getStocks());

    if (cart.getStock().getStocks() >= stock.getStocks()) {
      cart.setCartPrice(cart.getCartStocks() * stock.getPrice());
      cart.setId(buyer.getId());
      cartDao.insert(cart);
      sqlSessionFactory.openSession().commit();

      ModelAndView mv = new ModelAndView();
      mv.setViewName("redirect:list");
      return mv;
    } else {        
      out.printf("<script>alert('재고 수량을 초과해서 장바구니에 담을 수 없습니다.'); location.href='../cart/form?no=%d'</script>", stock.getStockNumber());
      out.flush();
    }
    return null;
  }

  @GetMapping("/cart/list")
  public ModelAndView list(CartList cartList, HttpServletRequest request, HttpServletResponse response) throws Exception {

    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    HttpSession session = request.getSession(false);

    if (session.getAttribute("loginUser") == null) {
      out.printf("<script>alert('로그인 후 사용 가능합니다.'); location.href='../main/loginMenu'</script>");
      out.flush();
    }

    Member buyer = (Member) request.getSession(false).getAttribute("loginUser");

    String id = buyer.getId();
    Collection<Cart> list = cartDao.findAll(id);

    cartList.setId(id);
    cartList.setPrivacyCart((List<Cart>) list);

    ModelAndView mv = new ModelAndView();
    mv.addObject("id", id);
    mv.addObject("cartList", cartList.getPrivacyCart());
    mv.addObject("pageTitle", "장바구니목록");
    mv.addObject("contentUrl", "cart/CartList.jsp");
    mv.setViewName("template2");
    return mv;
  }

  @GetMapping("/cart/detail")
  public ModelAndView detail(int cartNumber, String id) throws Exception {

    Cart cart = cartDao.findByNo(cartNumber, id);

    if (cart == null) {
      throw new Exception("해당 번호의 장바구니가 없습니다.");
    }

    ModelAndView mv = new ModelAndView();
    mv.addObject("cart", cart);
    mv.addObject("pageTitle", "장바구니상세보기");
    mv.addObject("contentUrl", "cart/CartDetail.jsp");
    mv.setViewName("template1");
    return mv;
  }

  @PostMapping("/cart/update")
  public ModelAndView update(Stock stock, int cartNumber, String buyerId) throws Exception {

    Cart cart = cartDao.findByNo(cartNumber, buyerId);

    if (cart == null) {
      throw new Exception("해당 번호와 아이디의 장바구니가 없습니다.");
    }

    cart.setCartStocks(stock.getStocks());

    cartDao.update(cart);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:list");
    return mv;
  }

  @GetMapping("/cart/delete")
  public ModelAndView delete(int no, HttpServletRequest request, HttpServletResponse response) throws Exception {

    HttpSession session = request.getSession(false);
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();

    if (session.getAttribute("loginUser") == null) {
      out.printf("<script>alert('로그인 후 사용 가능합니다.'); location.href='../main/loginMenu'</script>");
      out.flush();
    }

    Member buyer = (Member) request.getSession(false).getAttribute("loginUser");
    String id = buyer.getId();

    Cart cart = cartDao.findByNo(no, id);
    if (cart == null) {
      throw new Exception("해당 번호의 장바구니가 없습니다.");
    }

    cartDao.delete(cart);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:list");
    return mv;
  }








}
