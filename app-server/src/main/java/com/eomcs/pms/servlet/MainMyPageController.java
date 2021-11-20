package com.eomcs.pms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.eomcs.pms.dao.BoardDao;
import com.eomcs.pms.dao.CommentDao;
import com.eomcs.pms.dao.ReviewDao;
import com.eomcs.pms.domain.Board;
import com.eomcs.pms.domain.Comment;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Review;

//@WebServlet("/main/myPage")
public class MainMyPageController extends HttpServlet {
  private static final long serialVersionUID = 1L;
  ReviewDao reviewDao;
  CommentDao commentDao;
  BoardDao boardDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    reviewDao = (ReviewDao) 웹애플리케이션공용저장소.getAttribute("reviewDao");
    commentDao = (CommentDao) 웹애플리케이션공용저장소.getAttribute("commentDao");
    boardDao = (BoardDao) 웹애플리케이션공용저장소.getAttribute("boardDao");

  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    // 출력을 담당할 뷰를 호출한다.
    HttpSession session = request.getSession(false);

    if (session.getAttribute("loginUser") == null) {
      out.printf("<script>alert('로그인 후 사용 가능합니다.'); location.href='../main/loginMenu'</script>");
      out.flush();
      return;
    }
    Member member = (Member) request.getSession(false).getAttribute("loginUser");
    try {
      Collection<Review> reviewList = reviewDao.myReview(member.getId());
      Collection<Board> boardList = boardDao.findMine(member.getNumber());
      Collection<Comment> commentList = commentDao.findMine(member.getNumber());
      request.setAttribute("reviewList", reviewList);
      request.setAttribute("boardList", boardList);
      request.setAttribute("commentList", commentList);
      request.setAttribute("pageTitle", "MyPage");
      request.setAttribute("contentUrl", "/main/MyPage.jsp");
      request.getRequestDispatcher("/template1.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}

