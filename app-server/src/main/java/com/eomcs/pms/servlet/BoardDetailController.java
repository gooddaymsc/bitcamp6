package com.eomcs.pms.servlet;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.BoardDao;
import com.eomcs.pms.dao.CommentDao;
import com.eomcs.pms.domain.Board;
import com.eomcs.pms.domain.Comment;
import com.eomcs.pms.domain.LikeMember;


//@WebServlet("/board/detail")
public class BoardDetailController extends HttpServlet {
  private static final long serialVersionUID = 1L;
  SqlSession sqlSession;
  BoardDao boardDao; 
  CommentDao commentDao;
  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    boardDao = (BoardDao) 웹애플리케이션공용저장소.getAttribute("boardDao");
    commentDao = (CommentDao) 웹애플리케이션공용저장소.getAttribute("commentDao");
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      int no = Integer.parseInt(request.getParameter("no"));
      Board board = boardDao.findByNo(no);
      Collection<LikeMember> likeList = boardDao.findLikeList(no);
      board.setLikeMember((List<LikeMember>) likeList);
      board.setLikes(likeList.size());

      board.setViews(board.getViews()+1);
      Collection<Comment> commentList = commentDao.findAll(no);

      boardDao.updateCount(board.getBoardNumber());
      sqlSession.commit();
      request.setAttribute("board", board);
      request.setAttribute("commentList", commentList);
      request.setAttribute("pageTitle", "게시글상세보기");
      request.setAttribute("contentUrl", "/board/BoardDetail.jsp");
      request.getRequestDispatcher("/template1.jsp").forward(request, response);

    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}
