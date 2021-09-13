package com.eomcs.pms.handler;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Cart;
import com.eomcs.pms.domain.CartList;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.Seller;
import com.eomcs.pms.domain.Stock;
import com.eomcs.util.Prompt;

public class ProductSearchHandler extends AbstractProductHandler {

  StockPrompt stockPrompt;
  ProductPrompt productPrompt;
  List<Product> productList;
  MemberPrompt memberPrompt;
  CartPrompt cartPrompt;

  public ProductSearchHandler(ProductPrompt productPrompt,  StockPrompt stockPrompt, 
      List<Product> productList, MemberPrompt memberPrompt,  CartPrompt cartPrompt) {
    this.productPrompt = productPrompt;
    this.stockPrompt = stockPrompt;
    this.productList = productList;
    this.memberPrompt = memberPrompt;
    this.cartPrompt = cartPrompt;
  }

  @Override
  public void execute() {
    String storeName;
    //String storeAdress;
    String nowLoginId = App.getLoginUser().getId();

    System.out.println("[상품검색]");

    String productName  = productPrompt.findByProduct2(Prompt.inputString("상품입력: "));   
    HashMap<String, Seller> sellerInfo = memberPrompt.findByAdress(Prompt.inputString("주소입력: "));   

    System.out.println("==========상품 목록==========");

    while(true) {
      int size = 1;
      for(Product product : productList) {
        if(product.getProductName().equals(productName)) {
          System.out.printf("상품번호: %d \n", size++);
          System.out.printf("상품명: %s\n", product.getProductName());
          System.out.printf("주종: %s\n", product.getProductType());
          System.out.printf("원산지: %s\n", product.getCountryOrigin());
          System.out.printf("품종: %s\n", product.getVariety());
          System.out.printf("알콜도수: %.2f\n", product.getAlcoholLevel()); 
          System.out.printf("당도: %d, 산도: %d, 바디감:%d\n", product.getSugerLevel(),product.getAcidity(),product.getWeight());
        }
      }






      if(App.getLoginUser().getAuthority() == Menu.ACCESS_LOGOUT) {
        System.out.println("로그인 후 이용가능합니다.");
        return;
      }

      for (HashMap.Entry<String, Seller> entry : sellerInfo.entrySet()) {
        System.out.printf("가게명 : %s, 가게주소 : %s\n, 재고수량 : %d",
            entry.getValue().getBusinessName(),
            entry.getValue().getBusinessAddress());
      }

      //구매자 id의 cartList에 상품 담기.
      System.out.println();
      System.out.println("\n[장바구니 등록]");
      Cart cart = new Cart();
      HashMap<String, Stock> hashStock = stockPrompt.findBySellerId(productName);

      if(hashStock.size() == 0) {
        System.out.println("해당상품을 갖는 판매자가 없습니다.");
        return;
      }

      storeName = Prompt.inputString("가게명을 선택하세요 >");
      cart.setStock(hashStock.get(storeName));
      int stockNumber = Prompt.inputInt("수량 : ");
      while(true) {
        if(stockNumber <= hashStock.get(storeName).getStocks()){
          cart.setCartStocks(stockNumber);
          break;
        }
        else {
          System.out.println("주문수량이 재고를 초과하였습니다.");
          return;
        }
      }
      cart.setCartPrice(hashStock.get(storeName).getPrice()*stockNumber);
      cart.setCartNumber(cartPrompt.findCartListIndexById(nowLoginId));
      cart.setSellerId(memberPrompt.findByPlaceName(storeName).getId());
      cart.setRegistrationDate(new Date(System.currentTimeMillis()));
      System.out.println("장바구니가 등록되었습니다.");
      CartList cartList = cartPrompt.findCartListById(nowLoginId);
      cartList.getPrivacyCart().add(cart);
      return;
    }
  }
}
















