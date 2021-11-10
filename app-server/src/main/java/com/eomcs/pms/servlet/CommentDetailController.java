package com.eomcs.pms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.eomcs.pms.dao.CommentDao;
import com.eomcs.pms.domain.Comment;
import com.eomcs.pms.domain.Member;


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
  public void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();

    HttpSession session = request.getSession(false);

    if (session.getAttribute("loginUser") == null) {
      out.printf("<script>alert('로그인 후 사용 가능합니다.'); location.href='../main/loginMenu'</script>");
      out.flush();
      return;
    }

    Member member = (Member) request.getSession(false).getAttribute("loginUser");

    try {
      int no = Integer.parseInt(request.getParameter("no"));
      Comment comment = commentDao.findByNo(no);

      if (comment == null) {
        throw new Exception("해당 번호의  댓글이 없습니다.");
      }

      if (comment.getWriter().getId().equals(member.getId())) {
        request.setAttribute("comment", comment);
        request.setAttribute("pageTitle", "댓글상세보기");
        request.setAttribute("contentUrl", "/comment/CommentDetail.jsp");
        request.getRequestDispatcher("/template1.jsp").forward(request, response);
        //        request.getRequestDispatcher("/comment/CommentDetail.jsp").forward(request, response);

      } else {
        out.printf("<script>alert('본인 댓글만 수정 및 삭제할 수 있습니다.'); location.href='../detail?no=%d'</script>", comment.getBoardNumber());
        out.flush();
      }
    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}









