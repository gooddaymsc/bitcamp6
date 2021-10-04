package com.eomcs.pms.table;

import com.eomcs.pms.domain.Review;
import com.eomcs.server.DataProcessor;
import com.eomcs.server.Request;
import com.eomcs.server.Response;

public class ReviewTable extends JsonDataTable<Review> implements DataProcessor{

  ProductTable productTable;
  public ReviewTable(ProductTable productTable) {
    this.productTable = productTable;
  }

  @Override
  public void execute(Request request, Response response) throws Exception {
    switch (request.getCommand()) {
      case "review.insert" : insertReview (request, response); break;
      case "review.selectList" : selectList(request, response); break;
      //  case "review.find" : findReview(request, response); break;
      case "review.update" : updateReview(request, response); break;
      case "product.delete" : deleteReview(request, response); break;
      default : 
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }

  private void insertReview(Request request, Response response) throws Exception {
    Review review = request.getObject(Review.class);
    list.add(review);
    response.setStatus(Response.SUCCESS);
  }

  private void selectList(Request request, Response response) throws Exception{
    response.setStatus(Response.SUCCESS);
    response.setValue(list);
  }

  private void updateReview(Request request, Response response) throws Exception{
    Review review = request.getObject(Review.class);
    int index = indexOf(review.getId());

    list.set(index, review);
    response.setStatus(Response.SUCCESS);
    response.setValue(list);
  }

  private void deleteReview(Request request, Response response) throws Exception {
    Review review = request.getObject(Review.class);
    int index = indexOf(review.getId());

    list.remove(index);
    response.setStatus(Response.SUCCESS);
  }

  private int indexOf(String writer) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getId().equals(writer)) {
        return i;
      }
    }
    return -1;
  }


}
