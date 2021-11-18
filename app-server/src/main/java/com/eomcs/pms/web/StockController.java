package com.eomcs.pms.web;

import java.util.Collection;
import java.util.List;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
public class StockController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired ProductDao productDao;
  @Autowired SellerDao sellerDao;
  @Autowired StockDao stockDao;

  @GetMapping("stock/form")
  public ModelAndView form(HttpServletRequest request) throws Exception {
    ModelAndView mv = new ModelAndView();
    int productNo = Integer.parseInt(request.getParameter("productNumber"));
    Member member = (Member) request.getSession(false).getAttribute("loginUser");

    //    Stock stock = stockDao.findByNoId(productNo, member.getId());

    mv.addObject("productNo", productNo);
    mv.addObject("pageTitle", "재고담기");
    mv.addObject("contentUrl", "stock/StockForm.jsp");
    mv.setViewName("template2");
    return mv;
  }

  @PostMapping("/stock/add")
  public ModelAndView add(HttpServletRequest request, HttpSession session) throws Exception {
    int productNo = Integer.parseInt(request.getParameter("productNumber"));

    Member member = (Member) session.getAttribute("loginUser");
    Stock stock = new Stock();
    Product product = productDao.findByNo(productNo);
    stock.setProduct(product);
    stock.setPrice(Integer.parseInt(request.getParameter("price")));
    stock.setStocks(Integer.parseInt(request.getParameter("stocks")));
    Seller seller = sellerDao.findById(member.getId());
    stock.setSeller(seller);
    stockDao.insert(stock);

    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:list?id="+seller.getMember().getId());
    return mv;
  }

  @GetMapping("/stock/list")
  public ModelAndView list(HttpSession session) throws Exception {
    StockList stockList = new StockList();

    Member member = (Member) session.getAttribute("loginUser");
    System.out.println(member.getId());
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

  @GetMapping("/stock/sellerList")
  public ModelAndView sellerlist(ServletRequest request) throws Exception {
    int productNo = Integer.parseInt(request.getParameter("no"));

    Collection<Stock> stockSellerList = stockDao.findByProductNo(productNo);

    ModelAndView mv = new ModelAndView();
    mv.addObject("stockSellerList", stockSellerList);
    mv.addObject("productNo", productNo);
    mv.addObject("pageTitle", "재고판매자목록");
    mv.addObject("contentUrl", "stock/StockSellerList.jsp");
    mv.setViewName("template1");
    return mv;
  }

  @GetMapping("/stock/detail")
  public ModelAndView detail(ServletRequest request) throws Exception {
    int no = Integer.parseInt(request.getParameter("no"));
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

  @PostMapping("/stock/update")
  public ModelAndView update(HttpServletRequest request) throws Exception {
    int no = Integer.parseInt(request.getParameter("no"));
    Stock stock = stockDao.findByNo(no);

    stock.setStocks(Integer.parseInt(request.getParameter("stocks")));
    stock.setPrice(Integer.parseInt(request.getParameter("price")));

    stockDao.update(stock);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:list?id="+stock.getSeller().getMember().getId());
    return mv;
  }

  @GetMapping("/stock/delete")
  public ModelAndView delete(HttpServletRequest request) throws Exception {

    int no = Integer.parseInt(request.getParameter("no"));
    Stock stock = stockDao.findByNo(no);

    stockDao.delete(stock);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:list?id="+stock.getSeller().getMember().getId());
    return mv;
  }
}







