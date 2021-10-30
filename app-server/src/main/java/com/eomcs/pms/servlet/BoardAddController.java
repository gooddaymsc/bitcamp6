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

    Board board = new Board();

    board.setTitle(request.getParameter("title"));
    board.setContent(request.getParameter("content"));

    Member member = new Member();
    member.setNumber(1);
    board.setWriter(member);

    BoardTag boardTag = new BoardTag();
    boardTag.setTag(request.getParameter("tag"));
    board.setBoardTag(boardTag);

    try {
      System.out.println("0");
      boardDao.insert(board);
      System.out.println("1");
      boardDao.insertTag(board);
      System.out.println("2");

      boardDao.insertBoardTag(board.getBoardNumber(), board.getBoardTag().getTagNumber());
      System.out.println("3");

      sqlSession.commit();
      System.out.println(4);

      response.setHeader("Refresh", "1;url=list");
      System.out.println(5);

      request.getRequestDispatcher("/board/BoardAdd.jsp").forward(request, response);


    } catch(Exception e){
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);   
    }
  }
}






