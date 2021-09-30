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
      case "product.insert" : insert(request, response); break;
      case "product.selectList" : selectList(request, response); break;
      default : 
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }

  private void insert(Request request, Response response) throws Exception {
    Product product = request.getObject(Product.class);
    list.add(product);
    response.setStatus(Response.SUCCESS);
  }

  private void selectList(Request request, Response response) throws Exception{
    response.setStatus(Response.SUCCESS);
    response.setValue(list);
  }

}
