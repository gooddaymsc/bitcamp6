package com.eomcs.pms.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/main/findpwForm")
public class FindpwFormController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.getParameter("name");
    request.getParameter("id");
    request.getParameter("phoneOrEmail");
    request.getRequestDispatcher("FindpwForm.jsp").forward(request, response);
  }
}



