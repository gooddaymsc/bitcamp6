package com.eomcs.pms.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/main/findidMenu")
public class MainFindidController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.setAttribute("pageTitle", "아이디찾기");
    request.setAttribute("contentUrl", "/main/FindidForm.jsp");
    request.getRequestDispatcher("/template3.jsp").forward(request, response);
  } 
}



