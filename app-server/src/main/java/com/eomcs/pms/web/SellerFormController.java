package com.eomcs.pms.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/seller/form")
public class SellerFormController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // 출력을 담당할 뷰를 호출한다.
    request.setAttribute("pageTitle", "회원가입(판매자)");
    request.setAttribute("contentUrl", "/seller/SellerForm.jsp");
    request.getRequestDispatcher("/template2.jsp").forward(request, response);

  }
}







