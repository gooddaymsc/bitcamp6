package com.eomcs.pms.servlet;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import com.eomcs.pms.dao.BoardDao;
import com.eomcs.pms.domain.Board;

@WebServlet("/board/list")
public class BoardListController extends HttpServlet{
  private static final long serialVersionUID = 1L;

  BoardDao boardDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    boardDao = (BoardDao) 웹애플리케이션공용저장소.getAttribute("boardDao");
  }

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {
    try {
      Collection<Board> boardList = boardDao.findAll();
      request.setAttribute("boardList", boardList);
      request.getRequestDispatcher("BoardList.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }  
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


