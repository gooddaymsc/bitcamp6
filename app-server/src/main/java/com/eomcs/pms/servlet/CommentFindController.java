package com.eomcs.pms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.CommentDao;
import com.eomcs.pms.domain.Comment;
import com.eomcs.pms.domain.Member;

@WebServlet("/board/comment/find")
public class CommentFindController extends HttpServlet  {
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
  public void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    HttpSession session = request.getSession(false);

    if (session.getAttribute("loginUser") == null) {
      out.printf("<script>alert('로그인 후 사용 가능합니다.'); location.href='../../main/loginMenu'</script>");
      out.flush();
      return;
    }
    Member member = (Member) request.getSession(false).getAttribute("loginUser");
    try {
      Collection<Comment> commentList = commentDao.findMine(member.getNumber());
      request.setAttribute("commentList", commentList);
      request.setAttribute("pageTitle", "내댓글");
      request.setAttribute("contentUrl", "/comment/CommentList.jsp");
      request.getRequestDispatcher("/template2.jsp").forward(request, response);


    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
