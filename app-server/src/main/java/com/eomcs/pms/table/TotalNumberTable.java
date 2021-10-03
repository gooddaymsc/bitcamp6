package com.eomcs.pms.table;

import com.eomcs.server.DataProcessor;
import com.eomcs.server.Request;
import com.eomcs.server.Response;

public class TotalNumberTable extends JsonDataTable<Integer> implements DataProcessor {
  public static final int MEMBER_NUMBER_INDEX = 0;
  public static final int BOARD_NUMBER_INDEX = 1;
  public static final int PROUDCT_NUMBER_INDEX = 2;

  public TotalNumberTable() {
    super("totalNumber.json", Integer.class);
  }

  @Override
  public void execute(Request request, Response response) throws Exception {
    // TODO Auto-generated method stub
    switch (request.getCommand()) {
      //로딩오류가 나면 새로 생성하기.
      case "addNumber.member" : addNumberMember(request, response); break;
      case "addNumber.board" : addNumberBoard(request, response); break;
      case "addNumber.product" : addNumberProduct(request, response); break;
      default :
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }
  private void addNumberMember(Request request, Response response) throws Exception {
    checkList();
    int no = list.get(MEMBER_NUMBER_INDEX);
    list.set(MEMBER_NUMBER_INDEX, no+1);
    response.setStatus(Response.SUCCESS);
    response.setValue(no);
  } 

  private void addNumberBoard(Request request, Response response) throws Exception {
    checkList();
    int no = list.get(BOARD_NUMBER_INDEX);
    list.set(BOARD_NUMBER_INDEX, no+1);
    response.setStatus(Response.SUCCESS);
    response.setValue(no);
  }

  private void addNumberProduct(Request request, Response response) throws Exception {
    checkList();
    int no = list.get(PROUDCT_NUMBER_INDEX);
    list.set(PROUDCT_NUMBER_INDEX, no+1);
    response.setStatus(Response.SUCCESS);
    response.setValue(no);
  }

  private void checkList() {
    if (list.size()==0) {
      list.add(MEMBER_NUMBER_INDEX,1);
      list.add(BOARD_NUMBER_INDEX,1);
      list.add(PROUDCT_NUMBER_INDEX,1);
    }
  }
}
