package com.eomcs.pms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/product/form")
public class ProductFormHandler extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("  <title>새상품</title>");
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
    out.println("<h1>새상품</h1>");

    out.println("<form action='add'>");
    out.println("<label for='f-productName'>상품명</label> <input id='f-productName' type='text' name='productName'><br>");
    //    out.println("<label for='f-type'>주종</label> <input id='f-type' type='text' name='type'><br>");
    //    out.println("<label for='f-subType'>상세주종</label> <input id='f-subType' type='text' name='subType'><br>");
    out.println("<label for='f-countryOrigin'>원산지</label> <input id='f-countryOrigin' type='text' name='countryOrigin'><br>");
    //    out.println("<label for='f-variety'>품종</label> <input id='f-variety' type='text' name='variety'><br>");
    out.println("<label for='f-volume'>용량</label> <input id='f-volume' type='text' name='volume'><br>");
    out.println("<label for='f-alcoholLevel'>도수</label> <input id='f-alcoholLevel' type='text' name='alcoholLevel'><br>");
    //    out.println("<label for='f-sugarLevel'>당도</label> <input id='f-sugarLevel' type='text' name='sugarLevel'><br>");
    //    out.println("<label for='f-acidity'>산도</label> <input id='f-acidity' type='text' name='acidity'><br>");
    //    out.println("<label for='f-weight'>바디감</label> <input id='f-weight' type='text' name='weight'><br>");

    out.println("<button>등록</button><br>");
    out.println("</form>");

    out.println("</body>");
    out.println("</html>");
  }
}







