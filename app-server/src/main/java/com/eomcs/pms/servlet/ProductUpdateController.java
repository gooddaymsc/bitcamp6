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
      int no = Integer.parseInt(request.getParameter("productNumber"));
      Product product = productDao.findByNo(no);

      if (product == null) {
        throw new Exception("해당 이름의 상품이 없습니다.");
      }
      ProductType productType = new ProductType();
      productType.setType(request.getParameter("type"));
      productType.setSubType(request.getParameter("subType"));
      String type = request.getParameter("type");
      String subType = request.getParameter("subType");
      product.setProductType(new ProductHandlerHelper(productDao).promptType(type, subType));

      product.setCountryOrigin(request.getParameter("countryOrigin"));
      if(product.getProductType().getType().equals("와인")) {
        product.setVariety(request.getParameter("variety"));
      }
      product.setVolume(Integer.parseInt(request.getParameter("volume")));
      product.setAlcoholLevel(Float.parseFloat(request.getParameter("alcoholLevel")));
      product.setSugarLevel(Integer.parseInt(request.getParameter("sugarLevel")));
      product.setAcidity(Integer.parseInt(request.getParameter("acidity")));
      product.setWeight(Integer.parseInt(request.getParameter("weight")));
      productDao.update(product);
      sqlSession.commit();
      response.sendRedirect("list");

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}


