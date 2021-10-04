package com.eomcs.pms.table;

import com.eomcs.pms.domain.Board;
import com.eomcs.pms.domain.Comment;
import com.eomcs.server.DataProcessor;
import com.eomcs.server.Request;
import com.eomcs.server.Response;

public class CommentTable extends JsonDataTable<Comment> implements DataProcessor {
  BoardTable boardTable;

  public CommentTable(BoardTable boardTable) {
    this.boardTable = boardTable;
  }
  @Override
  public void execute(Request request, Response response) throws Exception {
    switch (request.getCommand()) {
      case "comment.insert" : insert(request, response); break;
      case "addNumber.comment" : addNumber(request, response); break;
      //      case "comment.selectList" : selectList(request, response); break;
      default: response.setStatus(Response.FAIL);
      response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }

  private void insert (Request request, Response response) {
    Comment comment = request.getObject(Comment.class);
    list.add(comment);
    response.setStatus(Response.SUCCESS);

  }
  //  private void selectList(Request request, Response response) throws Exception {
  //    int boardNo = Integer.parseInt(request.getParameter("boardNo"));
  //    Board board = boardTable.findByNo(boardNo);
  //    list = board.getCommentList();
  //    response.setStatus(Response.SUCCESS);
  //    response.setValue(list);
  //  }
  private void addNumber(Request request, Response response) {

  }

  public Comment findCommentByNo(int commentNumber, Board board) {
    for (Comment comment : board.getCommentList()) {
      if (comment.getCommentNumber()==commentNumber) {
        return comment;
      }
    }
    return null;
  }

}
