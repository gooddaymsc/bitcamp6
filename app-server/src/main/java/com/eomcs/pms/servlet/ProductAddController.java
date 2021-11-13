package com.eomcs.pms.servlet;

import java.io.IOException;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.ProductDao;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.ProductType;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
@WebServlet("/product/add")
public class ProductAddController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  SqlSession sqlSession;
  ProductDao productDao;

  @Override
  public void init() {
    ServletContext 웹애플리케이션공용저장소 = getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    productDao = (ProductDao) 웹애플리케이션공용저장소.getAttribute("productDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      Product product = new Product();
      product.setProductName(request.getParameter("productName"));
      String subType = request.getParameter("subType");
      String type = request.getParameter("type");

      ProductType productType = new ProductType();
      productType.setType(type);
      productType.setSubType(subType);

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
      //      product.setPhoto(request.getParameter("photo"));

      System.out.println("yyyyy");
      Part photoPart = request.getPart("photo");
      System.out.println("kkkkk");
      if (photoPart.getSize() > 0) {
        String filename = UUID.randomUUID().toString();
        photoPart.write(getServletContext().getRealPath("/upload/product") + "/" + filename);
        product.setPhoto(filename);

        Thumbnails.of(getServletContext().getRealPath("/upload/product") + "/" + filename)
        .size(300, 300)
        .outputFormat("jpg")
        .crop(Positions.CENTER)
        .toFiles(new Rename() {
          @Override
          public String apply(String name, ThumbnailParameter param) {
            return name + "_300x300";
          }
        });

        Thumbnails.of(getServletContext().getRealPath("/upload/product") + "/" + filename)
        .size(1000, 1000)
        .outputFormat("jpg")
        .crop(Positions.CENTER)
        .toFiles(new Rename() {
          @Override
          public String apply(String name, ThumbnailParameter param) {
            return name + "_1000x1000";
          }
        });
      }

      productDao.insert(product);
      sqlSession.commit();
      response.sendRedirect("list");
    } catch(Exception e){
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);   
    }
  }
}










