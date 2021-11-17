package com.eomcs.pms.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/message/form")
public class MessageFormController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.setAttribute("theOtherId", request.getParameter("id"));
    request.setAttribute("pageTitle", "메시지작성");
    request.setAttribute("contentUrl", "/message/MessageForm.jsp");
    request.getRequestDispatcher("/template2.jsp").forward(request, response);

  }
}



