package com.eomcs.pms.servlet;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import com.eomcs.pms.dao.CartDao;
import com.eomcs.pms.dao.SellerDao;
import com.eomcs.pms.domain.Cart;

@WebServlet("/cart/list")
public class CartListController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  CartDao cartDao;
  SellerDao sellerDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    cartDao = (CartDao) 웹애플리케이션공용저장소.getAttribute("cartDao");
    sellerDao = (SellerDao) 웹애플리케이션공용저장소.getAttribute("sellerDao");
  }

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    try {
      Collection<Cart> cartList = cartDao.findAll("1");
      //      List<Cart> cart = (List<Cart>)cartList;
      //      System.out.println(cart.get(0).getStock().getSeller().getBusinessName());


      request.setAttribute("cartList", cartList);
      request.getRequestDispatcher("CartList.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }

  //  @Override
  //  public void execute(CommandRequest request) throws Exception{
  //    String nowLoginId = ClientApp.getLoginUser().getId();
  //    Loop : while(true) {
  //      System.out.println("[장바구니 목록]");
  //      Collection<Cart> carts = cartDao.findAll(nowLoginId);
  //      CartList cartList = new CartList();
  //      cartList.setId(nowLoginId);
  //      cartList.setPrivacyCart((List<Cart>) carts);
  //
  //      if (cartList.getPrivacyCart().size() == 0) {
  //        System.out.println("장바구니에 담긴 상품이 없습니다.");
  //        return;
  //      }
  //      System.out.printf("%-3s\t%-6s\t%-6s\t%-6s\t%-6s\t%-6s\t%-6s\n",
  //          "번호", "가게명", "판매자", "상품명", "수량", "금액","등록일");
  //      System.out.println("--------------------------------------------------------------------------");
  //      int total = 0;
  //      for (Cart cart : cartList.getPrivacyCart()) {
  //        if (cart.getCartStocks()==-1) {
  //          continue;
  //        }
  //        System.out.printf("%-6d\t%-6s\t%-6s\t%-6s\t%-6d\t%-6d\t%-6s\n", // 장바구니 번호, 가게명, 상품명, 수량, 총액, 등록일
  //            cart.getCartNumber(), 
  //            sellerDao.findById(cart.getStock().getSeller().getMember().getId()).getBusinessName(),
  //            cart.getStock().getSeller().getMember().getId(),
  //            cart.getStock().getProduct().getProductName(), 
  //            cart.getCartStocks(), 
  //            cart.getStock().getPrice(),
  //            cart.getRegistrationDate());
  //        total += cart.getStock().getPrice() * cart.getCartStocks();
  //      }
  //      System.out.printf("\n>>> 총 금액 : %d원\n", total);
  //      System.out.println();
  //
  //      while(true) {
  //        System.out.println("1. 장바구니 상세보기 / 2. 판매자에게 문의하기 / 이전(0)");
  //        int choose = Prompt.inputInt("선택 > ");
  //        System.out.println();
  //        switch(choose) {
  //          case 1 : request.getRequestDispatcher("/cart/detail").forward(request); continue Loop;
  //          case 2 : request.getRequestDispatcher("/message/add").forward(request); continue Loop;
  //          case 0 : return;
  //        }
  //      }
  //    }
  //  }
}

