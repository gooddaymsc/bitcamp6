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
import com.eomcs.pms.dao.BoardDao;
import com.eomcs.pms.domain.Board;

@WebServlet("/board/list")
public class BoardListHandler extends HttpServlet{
  private static final long serialVersionUID = 1L;

  BoardDao boardDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    boardDao = (BoardDao) 웹애플리케이션공용저장소.getAttribute("boardDao");
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("  <title>게시판</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>[게시글 목록]</h1>");
    out.println("<a href='form'>이전</a><br>");
    out.println("<a href='form'>게시글 등록</a>");
    out.println("<table border='1'>");
    out.println("<thead>");
    out.println("  <tr>");
    out.println("    <th>번호</th>");
    out.println("    <th>제목</th>");
    // out.println("    <th>태그</th>");
    out.println("    <th>작성자</th>");
    out.println("    <th>조회수</th>");
    // out.println("    <th>좋아요</th>");
    out.println("    <th>등록일</th>");
    out.println("  <tr>");
    out.println("</thead>");
    out.println("<tbody>");


    //        if (ClientApp.getLoginUser().getAuthority()!=Menu.ACCESS_LOGOUT) {
    //          System.out.println("|| 게시글 등록(A) / 이전(0)\n");  
    //        } else {
    //          System.out.println("|| 이전(0)\n");
    //        }

    try {

      Collection<Board> boardList = boardDao.findAll();

      for (Board board : boardList) {
        out.printf("<tr> "
            + "<td>%d<\td>"
            + "<td>%s<\td>" 
            //             + "<td>%s<\td>" 
            + "<td>%s<\td>" 
            + "<td>%d<\td>" 
            //             + "<td>%d<\td>" 
            + "<td>%s<\td>" 
            + "<\tr>\n",
            board.getBoardNumber(), 
            board.getTitle(), 
            //              board.getBoardTag().getTag(),
            board.getWriter().getId(),
            board.getViews(), 
            //            board.getLikes(),
            board.getRegistrationDate());
      }
    } catch (Exception e) {
      throw new ServletException(e);
    }
    out.println("</tbody>");
    out.println("</table>");
    out.println("</body>");
    out.println("</html>");
  }
}


//        if (ClientApp.getLoginUser().getAuthority()==Menu.ACCESS_LOGOUT) {
//          System.out.println("\n 게시글 등록(A) / 상세보기(R) / 검색(1)");
//          while (true) {
//            String choose = Prompt.inputString("선택 > ");
//
//            System.out.println();
//            switch (choose) {
//              case "0" : return;
//              case "a" :
//              case "A" : System.out.println("로그인 후 가능합니다.\n"); return;
//              case "r" :
//              case "R" : request.getRequestDispatcher("/board/detail").forward(request); continue Loop;
//              case "1" : request.getRequestDispatcher("/board/search").forward(request); continue Loop ;
//              default : System.out.println("잘못입력하셨습니다."); continue;
//            }
//          }
//        } else {
//          System.out.println("\n 상세보기(R) / 검색(1)");
//          while (true) {
//            String choose = Prompt.inputString("선택 > ");
//            System.out.println();
//            switch (choose) {
//              case "0" : return;
//              case "r" :
//              case "R" : request.getRequestDispatcher("/board/detail").forward(request); continue Loop;
//              case "1" : request.getRequestDispatcher("/board/search").forward(request); continue Loop;
//              case "a" :
//              case "A" : request.getRequestDispatcher("/board/add").forward(request); continue Loop;
//              default : System.out.println("잘못입력하셨습니다."); continue;
//            }
//          }


