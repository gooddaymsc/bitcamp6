package com.eomcs.pms.web;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.eomcs.pms.dao.MemberDao;
import com.eomcs.pms.dao.ProductDao;

@Controller
public class MainController {

  @Autowired MemberDao memberDao;
  @Autowired ProductDao productDao;;
  //  @Autowired ServletContext sc;

  @GetMapping("/main/menu")
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

}

