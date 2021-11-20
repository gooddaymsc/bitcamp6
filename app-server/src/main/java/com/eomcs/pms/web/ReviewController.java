package com.eomcs.pms.web;

import java.io.PrintWriter;
import java.util.Collection;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.eomcs.pms.dao.ProductDao;
import com.eomcs.pms.dao.ReviewDao;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.Review;

@Controller
public class ReviewController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired ReviewDao reviewDao;
  @Autowired ProductDao productDao;
  @Autowired ServletContext sc;

  @GetMapping("/product/review/form")
  public ModelAndView form(int no, HttpServletRequest request, HttpServletResponse response) throws Exception {

    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    HttpSession session = request.getSession(false);

    if (session.getAttribute("loginUser") == null) {
      out.printf("<script>alert('로그인 후 사용 가능합니다.'); location.href='../main/loginForm'</script>");
      out.flush();
    }

    System.out.println("review1");
    int productNumber = no;
    Member member = (Member) request.getSession(false).getAttribute("loginUser");

    Review review = reviewDao.reviewIs(productNumber, member.getId());

    if (review != null) {
      out.printf("<script>alert('이미 등록된 리뷰가 있습니다.'); location.href='../detail?no=%d'</script>", productNumber);
      out.flush();
    } else {

      ModelAndView mv = new ModelAndView();
      mv.addObject("productNumber", productNumber);
      mv.addObject("pageTitle", "새리뷰");
      mv.addObject("contentUrl", "product/review/ReviewForm.jsp");
      mv.setViewName("template2");
      return mv;
    }
    return null;
  }

  @PostMapping("/product/review/add")
  public ModelAndView add(Product product, Review review, HttpServletRequest request, HttpServletResponse response) throws Exception {


    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    HttpSession session = request.getSession(false);

    if (session.getAttribute("loginUser") == null) {
      out.printf("<script>alert('로그인 후 사용 가능합니다.'); location.href='../main/loginForm'</script>");
      out.flush();
    }

    Member member = (Member) request.getSession(false).getAttribute("loginUser");

    Product oldProduct =  productDao.findByNo(product.getProductNumber());
    oldProduct.setReviewerNum(product.getReviewerNum()+1);
    review.setProductNo(product.getProductNumber());
    //    review.setReviewProduct(product.getProductName());
    review.setMember(member);

    reviewDao.insert(review);
    product.setRate(reviewDao.avg(review));
    productDao.updateRate(product);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:../show?no="+review.getProductNo());
    return mv;
  }

  @GetMapping("/product/review/list")
  public ModelAndView list(Product product) throws Exception {

    Collection<Review> reviewList = reviewDao.findAll(product.getProductNumber());

    if (reviewList.equals(null)) {
      System.out.println("등록된 댓글이 없습니다.");
    }

    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "리뷰목록");
    mv.addObject("contentUrl", "product/ProductDetail.jsp");
    mv.setViewName("template2");
    return mv;
  }

  @GetMapping("/product/review/detail")
  public ModelAndView detail(int no, HttpServletRequest request, HttpServletResponse response) throws Exception {

    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    HttpSession session = request.getSession(false);

    if (session.getAttribute("loginUser") == null) {
      out.printf("<script>alert('로그인 후 사용 가능합니다.'); location.href='../../main/loginForm'</script>");
      out.flush();
      return null;
    }
    Member member = (Member) request.getSession(false).getAttribute("loginUser");
    try {
      Review review = reviewDao.findByNo(no);

      if (review.equals(null)) {
        throw new Exception("해당 번호의 리뷰가 없습니다.");
      }

      if (review.getMember().getId().equals(member.getId()) || member.getId().equals("admin") ) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("review", review);
        mv.addObject("pageTitle", "리뷰상세보기");
        mv.addObject("contentUrl", "product/review/ReviewDetail.jsp");
        mv.setViewName("template1");
        return mv;
      } else {
        out.printf("<script>alert('본인 리뷰만 수정 및 삭제할 수 있습니다.'); location.href='../show?no=%d'</script>", review.getProductNo());
        out.flush();
      }
    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
    return null;
  }

  @PostMapping("/product/review/update") // 아... 모르겠다!!!!
  public ModelAndView update(int reviewNo, Review review) throws Exception {
    Review oldReview = reviewDao.findByNo(reviewNo);
    System.out.println("review10000");

    if (oldReview.equals(null)) {
      throw new Exception("해당 번호의 리뷰가 없습니다.");
    }

    reviewDao.update(review);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:../show?no="+oldReview.getProductNo());
    return mv;
  }

  @GetMapping("/product/review/delete")
  public ModelAndView delete(Review review) throws Exception {
    Review oldReview = reviewDao.findByNo(review.getNo());
    if (oldReview.equals(null)) {
      throw new Exception("해당 번호의 리뷰가 없습니다.");
    }

    int no = oldReview.getProductNo();

    reviewDao.delete(oldReview.getNo());
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:../show?no="+no);
    return mv;
  }

  @GetMapping("/product/review/find")
  public ModelAndView find(int no, HttpServletRequest request, HttpServletResponse response) throws Exception {

    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    HttpSession session = request.getSession(false);

    if (session.getAttribute("loginUser") == null) {
      out.printf("<script>alert('로그인 후 사용 가능합니다.'); location.href='../../main/loginForm'</script>");
      out.flush();
    }

    Member member = (Member) request.getSession(false).getAttribute("loginUser");

    Collection<Review> reviewList = reviewDao.myReview(member.getId());
    if (reviewList.equals(null)) {
      throw new Exception("작성한 리뷰가 없습니다.");
    }

    ModelAndView mv = new ModelAndView();
    mv.addObject("reviewList", reviewList);
    mv.addObject("pageTitle", "내리뷰");
    mv.addObject("contentUrl", "product/review/FindReviewList.jsp");
    mv.setViewName("template2"); 

    return mv;
  }

}
