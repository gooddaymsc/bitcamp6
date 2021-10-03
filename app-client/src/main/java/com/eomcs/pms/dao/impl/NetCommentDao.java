package com.eomcs.pms.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.eomcs.pms.dao.CommentDao;
import com.eomcs.pms.domain.Board;
import com.eomcs.pms.domain.Comment;
import com.eomcs.request.RequestAgent;

public class NetCommentDao implements CommentDao {

  RequestAgent requestAgent;

  public NetCommentDao(RequestAgent requestAgent) {
    this.requestAgent =  requestAgent;
  }

  @Override
  public void insert(Comment comment) throws Exception {
    //    requestAgent.request("addNumber.comment", null);
    //    int no = requestAgent.getObject(Integer.class);
    //    comment.setCommentNumber(no);

    requestAgent.request("comment.insert", comment);
    if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
      throw new Exception("댓글 데이터 저장 실패");
    }
  }

  @Override
  public List<Comment> findAll(int boardNo) throws Exception {
    HashMap<String, String> params = new HashMap<>();
    params.put("boardNo", String.valueOf(boardNo));

    requestAgent.request("comment.selectList", params);
    if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("댓글 데이터 조회 실패");
    }
    return new ArrayList<>(requestAgent.getObjects(Comment.class));
  }

  @Override
  public Board findBoardByNo(int no) throws Exception {
    HashMap<String, String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("board.selectOne", params);
    if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
      return null;
    }
    return requestAgent.getObject(Board.class);
  }
}
