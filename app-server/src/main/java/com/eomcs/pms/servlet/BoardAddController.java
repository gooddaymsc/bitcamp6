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
import com.eomcs.pms.domain.BoardTag;
import com.eomcs.pms.domain.Member;


@WebServlet("/board/add")
public class BoardAddController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  SqlSession sqlSession;
  BoardDao boardDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    boardDao = (BoardDao) 웹애플리케이션공용저장소.getAttribute("boardDao");
  }


  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    HttpSession session = request.getSession(false);

    if (session.getAttribute("loginUser") == null) {
      out.printf("<script>alert('로그인 후 사용 가능합니다.'); location.href='../../main/loginMenu'</script>");
      out.flush();
      return;
    }

    try {
      Member writer = (Member) request.getSession(false).getAttribute("loginUser");
      Board board = new Board();
      System.out.println(request.getParameter("title"));
      board.setTitle(request.getParameter("title"));
      board.setContent(request.getParameter("content"));

      board.setWriter(writer);

      BoardTag boardTag = new BoardTag();
      boardTag.setTag(request.getParameter("tag"));
      board.setBoardTag(boardTag);

      boardDao.insert(board);
      boardDao.insertTag(board);
      boardDao.insertBoardTag(board.getBoardNumber(), board.getBoardTag().getTagNumber());
      sqlSession.commit();

      request.setAttribute("pageTitle", "게시판등록");
      request.setAttribute("contentUrl", "BoardAdd.jsp");
      response.setHeader("Refresh", "1;url=list");
      request.getRequestDispatcher("/template1.jsp").forward(request, response);

      //      request.getRequestDispatcher("BoardAdd.jsp").forward(request, response);

    } catch(Exception e){
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);   
    }
  }
}






