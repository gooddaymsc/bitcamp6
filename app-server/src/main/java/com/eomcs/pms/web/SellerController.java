package com.eomcs.pms.web;

import java.sql.Date;
import java.util.Collection;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.eomcs.pms.dao.MemberDao;
import com.eomcs.pms.dao.SellerDao;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Seller;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

@Controller
public class SellerController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired SellerDao sellerDao;
  @Autowired MemberDao memberDao;
  @Autowired ServletContext sc;

  @GetMapping("/seller/form")
  public ModelAndView form() {
    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "회원가입(판매자)"); 
    mv.addObject("contentUrl", "seller/SellerForm.jsp");
    mv.setViewName("template2");
    return mv;
  }
  @GetMapping("/seller/passwordForm")
  public ModelAndView passwordForm(HttpSession session) throws Exception {
    Member member = (Member) session.getAttribute("loginUser");
    Seller seller = sellerDao.findById(member.getId());

    ModelAndView mv = new ModelAndView();
    mv.addObject("seller", seller);
    mv.addObject("pageTitle", "비밀번호 변경");
    mv.addObject("contentUrl", "seller/SellerPassword.jsp");
    mv.setViewName("template2");
    return mv;
  }

  @PostMapping("/seller/passwordResult")
  public ModelAndView passwordResult(Member member, HttpSession session) throws Exception {
    memberDao.update(member);
    sqlSessionFactory.openSession().commit();

    Member oldMember = (Member) session.getAttribute("loginUser");
    Seller seller = sellerDao.findById(oldMember.getId());

    ModelAndView mv = new ModelAndView();
    mv.addObject("seller", seller);
    mv.addObject("pageTitle", "개인정보변경");
    mv.addObject("contentUrl", "seller/SellerDetail.jsp");
    mv.setViewName("template2");
    return mv;
  }
  @PostMapping("/seller/add")
  public ModelAndView add(Seller seller, HttpServletRequest request, Part photoFile) throws Exception {

    Member member = new Member();
    member.setAuthority(4);
    member.setId(request.getParameter("id"));
    member.setName(request.getParameter("name"));
    member.setNickname(request.getParameter("nickname"));
    member.setEmail(request.getParameter("email"));
    member.setBirthday(Date.valueOf(request.getParameter("birthday")));
    member.setPassword(request.getParameter("password"));
    member.setPhoneNumber(request.getParameter("phoneNumber"));

    if (photoFile.getSize() > 0) {
      String filename = UUID.randomUUID().toString();
      photoFile.write(sc.getRealPath("/upload/member") + "/" + filename);
      member.setPhoto(filename);
      seller.setMember(member);

      Thumbnails.of(sc.getRealPath("/upload/member") + "/" + filename)
      .size(100, 100)
      .outputFormat("jpg")
      .crop(Positions.CENTER)
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_50x50";
        }
      });

      Thumbnails.of(sc.getRealPath("/upload/member") + "/" + filename)
      .size(1000, 1000)
      .outputFormat("jpg")
      .crop(Positions.CENTER)
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_100x100";
        }
      });
    }

    seller.setMember(member);
    sellerDao.insert(seller.getMember());
    sellerDao.insertSeller(seller);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.addObject("refresh", "2;url=list");
    mv.addObject("seller", seller);
    mv.addObject("pageTitle", "회원가입(판매자)");
    mv.addObject("contentUrl", "seller/SellerAdd.jsp");
    mv.setViewName("template2");
    return mv;
  }


  @GetMapping("/seller/list")
  public ModelAndView list() throws Exception {
    Collection<Seller> sellerList = sellerDao.findAll();

    ModelAndView mv = new ModelAndView();
    mv.addObject("sellerList", sellerList);
    mv.addObject("pageTitle", "회원(판매자)목록");
    mv.addObject("contentUrl", "seller/SellerList.jsp");
    mv.setViewName("template2");
    return mv;
  }

  @GetMapping("/seller/detail")
  public ModelAndView detail(String id, HttpSession session) throws Exception {
    String page = "";

    Member member = (Member) session.getAttribute("loginUser");

    Seller seller = sellerDao.findById(id);

    System.out.println(seller);

    if (seller == null) {
      throw new Exception("해당 번호의 회원이 없습니다.");
    }

    if (member.getAuthority() == 8) {
      page = "seller/SellerUpdate.jsp";
    } else {
      page = "seller/SellerDetail.jsp";
    }

    ModelAndView mv = new ModelAndView();
    mv.addObject("seller", seller);
    mv.addObject("pageTitle", "회원(판매자)상세보기");
    mv.addObject("contentUrl", page);
    mv.setViewName("template2");
    return mv;
  }

  @PostMapping("/seller/update")
  public ModelAndView update(Seller seller, Part photoFile, HttpServletRequest request) throws Exception {

    HttpSession session = request.getSession(false);
    Member loginUser = (Member) session.getAttribute("loginUser");

    Seller oldSeller = sellerDao.findById(seller.getMember().getId());

    if(oldSeller == null) {
      throw new Exception("해당 아이디의 회원이 없습니다.");
    }

    if (loginUser.getId().equals(seller.getMember().getId())) {

      if (photoFile.getSize() > 0) {
        String filename = UUID.randomUUID().toString();
        photoFile.write(sc.getRealPath("/upload/member") + "/" + filename);

        seller.getMember().setPhoto(filename);

        Thumbnails.of(sc.getRealPath("/upload/member") + "/" + filename)
        .size(100, 100)
        .outputFormat("jpg")
        .crop(Positions.CENTER)
        .toFiles(new Rename() {
          @Override
          public String apply(String name, ThumbnailParameter param) {
            return name + "_50x50";
          }
        });

        Thumbnails.of(sc.getRealPath("/upload/member") + "/" + filename)
        .size(1000, 1000)
        .outputFormat("jpg")
        .crop(Positions.CENTER)
        .toFiles(new Rename() {
          @Override
          public String apply(String name, ThumbnailParameter param) {
            return name + "_100x100";
          }
        });

        seller.getMember().setPhoto(filename);
      } else {
        seller.getMember().setPhoto(oldSeller.getMember().getPhoto());
      }
      sellerDao.update(seller.getMember());
      sellerDao.updateSeller(seller);
      sqlSessionFactory.openSession().commit();

      ModelAndView mv = new ModelAndView();
      mv.setViewName("redirect:../main/myPage");
      return mv;


    } else if (loginUser.getAuthority() == 8) {
      sellerDao.update(seller.getMember());
      sellerDao.updateSeller(seller);
      sqlSessionFactory.openSession().commit();
      ModelAndView mv = new ModelAndView();

      mv.addObject("pageTitle", "등급변경");
      mv.setViewName("template2");
      mv.setViewName("redirect:list");
      return mv;
    }
    return null;
  }

  @GetMapping("/seller/delete")
  public ModelAndView delete(String id, HttpSession session, HttpServletRequest request) throws Exception {
    Member member = (Member) session.getAttribute("loginUser");

    Seller seller = sellerDao.findById(id);
    if (seller == null) {
      throw new Exception("해당 아이디의 회원이 없습니다.");
    }  

    sellerDao.delete(seller.getMember().getNumber());
    sellerDao.deleteSeller(seller.getMember().getNumber());
    sqlSessionFactory.openSession().commit();
    ModelAndView mv = new ModelAndView();
    if (member.getAuthority()!=8) {
      HttpSession loginUser = request.getSession();
      loginUser.setAttribute("loginUser", null);
      loginUser.invalidate();
      request.getSession(true);
      mv.addObject("contentUrl", "main/LoginForm.jsp");
      mv.setViewName("template3");
      return mv;
    } else {
      mv.addObject("contentUrl", "main/MyPage.jsp");
      mv.setViewName("template2");
      return mv;
    }
  }
}