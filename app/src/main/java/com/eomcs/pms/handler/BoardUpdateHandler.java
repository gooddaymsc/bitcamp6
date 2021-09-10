package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Board;
import com.eomcs.util.Prompt;

public class BoardUpdateHandler extends AbstractBoardHandler  {

  public BoardUpdateHandler(List<Board> boardList) {
    super(boardList);
  }

  @Override
  public void execute() {

    System.out.println("[게시글 변경]");
    int no = Prompt.inputInt("변경할 게시글 번호 : ");

    Board board = findByNo(no);

    if (board == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    if ((board.getWriter() != App.getLoginUser().getId()) &
        (App.getLoginUser().getAuthority() != Menu.ACCESS_ADMIN)) {
      System.out.println("작성자가 아니므로 변경할 수 없습니다.");
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
      System.out.println("게시글을 변경하였습니다.");
      return;
    }

    System.out.println("게시글 변경을 취소하였습니다.");
    return;
  }

}






