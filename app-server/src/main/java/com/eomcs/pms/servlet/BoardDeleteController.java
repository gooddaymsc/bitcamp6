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
import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.BoardDao;
import com.eomcs.pms.domain.Board;
import com.eomcs.pms.domain.Member;

@WebServlet("/board/delete")
public class BoardDeleteController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  BoardDao boardDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    boardDao = (BoardDao) 웹애플리케이션공용저장소.getAttribute("boardDao");
  }


  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    HttpSession session = request.getSession(false);

    if (session.getAttribute("loginUser") == null) {
      response.sendRedirect("/drinker/login/menu");
      return;
    }


    Member member = (Member) request.getSession(false).getAttribute("loginUser");

    try {
      int no = Integer.parseInt(request.getParameter("no"));

      Board board = boardDao.findByNo(no);

      if (board == null) {
        throw new Exception("해당 번호의 게시글이 없습니다.<br>");
      } 
      if (board.getWriter().getId().equals(member.getId())) {
        //        out.println("<html>");
        //        out.println("<head>");
        //        out.println("  <title>게시글삭제</title>");
        //        out.println("</head>");
        //        out.println("<body>");
        //        out.println("<h1>게시글삭제결과</h1>");

        boardDao.delete(board); 
        sqlSession.commit();


        out.println("<a href='list'>[목록]<a><br>");
      } else {
        out.printf("<script>alert('본인 게시글만 수정 및 삭제할 수 있습니다.'); location.href='detail?no=%d'</script>", board.getBoardNumber());
        out.flush();
      }
    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);   
    }
  }
}

