package com.eomcs.pms.servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.CommentDao;
import com.eomcs.pms.domain.Comment;

@WebServlet("/comment/update")
public class CommentUpdateController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  CommentDao commentDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    commentDao = (CommentDao) 웹애플리케이션공용저장소.getAttribute("commentDao");
  }


  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      int no = Integer.parseInt(request.getParameter("commentNumber"));
      Comment comment = commentDao.findByNo(no);

      if (comment == null) {
        throw new Exception("해당 이름의 댓글이 없습니다.");
      }

      comment.setContent(request.getParameter("content"));

      commentDao.update(comment);
      sqlSession.commit();
      response.sendRedirect("list");

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}