package com.eomcs.pms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.ProductDao;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.ProductType;

@WebServlet("/product/update")
public class ProductUpdateController extends HttpServlet {
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

    try {
      // 왜 안될까요?
      String name = request.getParameter("productName");
      Product product = productDao.findByProduct(name);

      //      int no = Integer.parseInt(request.getParameter("no"));  
      //      Product product = productDao.findByNo(no);
      // form에(add)할때 상품번호를 입력받지 않음.. 고유item은 productName..(회원 id 처럼..)

      // 그냥 해봄..
      //      Product product = new Product();
      //      int no = product.getProductNumber();
      //      product = productDao.findByNo(no);


      if (product == null) {
        throw new Exception("해당 이름의 상품이 없습니다.");

      } else {

        ProductType productType = new ProductType();
        productType.setType(request.getParameter("type"));
        productType.setSubType(request.getParameter("subType"));
        String type = request.getParameter("type");
        String subType = request.getParameter("subType");

        try {
          product.setProductType(new ProductHandlerHelper(productDao).promptType(type, subType));
        } catch (Exception e1) {
          System.out.println(1);
          e1.printStackTrace();
        }

        product.setCountryOrigin(request.getParameter("countryOrigin"));

        if(product.getProductType().getType().equals("와인")) {
          product.setVariety(request.getParameter("variety"));
        }

        product.setVolume(Integer.parseInt(request.getParameter("volume")));
        product.setAlcoholLevel(Integer.parseInt(request.getParameter("alcoholLevel")));
        product.setSugarLevel(Integer.parseInt(request.getParameter("sugarLevel")));
        product.setAcidity(Integer.parseInt(request.getParameter("acidity")));
        product.setWeight(Integer.parseInt(request.getParameter("weight")));

        productDao.update(product);
        sqlSession.commit();
      }
      response.sendRedirect("list");

    } catch (Exception e) {
      // 변경 오류가 발생했을 때만 출력하게 한다.
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println("<html>");
      out.println("<head>");
      out.println("  <title>상품변경</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>상품변경결과</h1>");
      out.println("상품 변경 오류!");
      out.println("<a href='list'>[목록]</a><br>");
      out.println("</body>");
      out.println("</html>");
      e.printStackTrace();
    }
  }
}


//@Override
//public void execute(CommandRequest request) throws Exception {
//  System.out.println("[상품 변경]");
//
//  int productNumber = (Integer) request.getAttribute("productNumber");
//
//  Product product =  productDao.findByNo(productNumber);
//
//  String name = Prompt.inputString("상품이름(" + product.getProductName()  + ")? ");
//  if (productDao.findByProduct(name)!=null) {
//    System.out.println("중복되는 이름입니다.\n");
//    return;  }
//  String type = ProductValidation.checkType("주종(" + product.getProductType().getType() + ")? ");
//  String subType = ProductValidation.checkSubType(("상세주종(" + product.getProductType().getSubType() + ")? "),type);
//  String made = Prompt.inputString("원산지(" + product.getCountryOrigin() + ")? ");
//  String grapes = product.getVariety();
//  if(type.equals("와인")) {
//    grapes = Prompt.inputString("품종(" + product.getVariety() + ")? ");
//  }
//  int volumes = Prompt.inputInt("용량(" + product.getVolume() +")?");
//  float abv = Prompt.inputFloat("알콜도수(" + product.getAlcoholLevel() + ")? ");
//  int sweet = ProductValidation.checkNum("당도(" + product.getSugarLevel() + ")? ");
//  int acidic = ProductValidation.checkNum("산도(" + product.getAcidity() + ")? ");
//  int body = ProductValidation.checkNum("바디감(" + product.getWeight() + ")? ");
//
//
//  String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
//  if (input.equalsIgnoreCase("y")) {
//    product.setProductName(name);
//    product.setProductType(new ProductHandlerHelper(productDao).promptType(type, subType));
//    product.setCountryOrigin(made);
//    if(type.equals("와인")){
//      product.setVariety(grapes);
//    } else {
//      product.setVariety(null);
//    }
//    product.setVolume(volumes);
//    product.setAlcoholLevel(abv);
//    product.setSugarLevel(sweet);
//    product.setAcidity(acidic);
//    product.setWeight(body);
//
//    productDao.update(product);
//    sqlSession.commit();
//    System.out.println("상품정보를 변경하였습니다.\n");
//  } else {
//    System.out.println("상품정보 변경을 취소하였습니다.\n");
//    return;
//  }
//}
//}













