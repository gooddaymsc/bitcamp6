package com.eomcs.pms.web;

import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.eomcs.pms.dao.ProductDao;
import com.eomcs.pms.dao.SellerDao;
import com.eomcs.pms.dao.StockDao;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.Seller;
import com.eomcs.pms.domain.Stock;
import com.eomcs.pms.domain.StockList;


@Controller
@RequestMapping("/stock")
public class StockController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired ProductDao productDao;
  @Autowired SellerDao sellerDao;
  @Autowired StockDao stockDao;

  @GetMapping("form")
  public ModelAndView form(int no, HttpSession session) throws Exception {
    ModelAndView mv = new ModelAndView();
    mv.addObject("productNo", no);
    mv.addObject("pageTitle", "재고담기");
    mv.addObject("contentUrl", "stock/StockForm.jsp");
    mv.setViewName("template2");
    return mv;
  }

  @PostMapping("add")
  public ModelAndView add(Stock stock, HttpSession session) throws Exception {
    Member member = (Member) session.getAttribute("loginUser");
    Product product = productDao.findByNo(stock.getProduct().getProductNumber());
    stock.setProduct(product);
    Seller seller = sellerDao.findById(member.getId());
    stock.setSeller(seller);

    stockDao.insert(stock);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:list?id="+seller.getMember().getId());
    return mv;
  }

  @GetMapping("list")
  public ModelAndView list(HttpSession session) throws Exception {
    StockList stockList = new StockList();

    Member member = (Member) session.getAttribute("loginUser");
    Collection<Stock> list = stockDao.findAll(member.getId());
    stockList.setId(member.getId());
    stockList.setSellerStock((List<Stock>) list);

    ModelAndView mv = new ModelAndView();
    mv.addObject("id", member.getId());
    mv.addObject("stockList", stockList.getSellerStock());
    mv.addObject("pageTitle", "재고목록");
    mv.addObject("contentUrl", "stock/StockList.jsp");
    mv.setViewName("template1");
    return mv;
  }

  @GetMapping("sellerList")
  public ModelAndView sellerlist(int no) throws Exception {

    Collection<Stock> stockSellerList = stockDao.findByProductNo(no);

    ModelAndView mv = new ModelAndView();
    mv.addObject("stockSellerList", stockSellerList);
    mv.addObject("productNo", no);
    mv.addObject("pageTitle", "재고판매자목록");
    mv.addObject("contentUrl", "stock/StockSellerList.jsp");
    mv.setViewName("template1");
    return mv;
  }

  @GetMapping("detail")
  public ModelAndView detail(int no) throws Exception {
    Stock stock = stockDao.findByNo(no);

    if (stock == null) {
      throw new Exception("해당 번호의 상품이 없습니다.");
    }

    ModelAndView mv = new ModelAndView();
    mv.addObject("stock", stock);
    mv.addObject("pageTitle", "재고상세보기");
    mv.addObject("contentUrl", "stock/StockDetail.jsp");
    mv.setViewName("template1");
    return mv;
  }

  @PostMapping("update")
  public ModelAndView update(Stock stock) throws Exception {
    stockDao.update(stock);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:list?id="+stock.getSeller().getMember().getId());
    return mv;
  }

  @GetMapping("checkStock")
  @ResponseBody
  public String checkStock(int no , HttpSession session) throws Exception {
    Member member = (Member) session.getAttribute("loginUser");
    Stock stock = stockDao.findByNoId(no, member.getId());

    if (stock == null) {
      return "false";
    } else {
      return "true";
    }
  }

  @GetMapping("delete")
  public ModelAndView delete(int no) throws Exception {

    Stock stock = stockDao.findByNo(no);
    stockDao.delete(stock);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:list?id="+stock.getSeller().getMember().getId());
    return mv;
  }
}







