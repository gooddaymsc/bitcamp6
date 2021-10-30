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
import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.ProductDao;
import com.eomcs.pms.domain.Product;

@WebServlet("/product/delete")
public class ProductDeleteController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  ProductDao productDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    productDao = (ProductDao) 웹애플리케이션공용저장소.getAttribute("productDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<html>");
    out.println("<head>");
    out.println("  <title>상품삭제</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>상품삭제결과</h1>");

    try {
      int no = Integer.parseInt(request.getParameter("no"));

      Product product = productDao.findByNo(no);

      if (product == null) {
        out.println("해당 번호의 상품이 없습니다.<br>");

      } else {
        productDao.delete(product);
        sqlSession.commit();

        out.println("상품을 삭제하였습니다.<br>");
      }      

      out.println("<a href='list'>[목록]<a><br>");

    } catch (Exception e) {
      out.println("상품 삭제 오류!");
      e.printStackTrace();
    }

    out.println("</body>");
    out.println("</html>");

    response.sendRedirect("list");
  }
}



