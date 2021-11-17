package com.eomcs.pms.web;

import java.util.Collection;
import javax.servlet.ServletContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.eomcs.pms.domain.Buyer;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.service.BuyerService;

@Controller
@RequestMapping("/buyer/")
public class BuyerController {

  ServletContext sc;
  BuyerService buyerService;

  public BuyerController(BuyerService buyerService, ServletContext sc) {
    this.buyerService = buyerService;
    this.sc=sc;
  }

  @GetMapping("form")
  public void form() throws Exception {
  }

  @GetMapping("detail")
  public void detail(int no, Model model) throws Exception {

    Member member = (Member) request.getSession(false).getAttribute("loginUser");
    String id = request.getParameter("id");
    Buyer buyer = buyerDao.findById(id);

    request.setAttribute("buyer", buyer);
  }

  @PostMapping("buyerList")  
  public void list(String keyword, Model model) throws Exception{
    Collection<Buyer> buyerList = buyerService.buyerList(keyword);
    model.addAttribute("buyerList", buyerList);
  }


}
