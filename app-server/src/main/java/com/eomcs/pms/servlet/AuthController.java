package com.eomcs.pms.servlet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.eomcs.pms.dao.MemberDao;
import com.eomcs.pms.domain.Member;

@Controller
@RequestMapping("/main")
public class AuthController {

  @Autowired MemberDao memberDao;
  @Autowired ServletContext sc;

  @GetMapping("loginForm")
  public ModelAndView loginForm() throws Exception {
    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "로그인");
    mv.addObject("contentUrl", "main/LoginForm.jsp");
    mv.setViewName("template3");
    return mv;
  }

  @PostMapping("login")
  public ModelAndView login(String id, String password, HttpSession session) throws Exception {
    Member member = memberDao.findByIdAndPassword(id, password);

    ModelAndView mv = new ModelAndView();
    if (member != null) {
      session.setAttribute("loginUser", member);
      mv.setViewName("redirect:menu");

      //      mv.setViewName("redirect:./menu");
    } else {
      mv.addObject("refresh", "2;url=login");
      mv.addObject("pageTitle", "로그인오류!");
      mv.addObject("contentUrl", "main/LoginError.jsp");
      mv.setViewName("template1");
    }
    return mv;
  }

  @GetMapping("logout")
  public ModelAndView logout(HttpSession session) throws Exception {
    session.invalidate();
    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:loginForm");
    return mv;
  }
}
