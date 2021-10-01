package com.eomcs.pms.table;

import com.eomcs.pms.domain.Product;
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
      case "product.insert" : insertProduct(request, response); break;
      case "product.selectOne" : selectOne(request, response); break;
      case "product.selectList" : selectList(request, response); break;
      case "product.update" : update(request, response); break;
      case "product.delete" : deleteProduct(request, response); break;
      default : 
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }

  private void insertProduct(Request request, Response response) throws Exception {
    Product product = request.getObject(Product.class);
    list.add(product);
    response.setStatus(Response.SUCCESS);
  }

  private void selectList(Request request, Response response) throws Exception{
    response.setStatus(Response.SUCCESS);
    response.setValue(list);
  }

  private void selectOne(Request request, Response response) throws Exception {
    String productName = request.getParameter("productName");
    Product product = findByProduct(productName);
    if (product != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(product);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("해당 상품이 없습니다.");
    }
  }
  private void update(Request request, Response response) throws Exception {
    Product product = request.getObject(Product.class);

    int index = indexOf(product.getProductName());

    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 이름의 상품명이 없습니다.");
      return;
    } 
    list.set(index, product);
    response.setStatus(Response.SUCCESS);
  }

  private void deleteProduct(Request request, Response response) throws Exception {
    Product product = request.getObject(Product.class);
    Product name = findByProduct(product.getProductName());

    if(name.equals(null)) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 상품을 찾을 수 업습니다.");
      return;
    }
    list.remove(name);
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
  private int indexOf(String productName) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getProductName().equals(productName)) {
        return i;
      }
    }
    return -1;
  }
}
