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
import com.eomcs.pms.dao.MemberDao;
import com.eomcs.pms.domain.Comment;
import com.eomcs.pms.domain.Member;


@WebServlet("/comment/add")
public class CommentAddController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  CommentDao commentDao;
  MemberDao memberDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    commentDao = (CommentDao) 웹애플리케이션공용저장소.getAttribute("commentDao");
    memberDao = (MemberDao) 웹애플리케이션공용저장소.getAttribute("memberDao");

  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      Comment comment = new Comment();
      int boardNo = Integer.parseInt(request.getParameter("boardNumber"));
      comment.setBoardNumber(boardNo);
      comment.setContent(request.getParameter("content"));
      Member member = memberDao.findById(request.getParameter("writer"));
      comment.setWriter(member);
      commentDao.insert(comment);
      sqlSession.commit();

      response.setHeader("Refresh", "1;url=../board/detail?no="+boardNo);
      request.getRequestDispatcher("comment/CommentAdd.jsp").forward(request, response);

    } catch(Exception e){
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);   
    }
  }
}







