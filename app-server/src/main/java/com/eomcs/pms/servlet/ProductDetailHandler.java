package com.eomcs.pms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import com.eomcs.pms.dao.ProductDao;
import com.eomcs.pms.domain.Product;

@WebServlet("/product/detail")
public class ProductDetailHandler extends HttpServlet {
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
    out.println("  <title>상품상세</title>");
    out.println("  <style>");
    out.println("  label {");
    out.println("    margin-right: 5px;");
    out.println("    text-align: right;");
    out.println("    display: inline-block;");
    out.println("    width: 60px;");
    out.println("  }");
    out.println("  </style>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>상품 상세</h1>");

    int no = Integer.parseInt(request.getParameter("no"));

    try {
      Product product = productDao.findByNo(no);

      if (product == null) {
        out.println("해당 번호의 상품이 없습니다.");

      } else {
        out.println("<form action='update'>");
        out.printf("<label for='f-type'>주종</label> <input id='f-type' type='text' name='type' value='%s'><br>\n", product.getProductType().getType());
        out.printf("<label for='f-subType'>상세주종</label> <input id='f-subType' type='text' name='subType' value='%s'><br>\n", product.getProductType().getSubType());
        out.printf("<label for='f-countryOrigin'>원산지</label> <input id='f-countryOrigin' type='text' name='countryOrigin' value='%s'><br>\n", product.getCountryOrigin());
        out.printf("<label for='f-variety'>품종</label> <input id='f-variety' type='text' name='variety' value='%s'><br>\n", product.getVariety());
        out.printf("<label for='f-volume'>용량</label> <input id='f-volume' type='text' name='volume' value='%d'><br>\n", product.getVolume());
        out.printf("<label for='f-alcoholLevel'>도수</label> <input id='f-alcoholLevel' type='text' name='alcoholLevel' value='%s'><br>\n", product.getAlcoholLevel());
        out.printf("<label for='f-sugarLevel'>당도</label> <input id='f-sugarLevel' type='text' name='sugarLevel' value='%s'><br>\n", product.getSugarLevel());
        out.printf("<label for='f-acidity'>산도</label> <input id='f-acidity' type='text' name='acidity' value='%s'><br>\n", product.getAcidity());
        out.printf("<label for='f-weight'>바디감</label> <input id='f-weight' type='text' name='weight' value='%s'><br>\n", product.getWeight());
        out.println();

        out.println("<button>변경</button>");
        out.printf(" <a href='delete?no=%d'>[삭제]</a>", product.getProductNumber());
        out.println(" <a href='list'>[목록]</a><br>");
        out.println("</form>");
      }
    } catch (Exception e) {
      throw new ServletException(e);
    }

    out.println("</body>");
    out.println("</html>");
  }
}




