package com.eomcs.pms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.BoardDao;
import com.eomcs.pms.domain.Board;


@WebServlet("/board/add")
public class BoardAddHandler extends HttpServlet{
  private static final long serialVersionUID = 1L;

  BoardDao boardDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    boardDao = (BoardDao) 웹애플리케이션공용저장소.getAttribute("boardDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();


    out.println("<html>");
    out.println("<head>");
    out.println("  <title>게시글 등록</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>게시글 등록결과</h1>");

    Board board = new Board();
    board.setTitle(request.getParameter("제목 : "));
    board.setContent(request.getParameter("내용 : "));
    //board.setWriter(ClientApp.getLoginUser());
    board.setRegistrationDate(new Date(System.currentTimeMillis()));
    //    BoardTag boardTag = new BoardTag();
    //    boardTag.setTag(Prompt.inputString("태그 : "));
    //    board.setBoardTag(boardTag);

    try { 
      boardDao.insert(board);
      boardDao.insertTag(board);
      // boardDao.insertBoardTag(board.getBoardNumber(), board.getBoardTag().getTagNumber());
      sqlSession.commit();

      out.println("게시글을 등록했습니다.<br>");
      out.println("<a href='list'>[목록]</a><br>");

    } catch (Exception e) {
      out.println("목록 조회 오류!");
      e.printStackTrace();
    }
    out.println("</body>");
    out.println("</html>");

    response.setHeader("Refresh", "1;url=list");

  }
}







