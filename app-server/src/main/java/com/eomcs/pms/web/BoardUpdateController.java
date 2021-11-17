package com.eomcs.pms.web;

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
import com.eomcs.pms.domain.BoardTag;
import com.eomcs.pms.domain.Member;

@WebServlet("/board/update")
public class BoardUpdateController extends HttpServlet {
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

    System.out.println("boardError1");
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
      int no = Integer.parseInt(request.getParameter("boardNumber"));
      Board board = boardDao.findByNo(no);

      if (board == null) {
        throw new Exception("해당 번호의 게시글이 없습니다.");
      }
      if (board.getWriter().getId().equals(member.getId())) {

        System.out.println("boardError2");

        board.setTitle(request.getParameter("title"));
        board.setContent(request.getParameter("content"));

        BoardTag boardTag = new BoardTag();
        boardTag.setTag(request.getParameter("tag"));
        board.setBoardTag(boardTag);

        boardDao.update(board);
        sqlSession.commit();
        response.sendRedirect("list");

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

