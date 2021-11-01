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
import com.eomcs.pms.domain.Board;
import com.eomcs.pms.domain.Comment;
import com.eomcs.pms.domain.Member;


@WebServlet("/comment/add")
public class CommentAddController extends HttpServlet {
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

    Comment comment = new Comment();
    comment.setContent(request.getParameter("내용 : "));

    Member member = new Member();
    member.setNumber(1);
    comment.setWriter(member);

    Board board = new Board();
    board.setBoardNumber(1);
    comment.setBoardNumber(board.getBoardNumber());

    try {
      System.out.println(1);
      commentDao.insert(comment);
      System.out.println(2);
      sqlSession.commit();
      System.out.println(3);
      response.setHeader("Refresh", "1;url=list");
      System.out.println(4);
      request.getRequestDispatcher("comment/CommentAdd.jsp").forward(request, response);

    } catch(Exception e){
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);   
    }
  }
}







