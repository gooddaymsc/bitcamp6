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
import com.eomcs.pms.domain.Cart;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.Seller;
import com.eomcs.pms.domain.Stock;

@WebServlet("/cart/add")
public class CartAddController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  SqlSession sqlSession;
  CartDao cartDao;
  //  CartHandlerHelper cartHelper;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    cartDao = (CartDao) 웹애플리케이션공용저장소.getAttribute("cartDao");
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // 구매자, 가게명, 판매자, 상품명, 수량, 금액

    Cart cart = new Cart();
    Stock stock = new Stock();
    Seller seller = new Seller();
    Member member = new Member();
    Product product = new Product();

    cart.setId(request.getParameter("buyerId"));
    seller.setBusinessName(request.getParameter("businessName"));
    member.setId(request.getParameter("sellerid"));
    product.setProductName(request.getParameter("productName"));
    cart.setCartStocks(Integer.parseInt(request.getParameter("stocks")));
    stock.setPrice(Integer.parseInt(request.getParameter("price")));

    seller.setMember(member);
    stock.setSeller(seller);
    stock.setProduct(product);
    cart.setStock(stock);

    try {
      cartDao.insert(cart);
      sqlSession.commit();
      response.setHeader("Refresh", "1;url=list");
      request.getRequestDispatcher("cart/CartAdd.jsp").forward(request, response);
    } catch(Exception e){
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);   
    }
  }
}