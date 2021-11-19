package com.eomcs.pms.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/main/findpwMenu")
public class MainFindpwController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.setAttribute("pageTitle", "비밀번호찾기");
    request.setAttribute("contentUrl", "/main/FindpwForm.jsp");
    request.getRequestDispatcher("/template3.jsp").forward(request, response);
  }
}