package com.eomcs.pms.web;

import java.io.PrintWriter;
import java.util.Collection;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.eomcs.pms.dao.BoardDao;
import com.eomcs.pms.dao.CommentDao;
import com.eomcs.pms.dao.MemberDao;
import com.eomcs.pms.dao.ProductDao;
import com.eomcs.pms.dao.ReviewDao;
import com.eomcs.pms.dao.SellerDao;
import com.eomcs.pms.domain.Board;
import com.eomcs.pms.domain.Comment;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.Review;
import com.eomcs.pms.domain.Seller;

@Controller
@RequestMapping("/main")
public class MainController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired MemberDao memberDao;
  @Autowired SellerDao sellerDao;
  @Autowired BoardDao boardDao;
  @Autowired ProductDao productDao;
  @Autowired CommentDao commentDao;
  @Autowired ReviewDao reviewDao;

  @GetMapping("menu")
  public ModelAndView menu(HttpServletRequest request) throws Exception {
    ModelAndView mv = new ModelAndView();
    request.setAttribute("rankingWine", productDao.rankingType("와인"));
    request.setAttribute("rankingWhiskey", productDao.rankingType("위스키"));
    request.setAttribute("rankingBrandy", productDao.rankingType("브랜디/꼬냑"));
    request.setAttribute("rankingVodka", productDao.rankingType("리큐르/보드카"));
    request.setAttribute("rankingTrad", productDao.rankingType("전통주"));

    mv.addObject("pageTitle", "메인");
    mv.addObject("contentUrl", "main/Menu.jsp");
    mv.setViewName("template0");
    return mv;
  }

  @PostMapping("search")
  public ModelAndView search(ServletRequest request) throws Exception {
    ModelAndView mv = new ModelAndView();
    String input = "%"+request.getParameter("search")+"%";
    Collection<Product> productList = productDao.search(input);
    Collection<Seller> sellerList = sellerDao.search(input);
    Collection<Board> boardList = boardDao.search(input);

    mv.addObject("sellerList", sellerList);
    mv.addObject("boardList", boardList);
    mv.addObject("productList", productList);
    mv.addObject("pageTitle", "전체검색");
    mv.addObject("contentUrl", "main/Search.jsp");
    mv.setViewName("template2");
    return mv;
  }

  @GetMapping("myPage")
  public ModelAndView myPage(ServletRequest request,   HttpSession session) throws Exception {
    ModelAndView mv = new ModelAndView();
    Member member = (Member) session.getAttribute("loginUser");

    if (member.getAuthority() == 2) {
      Collection<Review> reviewList = reviewDao.myReview(member.getId());
      mv.addObject("reviewList", reviewList);
    }

    Collection<Board> boardList = boardDao.findMine(member.getNumber());
    Collection<Comment> commentList = commentDao.findMine(member.getNumber());

    mv.addObject("boardList", boardList);
    mv.addObject("commentList", commentList);
    mv.addObject("pageTitle", "MyPage");
    mv.addObject("contentUrl", "main/MyPage.jsp");
    mv.setViewName("template1");
    return mv;
  }

  @GetMapping("findidMenu")
  public ModelAndView findidMenu(ServletRequest request,   HttpSession session) throws Exception {
    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "아이디찾기");
    mv.addObject("contentUrl", "main/FindidForm.jsp");
    mv.setViewName("template3");
    return mv;
  }

  @RequestMapping("findidResult")
  public ModelAndView findidResult(HttpServletRequest request, HttpServletResponse response,  HttpSession session) throws Exception {
    ModelAndView mv = new ModelAndView();
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();

    Member member = memberDao.findByName(request.getParameter("name"));

    if (member.getPhoneNumber().equals(request.getParameter("phoneOrEmail")) || (member.getEmail().equals(request.getParameter("phoneOrEmail")))) {
      String id = member.getId();
      request.setAttribute("id", id);

      mv.addObject("pageTitle", "아이디찾기 결과");
      mv.addObject("contentUrl", "main/FindidResult.jsp");
      mv.setViewName("template3");
      return mv;
    } else {
      out.printf("<script>alert('일치하는 회원정보를 찾을 수 없습니다.'); location.href='../main/menu'</script>");
      out.flush();
    }
    return null;
  }

  @GetMapping("findpwMenu")
  public ModelAndView findpwMenu(ServletRequest request,   HttpSession session) throws Exception {
    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "비밀번호찾기");
    mv.addObject("contentUrl", "main/FindpwForm.jsp");
    mv.setViewName("template3");
    return mv;
  }

  @RequestMapping("findpwForm")
  public ModelAndView findpwForm(HttpServletRequest request, HttpServletResponse response,  HttpSession session) throws Exception {
    ModelAndView mv = new ModelAndView();
    Member member = new Member();
    member.setPassword(request.getParameter("password"));
    member.setNumber(Integer.parseInt(request.getParameter("member_no")));

    memberDao.update(member);
    sqlSessionFactory.openSession().commit();

    mv.addObject("contentUrl", "main/menu");
    mv.setViewName("template3");
    return mv;
  }

  @RequestMapping("findpwResult")
  public ModelAndView findpwResult(HttpServletRequest request, HttpServletResponse response,  HttpSession session) throws Exception {
    ModelAndView mv = new ModelAndView();
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    Member member = memberDao.findById(request.getParameter("id"));


    if (((member.getPhoneNumber().equals(request.getParameter("phoneOrEmail")) || member.getEmail().equals(request.getParameter("phoneOrEmail"))))
        && (member.getId().equals(request.getParameter("id")))){

      mv.addObject("pageTitle", "비밀번호 변경");
      mv.addObject("contentUrl", "main/FindpwResult.jsp");
      mv.setViewName("template3");
      return mv;
    } else {
      out.printf("<script>alert('일치하는 회원정보를 찾을 수 없습니다.'); location.href='../main/menu'</script>");
      out.flush();
    }
    return null;
  }



}
