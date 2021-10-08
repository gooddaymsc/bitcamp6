package com.eomcs.pms.table;

import com.eomcs.pms.domain.Product;
import com.eomcs.pms.domain.Review;
import com.eomcs.server.DataProcessor;
import com.eomcs.server.Request;
import com.eomcs.server.Response;

public class ProductTable extends JsonDataTable<Product> implements DataProcessor {

  public ProductTable() {
    super("product.json", Product.class);
  }

  @Override
  public void execute(Request request, Response response) throws Exception {
    // TODO Auto-generated method stub
    switch (request.getCommand()) {
      case "product.insert" : productInsert(request, response); break;
      case "product.selectOne" : productSelectOne(request, response); break;
      case "product.selectOne2" : productSelectOne2(request, response); break;
      case "product.selectList" : productSelectList(request, response); break;
      case "product.update" : productUpdate(request, response); break;
      case "product.delete" : productDelete(request, response); break;
      case "product.review.insert" : reviewInsert(request, response); break;
      //  case "product.review.selectOne" : reviewSelectOne(request, response); break;
      case "product.review.selectList" : reviewselectList(request, response); break;
      case "product.review.update" : reviewUpdate(request, response); break;
      case "product.review.delete" : reviewDelete(request, response); break;

      default : 
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }

  private void productInsert(Request request, Response response) throws Exception {
    Product product = request.getObject(Product.class);
    list.add(product);
    response.setStatus(Response.SUCCESS);
  }

  private void productSelectList(Request request, Response response) throws Exception{
    response.setStatus(Response.SUCCESS);
    response.setValue(list);
  }

  private void productSelectOne(Request request, Response response) throws Exception {
    int productNumber = Integer.parseInt(request.getParameter("productNumber"));
    Product product = findByNumber(productNumber);
    if (product != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(product);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("해당 상품이 없습니다.");
    }
  }

  private void productSelectOne2(Request request, Response response) throws Exception {
    String name = request.getParameter("productName");
    Product product = findByProduct(name);
    if (product != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(product);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("해당 상품이 없습니다.");
    }
  }
  private void productUpdate(Request request, Response response) throws Exception {
    Product product = request.getObject(Product.class);

    int index = indexOf(product.getProductNumber());
    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 이름의 상품명이 없습니다.");
      return;
    } 
    list.set(index, product);
    response.setStatus(Response.SUCCESS);
  }

  private void productDelete(Request request, Response response) throws Exception {
    Product product = request.getObject(Product.class);
    int index = indexOf(product.getProductName());

    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 상품을 찾을 수 없습니다.");
      return;
    }
    list.remove(index);
    response.setStatus(Response.SUCCESS);
  }

  public Product findByProduct (String ProductName) {
    for (Product product : list) {
      if (product.getProductName().equals(ProductName)) {
        return product;
      }
    }
    return null;
  }

  public Product findByNumber (int productNo) {
    for (Product product : list) {
      if (product.getProductNumber() == productNo) {
        return product;
      }
    }
    return null;
  }

  public Review fidndByReviewNumber (int reviewNumber, Product product) {
    for(Review review : product.getReviewList()) {
      if(review.getProductNo() == reviewNumber) {
        return review;
      }
    }
    return null;
  }

  private void reviewInsert (Request request, Response response) throws Exception {
    Review review = request.getObject(Review.class);
    Product product = findByNumber(review.getProductNo());
    product.setTotalReviewNumber(product.getTotalReviewNumber()+1);
    product.getReviewList().add(review);
    response.setStatus(Response.SUCCESS);
  }

  //  private void reviewSelectOne(Request request, Response response) throws Exception {
  //    int reviewNumber = Integer.parseInt(request.getParameter("reviewNumber"));
  //    int productNumber = Integer.parseInt(request.getParameter("productNumber"));
  //    Product product = findByNumber(productNumber);
  //    Review review = fidndByReviewNumber(reviewNumber, product); 
  //
  //    if (review != null) {
  //      response.setStatus(Response.SUCCESS);
  //      response.setValue(review);
  //    } else {
  //      response.setStatus(Response.FAIL);
  //      response.setValue("해당 상품이 없습니다.");
  //    }
  //  }

  private void reviewselectList(Request request, Response response) throws Exception{
    int productNumber = Integer.parseInt(request.getParameter("productNumber"));
    Product product = findByNumber(productNumber);

    response.setStatus(Response.SUCCESS);
    response.setValue(product.getReviewList());

  }

  private void reviewUpdate(Request request, Response response) throws Exception{
    Review review = request.getObject(Review.class);
    Product product = findByNumber(review.getProductNo());
    int index = indexOf(product, review.getNo());
    product.getReviewList().set(index, review);

    if(index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 리뷰가 없습니다.");
      return;
    }
    response.setStatus(Response.SUCCESS);

  }

  private void reviewDelete(Request request, Response response) throws Exception {
    Review review = request.getObject(Review.class);
    Product product = findByNumber(review.getProductNo());
    int index = indexOf(product, review.getNo());
    product.getReviewList().remove(index);
    response.setStatus(Response.SUCCESS);
  }

  private int indexOf(String productName) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getProductName().equals(productName)) {
        return i;
      }
    }
    return -1;
  }

  private int indexOf(Product product, int reviewNumber) {
    for (int i = 0; i < product.getReviewList().size(); i++) {
      if (product.getReviewList().get(i).getNo() == reviewNumber) {
        return i;
      }
    }
    return -1;
  }

  private int indexOf(int productNumber) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getProductNumber() == productNumber) {
        return i;
      }
    }
    return -1;
  }
}
