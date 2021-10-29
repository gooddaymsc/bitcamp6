package com.eomcs.pms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import com.eomcs.pms.dao.ProductDao;
import com.eomcs.pms.domain.Product;

@WebServlet("/product/list")
public class ProductListHandler extends HttpServlet {
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

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("  <title>상품목록</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>상품 목록</h1>");
    out.println("<a href='form'>새상품등록</a><br>");
    out.println("<table border='1'>");
    out.println("<thead>");
    out.println("  <tr>");
    out.println("    <th>번호</th>");
    out.println("    <th>상품명</th>");
    out.println("    <th>주종-상세주종</th>");
    out.println("    <th>원산지</th>");
    out.println("    <th>용량</th>");
    out.println("    <th>당도</th>");
    out.println("    <th>산도</th>");
    out.println("    <th>바디감</th>");
    out.println("    <th>도수</th>");
    out.println("  <tr>");
    out.println("</thread>");
    out.println("<tbody>");

    try {
      Collection<Product> productList = productDao.findAll();

      for (Product product : productList) {
        out.printf("<tr>"
            + "<td>%d</td>"
            + " <td><a href='detail?no=%1$d'>%s</a></td>"
            + " <td>%s-%s</td>"
            + " <td>%s</td>"
            + " <td>%d</td>"
            + " <td>%d</td>"
            + " <td>%d</td>"
            + " <td>%d</td>"
            + " <td>%f</td>"
            + "</tr>\n", 
            product.getProductNumber(), 
            product.getProductName(), 
            product.getProductType().getType(),
            product.getProductType().getSubType(),
            product.getCountryOrigin(),
            product.getVolume(),
            product.getSugarLevel(),
            product.getAcidity(),
            product.getWeight(),
            product.getAlcoholLevel());
      }
    } catch (Exception e) {
      throw new ServletException(e);
    }
    out.println("</tbody>");
    out.println("</table>");
    out.println("</body>");
    out.println("</html>");
  }
}
