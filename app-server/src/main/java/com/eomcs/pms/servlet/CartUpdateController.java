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

@WebServlet("/product/update")
public class CartUpdateController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  CartDao cartDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    cartDao = (CartDao) 웹애플리케이션공용저장소.getAttribute("cartDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      int no = Integer.parseInt(request.getParameter("cartNumber"));
      String id = request.getParameter("f-buyerId");

      Cart cart = cartDao.findByNo(no, id);

      if (cart == null) {
        throw new Exception("해당 번호와 아이디의 장바구니가 없습니다.");
      }

      cart.setCartStocks(Integer.parseInt(request.getParameter("stocks")));
      cart.setCartPrice(Integer.parseInt(request.getParameter("stocks")) * 
          Integer.parseInt(request.getParameter("price")));
      cartDao.update(cart);
      sqlSession.commit();
      response.sendRedirect("list");

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }

  //  @Override
  //  public void execute(CommandRequest request) throws Exception {
  //    while(true) {
  //      System.out.println("[장바구니 변경]");
  //      String nowLoginId = ClientApp.getLoginUser().getId();
  //      int cartNo = (Integer) request.getAttribute("cartNo");
  //      Cart cart = cartDao.findByNo(cartNo, nowLoginId);
  //
  //      int cartstocks = checkNum(String.format("수량(변경 전 : %d) : ", cart.getCartStocks()));
  //      String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
  //      if (input.equalsIgnoreCase("y")) {
  //        cart.setCartStocks(cartstocks);
  //        cart.setCartPrice(cart.getStock().getPrice()*cartstocks);
  //        cartDao.update(cart);
  //        sqlSession.commit();
  //
  //        System.out.println("장바구니를 변경하였습니다.");
  //        return;
  //      } else {
  //        System.out.println("장바구니 변경을 취소하였습니다.");
  //        return;
  //      }
  //    }
  //  }

}
