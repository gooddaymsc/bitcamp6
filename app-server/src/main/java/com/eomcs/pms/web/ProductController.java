package com.eomcs.pms.web;

import java.util.Collection;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.eomcs.pms.dao.ProductDao;
import com.eomcs.pms.dao.ReviewDao;
import com.eomcs.pms.dao.StockDao;
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.ProductType;
import com.eomcs.pms.domain.Review;
import com.eomcs.pms.servlet.ProductHandlerHelper;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

@Controller
public class ProductController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired ProductDao productDao;
  @Autowired StockDao stockDao;
  @Autowired ReviewDao reviewDao;
  @Autowired ServletContext sc;

  @GetMapping("/product/form")
  public ModelAndView form() {
    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "새상품");
    mv.addObject("contentUrl", "product/ProductForm.jsp");
    mv.setViewName("template2");
    return mv;
  }

  @PostMapping("/product/add")
  public ModelAndView add(Product product, ProductType productType, Part photoFile) throws Exception {
    if (photoFile.getSize() > 0) {
      String filename = UUID.randomUUID().toString();
      photoFile.write(sc.getRealPath("/upload/product") + "/" + filename);
      product.setPhoto(filename);
      product.setProductType(new ProductHandlerHelper(
          productDao).promptType(productType.getType(), productType.getSubType()));

      Thumbnails.of(sc.getRealPath("upload/product") + "/" + filename)
      .size(300, 300)
      .outputFormat("jpg")
      .crop(Positions.CENTER)
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_300x300";
        }
      });

      Thumbnails.of(sc.getRealPath("/upload/product") + "/" + filename)
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

    productDao.insert(product);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:list");
    return mv;

  }

  @GetMapping("/product/list")
  public ModelAndView list() throws Exception {

    Collection<Product> productList = productDao.findAll();

    ModelAndView mv = new ModelAndView();
    mv.addObject("productList", productList);
    mv.addObject("pageTitle", "상품목록");
    mv.addObject("contentUrl", "product/ProductList.jsp");
    mv.setViewName("template2");
    return mv;
  }

  @GetMapping("/product/detail")
  public ModelAndView detail(int no) throws Exception {
    Product product = productDao.findByNo(no);
    Collection<Review> reviewList = reviewDao.findAll(no);

    if (reviewList.equals(null)) {
      System.out.println("등록된 댓글이 없습니다.");
    }

    if (product == null) {
      throw new Exception("해당 번호의 상품이 없습니다.");

    }

    ModelAndView mv = new ModelAndView();
    mv.addObject("product", product);
    mv.addObject("pageTitle", "상품정보수정");
    mv.addObject("contentUrl", "product/ProductDetail.jsp");
    mv.setViewName("template2");
    return mv;
  }

  @PostMapping("/product/update")
  public ModelAndView update(Product product, ProductType productType, Part photoFile) throws Exception {

    Product oldProduct = productDao.findByNo(product.getProductNumber());

    if (oldProduct == null) {
      throw new Exception("해당 이름의 상품이 없습니다.");
    }

    product.setPhoto(oldProduct.getPhoto());
    product.setProductName(oldProduct.getProductName());
    product.setProductType(oldProduct.getProductType());

    if (photoFile.getSize() > 0) {
      String filename = UUID.randomUUID().toString();
      photoFile.write(sc.getRealPath("/upload/product") + "/" + filename);
      product.setPhoto(filename);
      //      product.setProductType(new ProductHandlerHelper(
      //          productDao).promptType(productType.getType(), productType.getSubType()));

      Thumbnails.of(sc.getRealPath("upload/product") + "/" + filename)
      .size(300, 300)
      .outputFormat("jpg")
      .crop(Positions.CENTER)
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_300x300";
        }
      });

      Thumbnails.of(sc.getRealPath("upload/product") + "/" + filename)
      .size(1000, 1000)
      .outputFormat("jpg")
      .crop(Positions.CENTER)
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_1000x1000";
        }
      });

      product.setPhoto(filename);
    }

    productDao.update(product);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:list");
    return mv;
  }

  @GetMapping("/product/delete")
  public ModelAndView delete(int no) throws Exception {
    Product product = productDao.findByNo(no);

    if (product == null) {
      throw new Exception("해당 번호의 상품이 없습니다.");
    }  

    stockDao.delete2(product.getProductNumber());
    reviewDao.delete2(product.getProductNumber());
    productDao.delete(product);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:list");
    return mv;
  }

  @GetMapping("/product/show")
  public ModelAndView show(int no) throws Exception {
    Product product = productDao.findByNo(no);
    Collection<Review> reviewList = reviewDao.findAll(no);

    if (reviewList.equals(null)) {
      System.out.println("등록된 댓글이 없습니다.");
    }

    if (product == null) {
      throw new Exception("해당 번호의 상품이 없습니다.");

    }

    ModelAndView mv = new ModelAndView();
    mv.addObject("product", product);
    mv.addObject("reviewList", reviewList);
    mv.addObject("pageTitle", "상품상세보기");
    mv.addObject("contentUrl", "product/ProductShow.jsp");
    mv.setViewName("template2"); 
    return mv;
  }

  @GetMapping("/product/listType")
  public ModelAndView listType(String type) throws Exception {
    System.out.println("typeerror");
    Collection<Product> productList = productDao.findTypeAll(type);

    ModelAndView mv = new ModelAndView();
    mv.addObject("type", type);
    mv.addObject("productList", productList);
    mv.addObject("pageTitle", "주류목록");
    mv.addObject("contentUrl", "product/ProductListType.jsp");
    mv.setViewName("template2"); 
    return mv;
  }

  @GetMapping("/product/listSubType")
  public ModelAndView listSubType(int no) throws Exception {
    Collection<Product> productList = productDao.findSubTypeAll(no);

    ModelAndView mv = new ModelAndView();
    mv.addObject("product", productDao.findSubType(no));
    mv.addObject("productList", productList);
    mv.addObject("pageTitle", "주류목록");
    mv.addObject("contentUrl", "product/ProductListSubType.jsp");
    mv.setViewName("template2"); 
    return mv;
  }

  @GetMapping("/product/place")
  public ModelAndView place(String place) throws Exception {
    String input = place;
    System.out.println(input);

    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "장소찾기");
    mv.addObject("contentUrl", "product/ProductSearch.jsp");
    mv.setViewName("template2"); 
    return mv;
  }

  @PostMapping("/product/search")
  public ModelAndView search(String search) throws Exception {
    String input = "%"+search+"%";
    Collection<Product> productList = productDao.search(input);

    ModelAndView mv = new ModelAndView();
    mv.addObject("productList", productList);
    mv.addObject("pageTitle", "상품검색");
    mv.addObject("contentUrl", "product/ProductList.jsp");
    mv.setViewName("template2"); 
    return mv;
  }

  @GetMapping("/product/ranking")
  public ModelAndView ranking() throws Exception {
    //HttpSession session = request.getSession(false);

    Collection<Product> productList = productDao.ranking();

    //    Product product =  (Product) session.getAttribute("productNumber");
    //    Collection<Review> reviewList = reviewDao.findRecent(product.getProductNumber());
    ModelAndView mv = new ModelAndView();
    mv.addObject("productList", productList);
    //mv.addObject("reviewList", reviewList);
    mv.addObject("pageTitle", "오늘의술");
    mv.addObject("contentUrl", "product/Ranking.jsp");
    mv.setViewName("template2");
    return mv;
  }
}
