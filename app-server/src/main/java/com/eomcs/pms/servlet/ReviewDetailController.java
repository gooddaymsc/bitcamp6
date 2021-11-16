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
import com.eomcs.pms.dao.ReviewDao;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Review;

@WebServlet("/product/review/detail")
public class ReviewDetailController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  ReviewDao reviewDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    reviewDao = (ReviewDao) 웹애플리케이션공용저장소.getAttribute("reviewDao");

  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    HttpSession session = request.getSession(false);

    if (session.getAttribute("loginUser") == null) {
      out.printf("<script>alert('로그인 후 사용 가능합니다.'); location.href='../../main/loginMenu'</script>");
      out.flush();
      return;
    }
    Member member = (Member) request.getSession(false).getAttribute("loginUser");
    System.out.println("1");
    try {
      int no = Integer.parseInt(request.getParameter("no"));
      Review review = reviewDao.findByNo(no);
      System.out.println(member.getId());

      if (review.equals(null)) {
        throw new Exception("해당 번호의 리뷰가 없습니다.");
      }

      if (review.getMember().getId().equals(member.getId())) {

        request.setAttribute("review", review);
        request.setAttribute("pageTitle", "리뷰상세보기");
        request.setAttribute("contentUrl", "/product/review/ReviewDetail.jsp");
        request.getRequestDispatcher("/template2.jsp").forward(request, response);
        //        request.getRequestDispatcher("./ReviewDetail.jsp").forward(request, response);
      } else {
        out.printf("<script>alert('본인 리뷰만 수정 및 삭제할 수 있습니다.'); location.href='../detail?no=%d'</script>", review.getProductNo());
        out.flush();
      }
    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}




