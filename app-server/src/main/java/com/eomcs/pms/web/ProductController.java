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
import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.ProductType;
import com.eomcs.pms.domain.Review;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

@Controller
public class ProductController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired ProductDao productDao;
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


}
