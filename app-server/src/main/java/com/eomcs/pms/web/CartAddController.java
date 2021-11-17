package com.eomcs.pms.web;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.CartDao;
import com.eomcs.pms.dao.StockDao;
import com.eomcs.pms.domain.Cart;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Stock;

@WebServlet("/cart/add")
public class CartAddController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  SqlSession sqlSession;  
  StockDao stockDao;
  CartDao cartDao;
  //  CartHandlerHelper cartHelper;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    stockDao = (StockDao) 웹애플리케이션공용저장소.getAttribute("stockDao");
    cartDao = (CartDao) 웹애플리케이션공용저장소.getAttribute("cartDao");
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {    
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    HttpSession session = request.getSession(false);

    if (session.getAttribute("loginUser") == null) {
      out.printf("<script>alert('로그인 후 사용 가능합니다.'); location.href='../main/loginMenu'</script>");
      out.flush();
      return;
    }

    try {
      Member buyer = (Member) request.getSession(false).getAttribute("loginUser");
      Cart cart = new Cart();
      Stock stock = stockDao.findByNo(Integer.parseInt(request.getParameter("stockNumber")));
      cart.setStock(stock);
      cart.setCartStocks(Integer.parseInt(request.getParameter("stocks")));
      if (cart.getStock().getStocks() >= Integer.parseInt(request.getParameter("stocks"))) {
        cart.setCartPrice(cart.getCartStocks() * stock.getPrice());
        cart.setId(buyer.getId());
        cartDao.insert(cart);
        sqlSession.commit();
        response.sendRedirect("list");
      } else {        
        out.printf("<script>alert('재고 수량을 초과해서 장바구니에 담을 수 없습니다.'); location.href='../cart/form?no=%d'</script>", stock.getStockNumber());
        out.flush();
      }
    } catch(Exception e){
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);   
    }
  }
}