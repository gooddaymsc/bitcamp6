package com.eomcs.pms.web;

import java.util.Collection;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
  public ModelAndView menu() throws Exception {
    ModelAndView mv = new ModelAndView();
    mv.addObject("rankingWine", productDao.rankingType("와인"));
    mv.addObject("rankingWhiskey", productDao.rankingType("위스키"));
    mv.addObject("rankingBrandy", productDao.rankingType("브랜디/꼬냑"));
    mv.addObject("rankingVodka", productDao.rankingType("리큐르/보드카"));
    mv.addObject("rankingTrad", productDao.rankingType("전통주"));
    mv.addObject("contentUrl", "main/Menu.jsp");
    mv.setViewName("template0");
    return mv;
  }

  @PostMapping("search")
  public ModelAndView search(String search) throws Exception {
    ModelAndView mv = new ModelAndView();
    String input = "%"+search+"%";
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
  public ModelAndView myPage(HttpSession session) throws Exception {
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
  public ModelAndView findidMenu() throws Exception {
    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "아이디찾기");
    mv.addObject("contentUrl", "main/FindidForm.jsp");
    mv.setViewName("template3");
    return mv;
  }

  @RequestMapping("findidResult")
  public ModelAndView findidResult(Member member) throws Exception {
    ModelAndView mv = new ModelAndView();

    Member oldMember = memberDao.findByName(member.getName());

    if (oldMember.getPhoneNumber().equals(member.getPhoneOrEmail()) || (oldMember.getEmail().equals(member.getPhoneOrEmail()))) {
      String id = oldMember.getId();
      mv.addObject("id", id);
      mv.addObject("pageTitle", "아이디찾기 결과");
      mv.addObject("contentUrl", "main/FindidResult.jsp");
      mv.setViewName("template3");
      return mv;
    } else {
      return null;
    }
  }
  @RequestMapping("checkId")
  @ResponseBody
  public String checkId(String name, String phoneOrEmail) throws Exception {
    Member oldMember = memberDao.findByName(name);
    // 이름과 폰/이메일 일치하는 회원이 있으면 false 없으면 true
    if (oldMember.getPhoneNumber().equals(phoneOrEmail) || (oldMember.getEmail().equals(phoneOrEmail))) {
      return "false";
    } else {
      return "true";
    }
  }

  @GetMapping("findpwMenu")
  public ModelAndView findpwMenu() throws Exception {
    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "비밀번호찾기");
    mv.addObject("contentUrl", "main/FindpwForm.jsp");
    mv.setViewName("template3");
    return mv;
  }

  @RequestMapping("findpwForm")
  public ModelAndView findpwForm(Member member) throws Exception {
    memberDao.update(member);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "로그인");
    mv.addObject("contentUrl", "main/Login.jsp");
    mv.setViewName("template3");
    return mv;
  }

  @RequestMapping("checkPw")
  @ResponseBody
  public String checkPw(String name, String phoneOrEmail, String id) throws Exception {
    Member oldMember = memberDao.findByIdName(id, name);
    // 이름과 폰/이메일 일치하는 회원이 있으면 false 없으면 true
    if (oldMember.getPhoneNumber().equals(phoneOrEmail) || (oldMember.getEmail().equals(phoneOrEmail))) {
      return "false";
    } else {
      return "true";
    }
  }
  @RequestMapping("findpwResult")
  public ModelAndView findpwResult(Member member, HttpSession session) throws Exception {
    ModelAndView mv = new ModelAndView();
    Member oldMember = memberDao.findByIdName(member.getId(), member.getName());


    if (oldMember.getPhoneNumber().equals(member.getPhoneOrEmail()) || (oldMember.getEmail().equals(member.getPhoneOrEmail()))) {
      mv.addObject("member_no", oldMember.getNumber());
      mv.addObject("pageTitle", "비밀번호 변경");
      mv.addObject("contentUrl", "main/FindpwResult.jsp");
      mv.setViewName("template3");
      return mv;
    } else {
      return null;
    }
  }
}