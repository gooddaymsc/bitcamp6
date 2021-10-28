package com.eomcs.pms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.ProductDao;
import com.eomcs.pms.domain.Product;

public class ProductAddHandler extends HttpServlet {
  private static final long serialVersionUID = 1L;

  SqlSession sqlSession;
  ProductDao productDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    productDao = (ProductDao) 웹애플리케이션공용저장소.getAttribute("productDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<html>");
    out.println("<head>");
    out.println("  <title>상품등록</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>상품등록결과</h1>");

    Product product = new Product();

    product.setProductName(productName);
    String type = ProductValidation.checkType("주종 : ");
    String subType = ProductValidation.checkSubType("상세주종 : ",type);

    product.setProductType(new ProductHandlerHelper(productDao).promptType(type, subType));
    product.setCountryOrigin(Prompt.inputString("원산지 : "));
    if(product.getProductType().getType().equals("와인")) {
      product.setVariety(Prompt.inputString("품종 : "));
    }
    product.setVolume(Prompt.inputInt("용량 : "));
    product.setAlcoholLevel(Prompt.inputFloat("알콜도수 : ")); 
    product.setSugarLevel(ProductValidation.checkNum("당도(1-5) : "));
    product.setAcidity(ProductValidation.checkNum("산도(1-5) : "));
    product.setWeight(ProductValidation.checkNum("바디감(1-5) : "));

    productDao.insert(product);
    sqlSession.commit();
    System.out.println("상품을 등록하였습니다.\n");

    //    member.setName(request.getParameter("name"));
    //    member.setEmail(request.getParameter("email"));
    //    member.setPassword(request.getParameter("password"));
    //    member.setPhoto(request.getParameter("photo"));
    //    member.setTel(request.getParameter("tel"));

    try {
      productDao.insert(product);
      sqlSession.commit();

      out.println("상품을 등록했습니다.<br>");

      out.println("<a href='list'>[목록]</a><br>");

    } catch (Exception e) {
      out.println("목록 조회 오류!");
      e.printStackTrace();
    }

    out.println("</body>");
    out.println("</html>");

    // 리프래시(refresh)
    // 웹브라우저에게 서버가 보내준 HTML을 출력한 후 
    // 1초가 경과하면 지정한 URL을 다시 요청하도록 명령한다.
    response.setHeader("Refresh", "1;url=list");
  }
}

////  @Override
//public void execute(CommandRequest request) throws Exception {
//
//  System.out.println("[상품 등록]");
//  Product product = new Product();
//  String productName = Prompt.inputString("상품명 : ");
//
//  if (productDao.findByProduct(productName)!=null) {
//    System.out.println("이미 추가된 상품입니다.\n");
//    return;  }
//
//  product.setProductName(productName);
//  String type = ProductValidation.checkType("주종 : ");
//  String subType = ProductValidation.checkSubType("상세주종 : ",type);
//
//  product.setProductType(new ProductHandlerHelper(productDao).promptType(type, subType));
//  product.setCountryOrigin(Prompt.inputString("원산지 : "));
//  if(product.getProductType().getType().equals("와인")) {
//    product.setVariety(Prompt.inputString("품종 : "));
//  }
//  product.setVolume(Prompt.inputInt("용량 : "));
//  product.setAlcoholLevel(Prompt.inputFloat("알콜도수 : ")); 
//  product.setSugarLevel(ProductValidation.checkNum("당도(1-5) : "));
//  product.setAcidity(ProductValidation.checkNum("산도(1-5) : "));
//  product.setWeight(ProductValidation.checkNum("바디감(1-5) : "));
//
//  productDao.insert(product);
//  sqlSession.commit();
//  System.out.println("상품을 등록하였습니다.\n");
//
//}  
//}











