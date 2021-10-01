package com.eomcs.pms.handler;

import java.util.HashMap;
import com.eomcs.pms.ClientApp;
import com.eomcs.pms.domain.Board;
import com.eomcs.request.RequestAgent;
import com.eomcs.util.Prompt;

public class BoardUpdateHandler implements Command {

  RequestAgent requestAgent;
  public BoardUpdateHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {

    System.out.println("[게시글 변경]");
    int no = (int) request.getAttribute("no");

    //    String no = Integer.toString(Prompt.inputInt("게시글 번호> ")); 
    HashMap<String, String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("board.selectOne", params);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("해당 번호의 게시글이 없습니다.\n");
      return;
    }
    Board board = requestAgent.getObject(Board.class);

    if (!board.getWriter().equals(ClientApp.getLoginUser().getId())) {
      System.out.println("작성자가 아니므로 변경할 수 없습니다.\n");
      return;
    }
    String title = Prompt.inputString(String.format("제목(변경 전 : %s) : ", board.getTitle()));
    String content = Prompt.inputString(String.format("내용(변경 전 : %s) : ", board.getContent()));
    String tag = Prompt.inputString(String.format("태그(변경 전 : %s) : ", board.getTag()));

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("y")) {
      board.setTitle(title);
      board.setContent(content);
      board.setTag(tag);

      requestAgent.request("board.update", board);
      if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
        System.out.println("게시글 변경 실패!");
        System.out.println(requestAgent.getObject(String.class));
      }
      System.out.println("게시글을 변경하였습니다.\n");
      return;
    }
    System.out.println("게시글 변경을 취소하였습니다.\n");
    return;
  }

}






