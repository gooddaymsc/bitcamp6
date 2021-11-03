package com.eomcs.pms.servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import com.eomcs.pms.dao.CommentDao;
import com.eomcs.pms.domain.Comment;


@WebServlet("/board/comment/detail")
public class CommentDetailController extends HttpServlet {
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
      int no = Integer.parseInt(request.getParameter("no"));
      Comment comment = commentDao.findByNo(no);

      if (comment == null) {
        throw new Exception("해당 번호의 게시글이 없습니다.");
      }

      request.setAttribute("comment", comment);
      request.getRequestDispatcher("/comment/CommentDetail.jsp").forward(request, response);

    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}









