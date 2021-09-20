package com.eomcs.pms.handler;

import java.util.HashMap;
import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.Seller;
import com.eomcs.util.Prompt;

public class ProductSearchHandler extends AbstractProductHandler {

  StockPrompt stockPrompt;
  ProductPrompt productPrompt;
  MemberPrompt memberPrompt;
  CartPrompt cartPrompt;
  List<Product> productList;

  public ProductSearchHandler(ProductPrompt productPrompt,  StockPrompt stockPrompt, 
      MemberPrompt memberPrompt,  CartPrompt cartPrompt ,List<Product> productList 
      ) {
    this.productPrompt = productPrompt;
    this.stockPrompt = stockPrompt;
    this.memberPrompt = memberPrompt;
    this.cartPrompt = cartPrompt;
    this.productList = productList;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    String storeName;
    //String storeAdress;
    String nowLoginId = App.getLoginUser().getId();
    HashMap<String, Seller> sellerInfo = null;   
    System.out.println("[상품검색]");

    while (true) {
      String input = Prompt.inputString("상품입력: ");

      if (input.equals("")) {
        System.out.println("잘못 입력하셨습니다.");
        continue;
      }

      String productName  = productPrompt.findByProduct2(input);

      request.setAttribute("productName", productName); 
      request.getRequestDispatcher("/product/list").forward(request);
      // productList를 연결하면 모든 리스트가 뜨고
      // productDetail을 연결하면 입력한 상품정보는 잘 뜨는데
      // 다른기능들이 섞여 있고(상품평 등록) 그 이후에 필요로 하는
      // 재고확인과 장바구니 등록은 productList에 메서드가 있음....
      // 그래서 아래 원래의 코드를 사용해야 할듯.

      // 재고찾기는 별도의 클래스로 분리해야 할듯.(또는 아래 코드 사용)


      //      System.out.println("==========상품 목록==========");
      //
      //      while(true) {
      //        int size = 1;
      //        for(Product product : productList) {
      //          if(product.getProductName().equals(productName)) {
      //            System.out.printf("상품번호: %d \n", size++);
      //            System.out.printf("상품명: %s\n", product.getProductName());
      //            System.out.printf("주종: %s\n", product.getProductType());
      //            System.out.printf("원산지: %s\n", product.getCountryOrigin());
      //            System.out.printf("품종: %s\n", product.getVariety());
      //            System.out.printf("알콜도수: %.2f\n", product.getAlcoholLevel()); 
      //            System.out.printf("당도: %d, 산도: %d, 바디감:%d\n", product.getSugerLevel(),product.getAcidity(),product.getWeight());
      //            System.out.println("-----------------------------------------");
      //          }
      //        }
      //



      //      if(App.getLoginUser().getAuthority() == Menu.ACCESS_LOGOUT) {
      //        System.out.println("로그인 후 이용가능합니다.");
      //        return;
      //      }
      //
      //      System.out.println("[재고 찾기]");
      //
      //      while(true) {
      //        try {
      //          sellerInfo = memberPrompt.findByAdress(Prompt.inputString("주소입력: ")); 
      //          break;
      //
      //        } catch (Exception e) {
      //          System.out.println("* 주소입력을 다시 해주세요. (예: 서울시 강남구 역삼동) ");
      //        }
      //      }
      //
      //      if(sellerInfo == null) {
      //        System.out.println("해당 위치에 판매처가 없습니다.");
      //        return;
      //      }
      //
      //      for (HashMap.Entry<String, Seller> entry : sellerInfo.entrySet()) {
      //        System.out.printf("가게명 : %s, 가게주소 : %s\n, 재고수량 : %d",
      //            entry.getValue().getBusinessName(),
      //            entry.getValue().getBusinessAddress());
      //
      //      }

      //      //구매자 id의 cartList에 상품 담기.
      //      System.out.println();
      //      System.out.println("\n[장바구니 등록]");
      //      Cart cart = new Cart();
      //      HashMap<String, Stock> hashStock = stockPrompt.findBySellerId(productName);
      //
      //      if(hashStock.size() == 0 ) {
      //        System.out.println("해당상품을 갖는 판매자가 없습니다.");
      //        return;
      //      }
      //
      //      else {
      //        storeName = Prompt.inputString("가게명을 선택하세요 >");
      //        cart.setStock(hashStock.get(storeName));
      //        int stockNumber = Prompt.inputInt("수량 : ");
      //        while(true) {
      //          if(stockNumber <= hashStock.get(storeName).getStocks()){
      //            cart.setCartStocks(stockNumber);
      //            break;
      //          }
      //          else {
      //            System.out.println("주문수량이 재고를 초과하였습니다.");
      //            return;
      //          }
      //        }
      //        cart.setCartPrice(hashStock.get(storeName).getPrice()*stockNumber);
      //        cart.setCartNumber(cartPrompt.findCartListIndexById(nowLoginId));
      //        cart.setSellerId(memberPrompt.findByPlaceName(storeName).getId());
      //        cart.setRegistrationDate(new Date(System.currentTimeMillis()));
      //        System.out.println("장바구니가 등록되었습니다.");
      //
      //      CartList cartList = cartPrompt.findCartListById(nowLoginId);
      //      cartList.getPrivacyCart().add(cart);
      //      return;
    }
  }
}

















