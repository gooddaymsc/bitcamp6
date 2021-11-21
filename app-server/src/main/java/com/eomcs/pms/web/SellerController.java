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
      System.out.println("gooood");
      String filename = UUID.randomUUID().toString();
      photoFile.write(sc.getRealPath("/upload/seller") + "/" + filename);
      member.setPhoto(filename);
      seller.setMember(member);

      Thumbnails.of(sc.getRealPath("/upload/seller") + "/" + filename)
      .size(1000, 1000)
      .outputFormat("jpg")
      .crop(Positions.CENTER)
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_1000x1000";
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
    Member member = (Member)session.getAttribute("loginUser");
    Seller seller = sellerDao.findById(id);

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
  public ModelAndView update(Seller seller, Member member, Part photoFile, HttpServletRequest request) throws Exception {

    Member loginUser = (Member) request.getSession(false).getAttribute("loginUser");

    Seller oldSeller = sellerDao.findById(seller.getMember().getId());

    if(oldSeller == null) {
      throw new Exception("해당 아이디의 회원이 없습니다.");
    }

    //    if (member.getId().equals(oldSeller.getMember().getId())) {
    if (loginUser.getId().equals(seller.getMember().getId())) {

      seller.getMember().setNickname(oldSeller.getMember().getNickname());
      seller.getMember().setEmail(oldSeller.getMember().getEmail());
      seller.getMember().setPassword(oldSeller.getMember().getPassword());
      seller.getMember().setPhoneNumber(oldSeller.getMember().getPhoneNumber());
      seller.getMember().setPhoto(oldSeller.getMember().getPhoto());

      oldSeller.setMember(member);

      if (photoFile.getSize() > 0) {
        String filename = UUID.randomUUID().toString();
        photoFile.write(sc.getRealPath("/upload/seller") + "/" + filename);

        seller.getMember().setPhoto(filename);
        oldSeller.setMember(member);

        Thumbnails.of(sc.getRealPath("/upload/seller") + "/" + filename)
        .size(20, 20)
        .outputFormat("jpg")
        .crop(Positions.CENTER)
        .toFiles(new Rename() {
          @Override
          public String apply(String name, ThumbnailParameter param) {
            return name + "_20x20";
          }
        });

        Thumbnails.of(sc.getRealPath("/upload/product") + "/" + filename)
        .size(100, 100)
        .outputFormat("jpg")
        .crop(Positions.CENTER)
        .toFiles(new Rename() {
          @Override
          public String apply(String name, ThumbnailParameter param) {
            return name + "_100x100";
          }
        });
        //        seller.getMember().setPhoto(filename);
      }
      //        }
      sellerDao.update(seller.getMember());
      sellerDao.updateSeller(seller);
      sqlSessionFactory.openSession().commit();

      ModelAndView mv = new ModelAndView();
      //      mv.addObject("contentUrl", "main/MyPage.jsp");
      mv.setViewName("template2");
      mv.setViewName("redirect:detail");
      return mv;
    } else if (loginUser.getAuthority() == 8) {
      oldSeller.setMember(member);
      //seller.getMember().setLevel(Integer.parseInt(oldSeller.getMember().getLevel());
      sellerDao.updateLevel(seller.getMember());
      sellerDao.updateSeller(seller);
      sqlSessionFactory.openSession().commit();
      ModelAndView mv = new ModelAndView();

      mv.addObject("pageTitle", "등급 변경");
      mv.setViewName("template2");
      mv.setViewName("redirect:list");
      return mv;
    }
    return null;
  }

  @GetMapping("/seller/delete")
  public ModelAndView delete(String id, HttpServletRequest request) throws Exception {
    Seller seller = sellerDao.findById(id);
    if (seller == null) {
      throw new Exception("해당 아이디의 회원이 없습니다.");
    }  

    sellerDao.delete(seller.getMember().getNumber());
    sellerDao.deleteSeller(seller.getMember().getNumber());
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    HttpSession loginUser = request.getSession();
    loginUser.setAttribute("loginUser", null);
    loginUser.invalidate();
    request.getSession(true);
    mv.addObject("contentUrl", "main/LoginForm.jsp");
    mv.setViewName("template3");
    return mv;
  }

}