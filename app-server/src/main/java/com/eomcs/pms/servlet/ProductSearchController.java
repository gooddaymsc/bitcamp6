package com.eomcs.pms.servlet;

import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import com.eomcs.menu.Menu;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.dao.ProductDao;
import com.eomcs.pms.dao.ReviewDao;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.Seller;
import com.eomcs.util.Prompt;

@WebServlet("/product/search")
public class ProductSearchController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  ProductDao productDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    productDao = (ProductDao) 웹애플리케이션공용저장소.getAttribute("productDao");

  }

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    HashMap<String, Seller> map = new HashMap<>();

    //      try {
    String adress = Prompt.inputString("주소입력: ");

    map = productValidation.findByAdress(adress, productNumber); 
    //break;

    //        catch (Exception e) {
    //        System.out.println("* 주소입력을 다시 해주세요. (예: 서울시 강남구 역삼동 / 0.취소) ");
    //      }
    // } 
    if(map == null) {
      System.out.println("해당 위치에 판매처가 없습니다.\n");
    } else {
      System.out.println();
      System.out.println("[현재 상품 판매처]");

      for (HashMap.Entry<String, Seller> entry : map.entrySet()) { //판매자 id 추가
        System.out.printf("%-6s\t%-6s\t%-19s\t%-12s\t%-4s\n","가게명", "판매자", "주소", "연락처", "재고수량");
        System.out.println("--------------------------------------------------------------------------");
        System.out.printf("%-6s\t%-6s\t%-19s\t%-12s\n", 
            entry.getValue().getBusinessName(),
            entry.getValue().getMember().getId(),
            entry.getValue().getBusinessAddress(),
            entry.getValue().getBusinessPlaceNumber(),
            entry.getValue().getMember().getId(), productNumber).getStocks()
        //    productValidation.findStockById(entry.getValue().getMember().getId(), productNumber).getStocks());
        request.setAttribute("storeName",entry.getValue().getBusinessName());
      }
    }

  }
}
}

