package com.eomcs.pms.table;

import com.eomcs.pms.domain.Board;
import com.eomcs.server.DataProcessor;
import com.eomcs.server.Request;
import com.eomcs.server.Response;

public class BoardTable extends JsonDataTable<Board> implements DataProcessor{

  public BoardTable() {
    super("board.json", Board.class);
  }

  @Override
  public void execute(Request request, Response response) throws Exception {
    // TODO Auto-generated method stub
    switch (request.getCommand()) {
      case "board.insert" : insert(request, response); break;
      case "board.selectList" : selectList(request, response); break;
      case "board.selectOne" : selectOne(request, response); break;
      case "board.update" : update(request, response); break;
      case "board.delete" : delete(request, response); break;
      //      case "board.search" : search(request, response); break;

      default :
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }

  private void insert(Request request, Response response) throws Exception {
    Board board = request.getObject(Board.class);
    list.add(board);
    response.setStatus(Response.SUCCESS);
  }

  private void selectList(Request request, Response response) throws Exception {
    response.setStatus(Response.SUCCESS);
    response.setValue(list);
  }


  private void selectOne(Request request, Response response) throws Exception {
    int no = Integer.parseInt(request.getParameter("no"));
    Board board = findByNo(no);
    if (board != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(board);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 게시글이 없습니다.");
    }
  }

  private void update(Request request, Response response) throws Exception {
    Board board = request.getObject(Board.class);

    int index = indexOf(board.getBoardNumber());

    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 게시글이 없습니다.");
      return;
    } 
    list.set(index, board);
    response.setStatus(Response.SUCCESS);
  }
  //
  private void delete(Request request, Response response) throws Exception {
    int no = Integer.parseInt(request.getParameter("no"));
    int index = indexOf(no);

    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 게시글을 찾을 수 없습니다.");
      return;
    }

    list.remove(index);
    response.setStatus(Response.SUCCESS);
  }
  //

  private Board findByNo(int no) {
    for (Board board : list) {
      if (board.getBoardNumber() == no) {
        return board;
      }
    }
    return null;
  }

  private int indexOf(int no) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getBoardNumber() == no) {
        return i;
      }
    }
    return -1;
  }

  //  private void search(Request request, Response response) throws Exception {
  //    int no = Integer.parseInt(request.getParameter("no"));
  //    Board board = findByNo(no);
  //    if (board != null) {
  //      response.setStatus(Response.SUCCESS);
  //      response.setValue(board);
  //    } else {
  //      response.setStatus(Response.FAIL);
  //      response.setValue("해당 번호의 게시글이 없습니다.");
  //    }
  //  }
}
