package com.eomcs.pms.table;

import com.eomcs.pms.domain.Board;
import com.eomcs.pms.domain.Comment;
import com.eomcs.server.DataProcessor;
import com.eomcs.server.Request;
import com.eomcs.server.Response;

public class BoardTable extends JsonDataTable<Board> implements DataProcessor{

  public BoardTable() {
    super("board.json", Board.class);
  }

  @Override
  public void execute(Request request, Response response) throws Exception {
    switch (request.getCommand()) {
      case "board.insert" : insert(request, response); break;
      case "board.selectList" : selectList(request, response); break;
      case "board.selectOne" : selectOne(request, response); break;
      case "board.update" : update(request, response); break;
      case "board.delete" : delete(request, response); break;
      case "board.search" : search(request, response); break;
      case "board.comment.insert" : commentInsert(request, response); break;
      case "board.comment.selectList" : commentSelectList(request, response); break;
      case "board.comment.selectOne" : commentSelectOne(request, response); break;
      case "board.comment.update" : commentUpdate(request, response); break;

      default :
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }
  // 댓글 등록
  private void commentInsert(Request request, Response response) {
    Comment comment = request.getObject(Comment.class);
    Board board = findByNo(comment.getBoardNumber());
    board.getCommentList().add(comment);
    board.setTotalCommentNumber(board.getTotalCommentNumber()+1);
    response.setStatus(Response.SUCCESS);
  }

  private void commentSelectList(Request request, Response response) throws Exception{
    int no = Integer.parseInt(request.getParameter("boardNo"));
    Board board = findByNo(no);
    if (board != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(board.getCommentList());

    } else {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 게시글이 없습니다.");
    }
  }

  private void commentSelectOne(Request request, Response response) throws Exception {
    int commentNo = Integer.parseInt(request.getParameter("commentNo"));
    int boardNo = Integer.parseInt(request.getParameter("boardNo"));
    Board board = findByNo(boardNo);
    Comment comment = findCommentByNo(commentNo, board);

    if (comment != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(comment);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 댓글이 없습니다.");
    }
  }

  private Comment findCommentByNo(int commentNo, Board board) {
    for (Comment comment : board.getCommentList()) {
      if (comment.getCommentNumber()==commentNo) {
        return comment;
      }
    }
    return null;
  }
  // 댓글 변경
  private void commentUpdate(Request request, Response response) {
    Comment comment =  request.getObject(Comment.class);
    int index = indexOf(comment.getBoardNumber(), comment.getCommentNumber());

    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("댓글 데이터 변경 실패!");
      return;
    } 

    Board board = findByNo(comment.getBoardNumber());
    board.getCommentList().set(index, comment);
    response.setStatus(Response.SUCCESS);
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

  protected Board findByNo(int no) {
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

  private int indexOf(int boardNo, int commentNo) {
    Board board = findByNo(boardNo);
    for (int i = 0; i < board.getCommentList().size(); i++) {
      if (board.getCommentList().get(i).getCommentNumber() == commentNo) {
        return i;
      }
    }
    return -1;
  }

  private void search(Request request, Response response) throws Exception {
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
}
