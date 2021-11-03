package com.eomcs.pms.servlet;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.eomcs.pms.dao.CommentDao;
import com.eomcs.pms.domain.Comment;

@WebServlet("/board/comment/list")
public class CommentListContoller extends HttpServlet  {

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
    try {
      //int boardNo = (Integer) request.getAttribute("no");
      //      int boardNo =1;
      //      Collection<Comment> commentList = commentDao.findAll(boardNo);
      //      request.setAttribute("commentList", commentList);   
      //      request.getRequestDispatcher("CommentList.jsp").forward(request, response);

      int boardNumber = (Integer)request.getAttribute("boardNumber");
      Collection<Comment> commentList = commentDao.findAll(boardNumber);

      if (commentList.equals(null)) {
        System.out.println("등록된 댓글이 없습니다.");
      }
      request.getRequestDispatcher("BoardDetail.jsp").forward(request, response);


    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}









