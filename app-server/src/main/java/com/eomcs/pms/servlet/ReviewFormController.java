package com.eomcs.pms.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/product/review/form")
public class ReviewFormController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // 출력을 담당할 뷰를 호출한다.
    int productNumber = Integer.parseInt(request.getParameter("no"));
    System.out.println(productNumber);
    request.setAttribute("productNumber", productNumber);
    request.getRequestDispatcher("/review/ReviewForm.jsp").forward(request, response);
  }
}







