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
public class ProductDeleteHandler extends HttpServlet {
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

    // 리다이렉트(redirect)
    // 서버의 응답을 받는 즉시 지정한 URL로 요청하도록 웹브라우저에게 명령한다.
    // 리다이렉트의 경우 서버는 응답할 때 내용을 보내지 않는다.
    // 어? 그럼 위에서 출력한 내용은 어떻게 되나요?
    // => 위의 코드에서 출력한 내용은 현재 버퍼에 보관되어 있다.
    // => 아직 웹브라우저에게 보낸 상태가 아니다.
    // => 따라서 위 코드에서 출력한 모든 내용, 즉 버퍼에 들어 있는 콘텐트는 모두 버려진다.
    // => 그래서 클라이언트로 내용을 보내지 않는 것이다.
    // => 오직 다시 요청해야 하는 URL 정보만 보낸다.
    response.sendRedirect("list");
  }
}



