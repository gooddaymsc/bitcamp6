package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Stock;

public class StockListHandler extends AbstractStockHandler {
  AbstractSellerPrivacyHandler sellermemberList;
  public StockListHandler(List<Stock> stockList, AbstractSellerPrivacyHandler sellermemberList) {
    super(stockList);
    this.sellermemberList = sellermemberList;
  }


  @Override
  public void execute() {
    if (App.getLoginUser().getAuthority() == Menu.ACCESS_LOGOUT) {
      System.out.println("해당 메뉴는 로그인 후 사용가능합니다.\n로그인 후 사용해주세요.");
      return;
    }
    if (App.loginPrivacy.getAuthority()!=Menu.ACCESS_SELLER) {
      System.out.println("[재고 목록]");

      for (Stock stock : stockList) {
        System.out.printf("%d, %s, %d, %d\n", 
            stock.getStockNumber(),
            stock.getProduct().getProductName(), 
            stock.getPrice(), 
            stock.getStocks());
      }
    } else {
      System.out.println("[재고 목록]");
      if (sellermemberList.findById(App.getLoginUser().getId()).getStock()==null) {
        System.out.println("아직 등록하신 재고가 없습니다.");
        return;
      }
      List<Stock> stockList = sellermemberList.findById(App.getLoginUser().getId()).getStock();
      for (Stock stock : stockList) {
        System.out.printf("%d, %s, %d, %d\n", 
            stock.getStockNumber(),
            stock.getProduct().getProductName(), 
            stock.getPrice(), 
            stock.getStocks());
      }
    }
  }

}






