package com.eomcs.pms.servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.BuyerDao;
import com.eomcs.pms.domain.Buyer;
import com.eomcs.pms.domain.Member;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

//@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
//@WebServlet("/buyer/add")
public class BuyerAddController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  SqlSession sqlSession;
  BuyerDao buyerDao;

  @Override
  public void init() {
    ServletContext 웹애플리케이션공용저장소 = getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    buyerDao = (BuyerDao) 웹애플리케이션공용저장소.getAttribute("buyerDao");
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    System.out.println("ccc");

    try {
      Buyer buyer = new Buyer();
      Member member = new Member();

      member.setAuthority(2);
      member.setId(request.getParameter("id"));
      member.setName(request.getParameter("name"));
      member.setNickname(request.getParameter("nickname"));
      member.setEmail(request.getParameter("email"));
      member.setBirthday(Date.valueOf(request.getParameter("birthday")));
      member.setPassword(request.getParameter("password"));
      member.setPhoneNumber(request.getParameter("phoneNumber"));
      //      member.setPhoto(request.getParameter("photo"));

      Part photoPart = request.getPart("photo");
      if (photoPart.getSize() > 0) {
        String filename = UUID.randomUUID().toString();
        photoPart.write(getServletContext().getRealPath("/upload/buyer") + "/" + filename);
        member.setPhoto(filename);

        Thumbnails.of(getServletContext().getRealPath("/upload/buyer") + "/" + filename)
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

      //    if (findDeletedByName(buyer.getName()) != -1) {
      //      if (deletedMemberList.get(memberPrompt.findDeletedByName(buyer.getName())).getPhoneNumber().equals(buyer.getPhoneNumber()) && 
      //          deletedMemberList.get(memberPrompt.findDeletedByName(buyer.getName())).getName().equals(buyer.getName())) {
      //        System.out.println("탈퇴한 회원입니다. 7일후 재가입해주세요.");
      //        return;
      //      }
      //    }

      // 회원가입할때 쿠폰지급하기 (2개)
      //      Coupon coupon = new Coupon();
      //      coupon.setMinimum(10000);
      //      coupon.setPercent(10);
      //      buyer.getCoupon().add(coupon);
      //      coupon = new Coupon();
      //      coupon.setMinimum(15000);
      //      coupon.setPrice(2000);
      //      buyer.getCoupon().add(coupon);

      buyer.setZipcode(request.getParameter("zipcode"));
      buyer.setAddress(request.getParameter("address"));
      buyer.setDetailAddress(request.getParameter("detailAddress"));
      //    System.out.printf("이름 : %s\n", memberPrompt.findById(id).getName());
      //    member.setRegisteredDate(new Date(System.currentTimeMillis()));
      //    buyer.setNumber(totalNumberList.get(App.MEMBER_NUMBER_INDEX));
      //    totalNumberList.set(App.MEMBER_NUMBER_INDEX, buyer.getNumber()+1);
      //    memberList.add(buyer);
      buyer.setMember(member);
      buyerDao.insert(buyer);
      sqlSession.commit();
      request.setAttribute("pageTitle", "회원가입(구매자)");
      request.setAttribute("contentUrl", "/buyer/BuyerAdd.jsp");
      request.getRequestDispatcher("/template2.jsp").forward(request, response);
      response.setHeader("Refresh", "1;url=../main/loginMenu");

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}

