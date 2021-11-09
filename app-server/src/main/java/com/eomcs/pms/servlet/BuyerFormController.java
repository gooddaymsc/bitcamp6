package com.eomcs.pms.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/buyer/form")
public class BuyerFormController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // 출력을 담당할 뷰를 호출한다.
    request.setAttribute("pageTitle", "회원가입(구매자)");
    request.setAttribute("contentUrl", "/buyer/BuyerForm.jsp");
    request.getRequestDispatcher("/template2.jsp").forward(request, response);


  }
}







