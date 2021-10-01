package com.eomcs.pms.handler;

import java.util.HashMap;
import com.eomcs.request.RequestAgent;
import com.eomcs.util.Prompt;

public class BoardDeleteHandler implements Command {

  RequestAgent requestAgent;

  public BoardDeleteHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {

    System.out.println("[게시글 삭제]");
    int no = (int) request.getAttribute("no");
    //    int no = Prompt.inputInt("게시글 번호 >");

    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("board.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("해당번호의 게시글이 없습니다.");
      return;
    }
    //    Board board = requestAgent.getObject(Board.class);


    //    if (!((board.getWriter().equals(ClientApp.getLoginUser().getId())) ||
    //        (ClientApp.getLoginUser().getAuthority() == Menu.ACCESS_ADMIN))) {
    //      System.out.println("작성자가 아니므로 삭제할 수 없습니다.\n");
    //      return;
    //    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("y")) {
      requestAgent.request("board.delete", params);
      System.out.println("게시글을 삭제하였습니다.\n");
      return;
    }

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("게시글 삭제 실패!");
      System.out.println(requestAgent.getObject(String.class));
      return;
    }

    System.out.println("게시글 삭제를 취소하였습니다.\n");
    return;
  }

}






