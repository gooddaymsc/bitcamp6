package com.eomcs.pms.servlet;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.CartDao;
import com.eomcs.pms.dao.StockDao;
import com.eomcs.pms.domain.Cart;
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
    // 가게명, 상품명, 수량

    try {
      Cart cart = new Cart();
      Stock stock = new Stock();

      stock = stockDao.findByNo(Integer.parseInt(request.getParameter("stockNumber")));
      cart.setStock(stock);

      cart.setCartStocks(Integer.parseInt(request.getParameter("stocks")));

      cart.setCartPrice(cart.getCartStocks() * stock.getPrice());

      cart.setId("5");

      cartDao.insert(cart);
      sqlSession.commit();
      response.setHeader("Refresh", "1;url=list");
      request.getRequestDispatcher("cart/CartAdd.jsp").forward(request, response);
    } catch(Exception e){
      System.out.println("20");
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);   
    }
  }
}