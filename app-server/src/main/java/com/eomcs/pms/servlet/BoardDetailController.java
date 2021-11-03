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
import com.eomcs.pms.dao.BoardDao;
import com.eomcs.pms.dao.CommentDao;
import com.eomcs.pms.domain.Board;
import com.eomcs.pms.domain.Comment;


@WebServlet("/board/detail")
public class BoardDetailController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  BoardDao boardDao; 
  CommentDao commentDao;
  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    boardDao = (BoardDao) 웹애플리케이션공용저장소.getAttribute("boardDao");
    commentDao = (CommentDao) 웹애플리케이션공용저장소.getAttribute("commentDao");
  }

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {
    try {
      int no = Integer.parseInt(request.getParameter("no"));
      Board board = boardDao.findByNo(no);
      Collection<Comment> commentList = commentDao.findAll(no);

      if (board == null) {
        throw new Exception("해당 번호의 게시글이 없습니다.");
      }

      request.setAttribute("board", board);
      request.setAttribute("commentList", commentList);
      request.getRequestDispatcher("/board/BoardDetail.jsp").forward(request, response);

    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}









