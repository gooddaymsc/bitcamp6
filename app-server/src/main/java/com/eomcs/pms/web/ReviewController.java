package com.eomcs.pms.web;

import java.util.Collection;
import javax.servlet.ServletContext;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.eomcs.pms.dao.ProductDao;
import com.eomcs.pms.dao.ReviewDao;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.Review;

public class ReviewController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired ReviewDao reviewDao;
  @Autowired ProductDao productDao;
  @Autowired Member member;
  @Autowired ServletContext sc;

  @GetMapping("/product/review/form")
  public ModelAndView form(int no) throws Exception {

    //    response.setContentType("text/html; charset=UTF-8");
    //    PrintWriter out = response.getWriter();
    //    HttpSession session = request.getSession(false);
    //
    //    if (session.getAttribute("loginUser") == null) {
    //      out.printf("<script>alert('로그인 후 사용 가능합니다.'); location.href='../main/loginMenu'</script>");
    //      out.flush();
    //      return;
    //    }

    int productNumber = no;

    //    Review review = reviewDao.reviewIs(productNumber, member.getId());

    //    if (review != null) {
    //      out.printf("<script>alert('이미 등록된 리뷰가 있습니다.'); location.href='../detail?no=%d'</script>", productNumber);
    //      out.flush();
    //    } else {

    ModelAndView mv = new ModelAndView();
    mv.addObject("productNumber", productNumber);
    mv.addObject("pageTitle", "새리뷰");
    mv.addObject("contentUrl", "/product/review/ReviewForm.jsp");
    mv.setViewName("template2");
    return mv;
  }
  //}

  @PostMapping("/product/add")
  public ModelAndView add(Product product, Review review) throws Exception {

    reviewDao.insert(review);
    product.setRate(reviewDao.avg(review));
    productDao.updateRate(product);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    //    response.sendRedirect("../show?no="+review.getProductNo());
    mv.setViewName("redirect:list");
    return mv;
  }

  @GetMapping("/product/review/list")
  public ModelAndView list(int no) throws Exception {

    Collection<Review> reviewList = reviewDao.findAll(no);

    if (reviewList.equals(null)) {
      System.out.println("등록된 댓글이 없습니다.");
    }

    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "리뷰목록");
    mv.addObject("contentUrl", "/product/ProductDetail.jsp");
    mv.setViewName("template2");
    return mv;
  }

  @GetMapping("/product/review/detail")
  public ModelAndView detail(int no) throws Exception {

    Review review = reviewDao.findByNo(no);

    ModelAndView mv = new ModelAndView();
    mv.addObject("review", review);
    mv.addObject("pageTitle", "리뷰상세보기");
    mv.addObject("contentUrl", "/product/review/ReviewDetail.jsp");
    mv.setViewName("template1");
    return mv;
  }

  @PostMapping("/product/review/update")
  public ModelAndView update(int no) throws Exception {
    Review review = reviewDao.findByNo(no);

    if (review.equals(null)) {
      throw new Exception("해당 번호의 리뷰가 없습니다.");
    }

    reviewDao.update(review);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:detail");
    //    response.sendRedirect("../detail?no="+review.getProductNo());
    return mv;
  }

  @GetMapping("/product/review/delete")
  public ModelAndView delete(int no) throws Exception {
    Review review = reviewDao.findByNo(no);
    if (review.equals(null)) {
      throw new Exception("해당 번호의 리뷰가 없습니다.");
    }

    reviewDao.delete(review.getNo());
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:detail");
    //    response.sendRedirect("../detail?no="+review.getProductNo());
    return mv;
  }

  @GetMapping("/product/review/find")
  public ModelAndView find(int no) throws Exception {
    Collection<Review> reviewList = reviewDao.myReview(member.getId());
    if (reviewList.equals(null)) {
      throw new Exception("작성한 리뷰가 없습니다.");
    }

    ModelAndView mv = new ModelAndView();
    mv.addObject("reviewList", reviewList);
    mv.addObject("pageTitle", "내리뷰");
    mv.addObject("contentUrl", "/product/review/FindReviewList.jsp");
    mv.setViewName("template2"); 

    return mv;
  }

}
