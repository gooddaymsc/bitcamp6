package com.eomcs.pms.servlet;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import com.eomcs.pms.dao.CommentDao;
import com.eomcs.pms.domain.Comment;

@WebServlet("/comment/list")
public class CommentListContoller extends HttpServlet  {

  private static final long serialVersionUID = 1L;

  CommentDao commentDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    commentDao = (CommentDao) 웹애플리케이션공용저장소.getAttribute("commentDao");
  }


  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {
    try {
      System.out.println("1");
      //      int boardNo = (Integer) request.getAttribute("no");
      int boardNo =1;
      System.out.println("2");
      Collection<Comment> commentList = commentDao.findAll(boardNo);
      System.out.println("3");
      request.setAttribute("commentList", commentList);
      System.out.println("4");
      request.getRequestDispatcher("CommentList.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}









