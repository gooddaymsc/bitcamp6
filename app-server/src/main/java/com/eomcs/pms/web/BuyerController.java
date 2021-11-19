package com.eomcs.pms.web;

import java.sql.Date;
import java.util.Collection;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.eomcs.pms.dao.BuyerDao;
import com.eomcs.pms.dao.MemberDao;
import com.eomcs.pms.domain.Buyer;
import com.eomcs.pms.domain.Member;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

@Controller
public class BuyerController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired BuyerDao buyerDao;
  @Autowired MemberDao memberDao;
  @Autowired ServletContext sc;

  @GetMapping("/buyer/form")
  public ModelAndView form() {
    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "회원가입(구매자)"); 
    mv.addObject("contentUrl", "buyer/BuyerForm.jsp");
    mv.setViewName("template2");
    return mv;
  }

  @PostMapping("/buyer/add")
  public ModelAndView add(Buyer buyer, HttpServletRequest request, Part photoFile) throws Exception{

    Member member = new Member();

    member.setAuthority(2);
    member.setId(request.getParameter("id"));
    member.setName(request.getParameter("name"));
    member.setNickname(request.getParameter("nickname"));
    member.setEmail(request.getParameter("email"));
    member.setBirthday(Date.valueOf(request.getParameter("birthday")));
    member.setPassword(request.getParameter("password"));
    member.setPhoneNumber(request.getParameter("phoneNumber"));

    //    member.setPhoto(request.getParameter("photo"));


    if (photoFile.getSize() > 0) { 
      String filename = UUID.randomUUID().toString();
      photoFile.write(sc.getRealPath("/upload/buyer") + "/" + filename);
      member.setPhoto(filename);
      buyer.setMember(member);

      Thumbnails.of(sc.getRealPath("/upload/buyer") + "/" + filename)
      .size(20, 20)
      .outputFormat("jpg")
      .crop(Positions.CENTER)
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_20x20";
        }
      });

      Thumbnails.of(sc.getRealPath("/upload/buyer") + "/" + filename)
      .size(100, 100)
      .outputFormat("jpg")
      .crop(Positions.CENTER)
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_100x100";
        }
      });
    }

    buyer.setMember(member);
    buyerDao.insert(buyer);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.addObject("refresh", "2;url=list");
    mv.addObject("pageTitle", "회원가입(구매자)");
    mv.addObject("contentUrl", "buyer/BuyerAdd.jsp");
    mv.setViewName("template2");
    return mv;
  }

  @GetMapping("/buyer/list")
  public ModelAndView list() throws Exception{

    Collection<Buyer> buyerList =  buyerDao.findAll();

    ModelAndView mv = new ModelAndView();
    mv.addObject("buyerList", buyerList);
    mv.addObject("pageTitle", "회원(구매자)목록");
    mv.addObject("contentUrl", "/buyer/BuyerList.jsp");
    mv.setViewName("template2");
    return mv;
  }

  @GetMapping("/buyer/detail")
  public ModelAndView detail(String id) throws Exception{
    //    String page = "";
    //    Member member = (Member) request.getSession(false).getAttribute("loginUser");

    Buyer buyer = buyerDao.findById(id);

    if (buyer == null) {
      throw new Exception("해당 번호의 회원이 없습니다.");
    }

    //    if (member.getAuthority() == 8) {
    //      page = "/buyer/BuyerUpdate.jsp";
    //    } else {
    //      page = "/buyer/BuyerDetail.jsp";
    //    }

    ModelAndView mv = new ModelAndView();
    mv.addObject("buyer", buyer);
    mv.addObject("pageTitle", "회원(구매자)상세보기");
    mv.addObject("contentUrl", "/buyer/BuyerUpdate.jsp");
    mv.setViewName("template2");
    return mv;
  }

  @PostMapping("/buyer/update")
  public ModelAndView update(Buyer buyer, Part photoFile) throws Exception {

    //Member member = (Member) session.getAttribute("loginUser");

    System.out.println(buyer.getMember().getId());
    Buyer oldBuyer = buyerDao.findById(buyer.getMember().getId());

    if (oldBuyer == null) {
      throw new Exception("해당 번호의 회원이 없습니다.");
    }

    buyer.getMember().setNickname(oldBuyer.getMember().getNickname());
    //  buyer.getMember().setPassword(request.getParameter("password"));
    buyer.getMember().setPhoneNumber(oldBuyer.getMember().getPhoneNumber());
    buyer.setZipcode(oldBuyer.getZipcode());
    buyer.setAddress(oldBuyer.getAddress());
    buyer.setDetailAddress(oldBuyer.getDetailAddress());

    if (photoFile.getSize() > 0) {
      String filename = UUID.randomUUID().toString();
      photoFile.write(sc.getRealPath("/upload/buyer") + "/" + filename);
      buyer.getMember().setPhoto(filename);   

      Thumbnails.of(sc.getRealPath("/upload/buyer") + "/" + filename)
      .size(20, 20)
      .outputFormat("jpg")
      .crop(Positions.CENTER)
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_20x20";
        }
      });

      Thumbnails.of(sc.getRealPath("/upload/buyer") + "/" + filename)
      .size(100, 100)
      .outputFormat("jpg")
      .crop(Positions.CENTER)
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_100x100";
        }
      });

      // } else if (member.getAuthority() == 8) {
      //buyer.getMember().setLevel(Integer.parseInt(request.getParameter("level")));

      // }
    }
    buyerDao.update(buyer);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "개인정보변경");
    mv.addObject("contentUrl", "main/Menu.jsp");
    mv.setViewName("/template2.jsp");
    return mv;
  }

  @GetMapping("/buyer/delete")
  public ModelAndView delete(String id) throws Exception {

    //    if (session.getAttribute("loginUser") == null) {
    //      out.printf("<script>alert('로그인 후 사용 가능합니다.'); location.href='../main/loginMenu'</script>");
    //      out.flush();
    //      return;
    //    }

    Buyer buyer = buyerDao.findById(id);

    if (buyer == null) {
      throw new Exception("해당 번호의 회원이 없습니다.");
    }  

    buyerDao.delete(buyer.getMember().getNumber());
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:list");
    return mv;
  }

}

