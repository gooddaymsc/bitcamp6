package com.eomcs.pms.web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.CommentDao;
import com.eomcs.pms.dao.MemberDao;
import com.eomcs.pms.domain.Comment;
import com.eomcs.pms.domain.Member;


//@WebServlet("/board/comment/add")
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
    HttpSession session = request.getSession(false);

    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();

    if (session.getAttribute("loginUser") == null) {
      out.printf("<script>alert('로그인 후 사용 가능합니다.'); location.href='../main/loginMenu'</script>");
      out.flush();
      return;
    }

    try {
      Comment comment = new Comment();

      // 로그인 구현 전엔 자신의 가상 데이터에 맞게 setNumber넣으세요..
      Member member = (Member) request.getSession(false).getAttribute("loginUser");

      member.setNumber(member.getNumber());
      comment.setWriter(member);
      comment.setBoardNumber(Integer.parseInt(request.getParameter("boardNumber")));
      comment.setContent(request.getParameter("content"));
      commentDao.insert(comment);
      sqlSession.commit();
      response.sendRedirect("../detail?no="+comment.getBoardNumber());


    } catch(Exception e){
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);   
    }
  }
}







